import java.util.ArrayList;

public class Moneda1 {
    static int objetivo = 6;
    public static void main(String[] args) {
        int[] monedas = {1,2,5};
        ArrayList<Integer> comb_actual = new ArrayList<>();
        calcular(monedas, comb_actual, 0);
    }

    private static void calcular(int[] monedas, ArrayList<Integer> comb_actual, int i) {
        int sumaActual = comb_actual.stream().mapToInt(Integer::intValue).sum();
        if(sumaActual == objetivo) {
            System.out.println(comb_actual);
            return;
        }    
        if (sumaActual > objetivo) {
            // Caso Base de Fallo: Se excedió el objetivo
            return;
        }
        for (int j = i; j < monedas.length; j++) {
            comb_actual.add(monedas[j]);
            calcular(monedas, comb_actual, j);
            comb_actual.remove(comb_actual.size()-1);
        }
    }
}
