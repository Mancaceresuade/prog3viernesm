import java.util.*;


public class Main {

    static int[] monedas = {1, 2, 5};
    static int objetivo = 6;

    static int mejorCantidad = Integer.MAX_VALUE;
    static List<Integer> mejorCombinacion = new ArrayList<>();

    public static void main(String[] args) {
        // Ordenar de mayor a menor para explorar primero monedas grandes (mejor poda)
        Arrays.sort(monedas);
        for (int i = 0, j = monedas.length - 1; i < j; i++, j--) {
            int tmp = monedas[i]; monedas[i] = monedas[j]; monedas[j] = tmp;
        }

        System.out.println("Monedas disponibles: " + Arrays.toString(monedas));
        System.out.println("Monto objetivo: " + objetivo);

        List<Integer> actual = new ArrayList<>();
        resolver(0, 0, 0, actual);

        System.out.println("\nMínimo de monedas necesarias: " + mejorCantidad);
        System.out.println("Combinación óptima: " + mejorCombinacion);
    }

    /**
     * Backtracking + Branch & Bound recursivo.
     * @param nivel índice actual de moneda
     * @param suma  monto acumulado
     * @param usadas monedas usadas hasta el momento
     * @param actual lista actual de monedas seleccionadas
     */
    static void resolver(int nivel, int suma, int usadas, List<Integer> actual) {
        // Poda 1: si superamos el monto, no continuar
        if (suma > objetivo) return;

        // Poda 2: si ya usamos más monedas que la mejor solución conocida
        if (usadas >= mejorCantidad) return;

        // Poda 3: si la cota inferior ya no puede mejorar la mejor solución
        double cota = calcularCota(nivel, suma, usadas);
        if (cota >= mejorCantidad) return;

        // Caso base: encontramos una solución exacta
        if (suma == objetivo) {
            mejorCantidad = usadas;
            mejorCombinacion = new ArrayList<>(actual);
            return;
        }

        // Caso base: agotamos tipos de monedas
        if (nivel == monedas.length) return;

        int moneda = monedas[nivel];

        // RAMIFICACIÓN:
        // 1. Tomar esta moneda (se puede repetir)
        actual.add(moneda);
        resolver(nivel, suma + moneda, usadas + 1, actual);
        actual.remove(actual.size() - 1);

        // 2. Pasar a la siguiente moneda
        resolver(nivel + 1, suma, usadas, actual);
    } // a = 2 b = 1 k= 0  =>   2**n

    /**
     * Cota inferior (bound): cantidad mínima posible de monedas adicionales necesarias
     * usando la mayor moneda disponible desde el nivel actual.
     */
    static double calcularCota(int nivel, int suma, int usadas) {
        if (suma >= objetivo) return usadas;
        if (nivel >= monedas.length) return Double.POSITIVE_INFINITY;

        int restante = objetivo - suma;
        int mayor = monedas[nivel]; // porque están ordenadas desc
        double minExtra = (double) restante / mayor;
        return usadas + minExtra;
    }
}
 