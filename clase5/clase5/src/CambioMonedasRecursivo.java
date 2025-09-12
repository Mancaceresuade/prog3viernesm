import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// n= 5  para {1,2,5}
// 11111
// 1112
// 122
// 5


public class CambioMonedasRecursivo {

    public static void main(String[] args) {
        int[] monedas = {1, 2, 5};
        int n = 5;

        List<List<Integer>> res = listarCombinacionesOrdenadas(monedas, n);

        for (List<Integer> comb : res) {
            System.out.println(comb);
        }
        System.out.println("Formas de dar cambio: " + res.size());
    }

    // Lista combinaciones en orden: primero usando la moneda más grande
    static List<List<Integer>> listarCombinacionesOrdenadas(int[] monedas, int n) {
        // Ordenamos DESC para priorizar monedas grandes primero
        Arrays.sort(monedas); // O(n log n)
        for (int l = 0, r = monedas.length - 1; l < r; l++, r--) {
            int tmp = monedas[l]; monedas[l] = monedas[r]; monedas[r] = tmp;
        }

        List<List<Integer>> resultado = new ArrayList<>();
        backtrackDesc(monedas, 0, n, new ArrayList<>(), resultado); // O(n)
        return resultado;
    } // O(n log n)
    //               O(n log n)     O(n)
    // para n 1000   9966           1000   


    // Permite reutilizar la misma moneda antes de pasar a la siguiente
    static void backtrackDesc(int[] monedas, int i, int resto, List<Integer> actual, List<List<Integer>> res) {
        if (resto == 0) {
            res.add(new ArrayList<>(actual));
            return;
        }
        if (i == monedas.length || resto < 0) return;

        // Se prioriza que salgan la de mas alto valor y despues mezclas con 2, etc.
        if (monedas[i] <= resto) {
            actual.add(monedas[i]);
            backtrackDesc(monedas, i, resto - monedas[i], actual, res);
            actual.remove(actual.size() - 1);
        }

        // Pasas a la siguiente moneda (más chica)
        backtrackDesc(monedas, i + 1, resto, actual, res);
    } //metodo sustraccion, a=1, b=1 , k=0  => O(n)
}