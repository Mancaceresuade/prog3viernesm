import java.util.ArrayList;

public class Moneda {
    static int montoTotal = 6;
    static int menorCantidad = Integer.MAX_VALUE;    
    static ArrayList<Integer> mejorCombinacion = new ArrayList<>();

    public static void main(String[] args) {
        int[] monedas = {1,2,5};
        ArrayList<Integer> com_actual = new ArrayList<>();
        menorCantidadMonedas(monedas, com_actual);
        System.out.println(mejorCombinacion);
    }

    private static void menorCantidadMonedas(int[] monedas, ArrayList<Integer> com_actual) {
        int suma_actual = com_actual.stream().mapToInt(Integer::intValue).sum();

        // poda por factibilidad
        if (suma_actual > montoTotal) return;

        // caso base
        if (suma_actual == montoTotal) {
            if (com_actual.size() < menorCantidad) {
                menorCantidad = com_actual.size();
                mejorCombinacion = new ArrayList<>(com_actual);
            }
            return;
        }

        // *** poda por bound ***
        int falta = montoTotal - suma_actual;
        int mayorMoneda = monedas[monedas.length - 1];
        int cotaInferior = com_actual.size() + (int)Math.ceil((double)falta / mayorMoneda);
        if (cotaInferior >= menorCantidad) return;

        // ramificación
        for (int moneda : monedas) {
            com_actual.add(moneda);
            menorCantidadMonedas(monedas, com_actual);
            com_actual.remove(com_actual.size() - 1);
        }
    }
}
