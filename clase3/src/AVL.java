import java.util.ArrayList;
import java.util.List;

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

    // Clase para representar un jugador con nombre y puntaje
    public static class Jugador implements Comparable<Jugador> {
        private String nombre;
        private int puntaje;

        public Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntaje() {
            return puntaje;
        }

        @Override
        public int compareTo(Jugador otro) {
            // Ordenamos por puntaje descendente (mayor puntaje primero)
            return Integer.compare(otro.puntaje, this.puntaje);
        }

        @Override
        public String toString() {
            return nombre + " (" + puntaje + ")";
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

    /**
     * Busca los k mejores jugadores en el rango de puntajes [p_min, p_max]
     * @param p_min Puntaje mínimo del rango
     * @param p_max Puntaje máximo del rango
     * @param k Número de mejores jugadores a retornar
     * @return Lista con los k mejores jugadores en el rango especificado
     */
    public List<Jugador> buscarMejoresJugadoresEnRango(int p_min, int p_max, int k) {
        List<Jugador> resultado = new ArrayList<>();
        buscarMejoresJugadoresEnRangoRec(raiz, p_min, p_max, k, resultado);
        return resultado;
    }

    /**
     * Método recursivo para buscar los k mejores jugadores en un rango
     * Optimizado para no visitar nodos fuera del rango [p_min, p_max]
     */
    @SuppressWarnings("unchecked")
    private void buscarMejoresJugadoresEnRangoRec(Nodo<T> nodo, int p_min, int p_max, int k, List<Jugador> resultado) {
        if (nodo == null || resultado.size() >= k) {
            return;
        }

        Jugador jugador = (Jugador) nodo.elem;
        int puntaje = jugador.getPuntaje();

        // Si el puntaje está en el rango, lo agregamos al resultado
        if (puntaje >= p_min && puntaje <= p_max) {
            resultado.add(jugador);
        }

        // Optimización: decidimos qué subárbol visitar primero
        // Si el puntaje actual es mayor que p_max, solo visitamos el subárbol izquierdo
        // Si el puntaje actual es menor que p_min, solo visitamos el subárbol derecho
        // Si está en el rango, visitamos ambos subárboles

        if (puntaje > p_max) {
            // El puntaje actual es mayor que el máximo del rango
            // Solo necesitamos visitar el subárbol izquierdo (puntajes menores)
            buscarMejoresJugadoresEnRangoRec(nodo.izq, p_min, p_max, k, resultado);
        } else if (puntaje < p_min) {
            // El puntaje actual es menor que el mínimo del rango
            // Solo necesitamos visitar el subárbol derecho (puntajes mayores)
            buscarMejoresJugadoresEnRangoRec(nodo.der, p_min, p_max, k, resultado);
        } else {
            // El puntaje actual está en el rango
            // Visitamos ambos subárboles, pero priorizamos el derecho (puntajes mayores)
            // ya que queremos los mejores jugadores
            buscarMejoresJugadoresEnRangoRec(nodo.der, p_min, p_max, k, resultado);
            if (resultado.size() < k) {
                buscarMejoresJugadoresEnRangoRec(nodo.izq, p_min, p_max, k, resultado);
            }
        }
    }

    /**
     * Método alternativo que utiliza un recorrido in-order optimizado
     * para obtener los jugadores ordenados por puntaje en el rango
     */
    public List<Jugador> buscarMejoresJugadoresEnRangoInOrder(int p_min, int p_max, int k) {
        List<Jugador> resultado = new ArrayList<>();
        buscarMejoresJugadoresEnRangoInOrderRec(raiz, p_min, p_max, k, resultado);
        return resultado;
    }

    @SuppressWarnings("unchecked")
    private void buscarMejoresJugadoresEnRangoInOrderRec(Nodo<T> nodo, int p_min, int p_max, int k, List<Jugador> resultado) {
        if (nodo == null || resultado.size() >= k) {
            return;
        }

        Jugador jugador = (Jugador) nodo.elem;
        int puntaje = jugador.getPuntaje();

        // Recorrido in-order optimizado: derecha -> raíz -> izquierda
        // Esto nos da los jugadores ordenados de mayor a menor puntaje

        // Primero visitamos el subárbol derecho (puntajes mayores)
        if (puntaje >= p_min) {
            buscarMejoresJugadoresEnRangoInOrderRec(nodo.der, p_min, p_max, k, resultado);
        }

        // Luego procesamos el nodo actual si está en el rango
        if (resultado.size() < k && puntaje >= p_min && puntaje <= p_max) {
            resultado.add(jugador);
        }

        // Finalmente visitamos el subárbol izquierdo (puntajes menores)
        if (puntaje <= p_max) {
            buscarMejoresJugadoresEnRangoInOrderRec(nodo.izq, p_min, p_max, k, resultado);
        }
    }
}