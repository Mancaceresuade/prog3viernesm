import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[] numeros = {2,3,5};
        int objetivo = 5;
        generarObjetivo(numeros,objetivo);
        System.out.println(" ");
        //generarCombinaciones(numeros, 0);
    }

    public static void generarCombinaciones(int[] numeros, int i) {
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>(); // [{2,3}, {5}]
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        generarCombinaciones(numeros, i, resultados, combinacion_actual);
        resultados.forEach(r -> System.out.println(r));
    }

    public static void generarCombinaciones(int[] numeros, int i,ArrayList<ArrayList<Integer>> resultados,
    ArrayList<Integer> combinacion_actual) {
        resultados.add(new ArrayList<>(combinacion_actual));  // [{2,3}, {5}]
        for (int j = i; j < numeros.length; j++) {
            combinacion_actual.add(numeros[j]);
            generarCombinaciones(numeros, j+1,resultados,combinacion_actual);
            combinacion_actual.remove(combinacion_actual.size()-1);
        }
    }

    private static void generarObjetivo(int[] numeros, int objetivo) {
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>(); // [{2,3}, {5}]
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, 0, 0);
        resultados.forEach(r -> System.out.println(r));
    }

    private static void generarObjetivo(int[] numeros, int objetivo, ArrayList<ArrayList<Integer>> resultados,
            ArrayList<Integer> combinacion_actual, int suma, int posicion) {
        if (suma > objetivo) return;   // poda => mas eficiente, corta antes        
        if(posicion==numeros.length) {
            if(suma == objetivo){
                resultados.add(new ArrayList<>(combinacion_actual));  // [{2,3}, {5}]
            }
            return;
        }
        // if (suma > objetivo) return; // menos eficiente
        combinacion_actual.add(numeros[posicion]);
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, suma+numeros[posicion], posicion+1);
        combinacion_actual.remove(combinacion_actual.size()-1);
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, suma, posicion+1);
    }

    


}
