public class AVL<T extends Comparable<T>> {
    Nodo<T> raiz;

    public class Nodo<U> {
        U elem;
        Nodo<U> izq;
        Nodo<U> der;
        @Override
        public String toString() {
            return elem.toString();
        }
    }

    public void insertar(T valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T valor) {
        if (nodo == null) {
            Nodo<T> nuevo = new Nodo<>();
            nuevo.elem = valor;
            return nuevo;
        }
        if (valor.compareTo(nodo.elem) < 0) {
            nodo.izq = insertarRec(nodo.izq, valor);
        } else if (valor.compareTo(nodo.elem) > 0) {
            nodo.der = insertarRec(nodo.der, valor);
        } else {
            return nodo; // No duplicados
        }
        return balancear(nodo);
    }

    private Nodo<T> balancear(Nodo<T> nodo) {
        int balance = altura(nodo.izq) - altura(nodo.der);
        if (balance > 1) {
            if (altura(nodo.izq.izq) >= altura(nodo.izq.der)) {
                nodo = rotarDerecha(nodo);
            } else {
                nodo.izq = rotarIzquierda(nodo.izq);
                nodo = rotarDerecha(nodo);
            }
        } else if (balance < -1) {
            if (altura(nodo.der.der) >= altura(nodo.der.izq)) {
                nodo = rotarIzquierda(nodo);
            } else {
                nodo.der = rotarDerecha(nodo.der);
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    private Nodo<T> rotarIzquierda(Nodo<T> x) {
        Nodo<T> y = x.der;
        x.der = y.izq;
        y.izq = x;
        return y;
    }

    private Nodo<T> rotarDerecha(Nodo<T> y) {
        Nodo<T> x = y.izq;
        y.izq = x.der;
        x.der = y;
        return x;
    }

    public void inOrder() {
        inOrder(raiz);
    }

    private void inOrder(Nodo<T> nodo) {
        if(nodo==null) return ;
        inOrder(nodo.izq);
        System.out.println(nodo.toString());
        inOrder(nodo.der);
    }

    
}
