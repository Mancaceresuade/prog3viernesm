// Persistencia simple en localStorage
const STORAGE_KEY = 'followers_graph_v1';

class AdjacencyListGraph {
    constructor() {
        // Map<string, Set<string>>: usuario -> a quién sigue (out-edges)
        this.followingMap = new Map();
        // Map<string, Set<string>>: usuario -> quiénes lo siguen (in-edges)
        this.followersMap = new Map();
    }

    static fromObject(obj) {
        const g = new AdjacencyListGraph();
        if (!obj) return g;
        for (const [u, list] of Object.entries(obj.following || {})) {
            g.followingMap.set(u, new Set(list));
        }
        for (const [u, list] of Object.entries(obj.followers || {})) {
            g.followersMap.set(u, new Set(list));
        }
        return g;
    }

    toObject() {
        const following = {};
        const followers = {};
        for (const [u, set] of this.followingMap) following[u] = Array.from(set);
        for (const [u, set] of this.followersMap) followers[u] = Array.from(set);
        return { following, followers };
    }

    ensureUser(user) {
        if (!this.followingMap.has(user)) this.followingMap.set(user, new Set());
        if (!this.followersMap.has(user)) this.followersMap.set(user, new Set());
    }

    addUser(user) {
        if (!user) return false;
        if (this.followingMap.has(user)) return false;
        this.followingMap.set(user, new Set());
        this.followersMap.set(user, new Set());
        return true;
    }

    removeUser(user) {
        if (!this.followingMap.has(user)) return false;
        // Eliminar out-edges
        for (const followed of this.followingMap.get(user)) {
            this.followersMap.get(followed)?.delete(user);
        }
        this.followingMap.delete(user);
        // Eliminar in-edges
        for (const follower of this.followersMap.get(user)) {
            this.followingMap.get(follower)?.delete(user);
        }
        this.followersMap.delete(user);
        return true;
    }

    follow(follower, followed) {
        if (!follower || !followed || follower === followed) return false;
        this.ensureUser(follower);
        this.ensureUser(followed);
        const before = this.followingMap.get(follower).size;
        this.followingMap.get(follower).add(followed);
        this.followersMap.get(followed).add(follower);
        return this.followingMap.get(follower).size !== before;
    }

    unfollow(follower, followed) {
        if (!follower || !followed || follower === followed) return false;
        if (!this.followingMap.has(follower) || !this.followersMap.has(followed)) return false;
        const removed = this.followingMap.get(follower).delete(followed);
        if (removed) this.followersMap.get(followed).delete(follower);
        return removed;
    }

    getFollowing(user) {
        return Array.from(this.followingMap.get(user) || []);
    }

    getFollowers(user) {
        return Array.from(this.followersMap.get(user) || []);
    }

    getUsers() {
        return Array.from(new Set([...this.followersMap.keys(), ...this.followingMap.keys()])).sort((a, b) => a.localeCompare(b));
    }
}

function saveGraph(graph) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(graph.toObject()));
}

function loadGraph() {
    try {
        const raw = localStorage.getItem(STORAGE_KEY);
        if (!raw) return new AdjacencyListGraph();
        return AdjacencyListGraph.fromObject(JSON.parse(raw));
    } catch {
        return new AdjacencyListGraph();
    }
}

// UI
const graph = loadGraph();

const el = (id) => document.getElementById(id);
const usersList = el('usersList');
const followingList = el('followingList');
const followersList = el('followersList');
const graphDump = el('graphDump');

function renderUsers() {
    usersList.innerHTML = '';
    const users = graph.getUsers();
    if (users.length === 0) {
        const li = document.createElement('li');
        li.innerHTML = '<span class="muted">No hay usuarios aún</span>';
        usersList.appendChild(li);
        return;
    }
    for (const u of users) {
        const li = document.createElement('li');
        const info = document.createElement('div');
        info.textContent = u;
        const counts = document.createElement('span');
        counts.className = 'tag';
        counts.textContent = `${graph.getFollowers(u).length} seg. | ${graph.getFollowing(u).length} seguidos`;
        li.appendChild(info);
        li.appendChild(counts);
        usersList.appendChild(li);
    }
}

function renderQuery(user) {
    followingList.innerHTML = '';
    followersList.innerHTML = '';
    const following = graph.getFollowing(user).sort((a, b) => a.localeCompare(b));
    const followers = graph.getFollowers(user).sort((a, b) => a.localeCompare(b));
    if (following.length === 0) {
        const li = document.createElement('li');
        li.innerHTML = '<span class="muted">No sigue a nadie</span>';
        followingList.appendChild(li);
    } else {
        for (const u of following) {
            const li = document.createElement('li');
            li.textContent = u;
            followingList.appendChild(li);
        }
    }
    if (followers.length === 0) {
        const li = document.createElement('li');
        li.innerHTML = '<span class="muted">Sin seguidores</span>';
        followersList.appendChild(li);
    } else {
        for (const u of followers) {
            const li = document.createElement('li');
            li.textContent = u;
            followersList.appendChild(li);
        }
    }
}

function renderDump() {
    const obj = graph.toObject();
    graphDump.textContent = JSON.stringify(obj, null, 2);
}

function rerenderAll() {
    renderUsers();
    renderDump();
}

// Eventos
document.addEventListener('DOMContentLoaded', () => {
    // Add user
    const formAddUser = document.getElementById('form-add-user');
    formAddUser.addEventListener('submit', (e) => {
        e.preventDefault();
        const input = document.getElementById('newUser');
        const name = input.value.trim();
        if (name.length === 0) return;
        const created = graph.addUser(name);
        if (!created) {
            alert('El usuario ya existe o el nombre es inválido');
            return;
        }
        saveGraph(graph);
        rerenderAll();
        input.value = '';
    });

    // Follow / Unfollow
    const formFollow = document.getElementById('form-follow');
    const btnUnfollow = document.getElementById('btn-unfollow');
    formFollow.addEventListener('submit', (e) => {
        e.preventDefault();
        const follower = document.getElementById('follower').value.trim();
        const followed = document.getElementById('followed').value.trim();
        if (!follower || !followed) return;
        const changed = graph.follow(follower, followed);
        if (!changed) {
            alert('Relación ya existente o inválida');
        }
        saveGraph(graph);
        rerenderAll();
    });
    btnUnfollow.addEventListener('click', () => {
        const follower = document.getElementById('follower').value.trim();
        const followed = document.getElementById('followed').value.trim();
        if (!follower || !followed) return;
        const removed = graph.unfollow(follower, followed);
        if (!removed) {
            alert('No existía esa relación');
        }
        saveGraph(graph);
        rerenderAll();
    });

    // Queries
    const formQuery = document.getElementById('form-query');
    formQuery.addEventListener('submit', (e) => {
        e.preventDefault();
        const user = document.getElementById('queryUser').value.trim();
        if (!user) return;
        renderQuery(user);
    });

    rerenderAll();
});


