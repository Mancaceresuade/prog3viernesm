import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[] numeros = {2,3,5};
        int objetivo = 5;
        generarObjetivo(numeros,objetivo);
    }

    private static void generarObjetivo(int[] numeros, int objetivo) {
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>(); // [{2,3}, {5}]
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, 0, 0);
        resultados.forEach(r -> System.out.println(r));
    }

    private static void generarObjetivo(int[] numeros, int objetivo, ArrayList<ArrayList<Integer>> resultados,
            ArrayList<Integer> combinacion_actual, int suma, int posicion) {
        if(posicion==numeros.length) {
            if(suma == objetivo){
                resultados.add(new ArrayList<>(combinacion_actual));  // [{2,3}, {5}]
            }
            return;
        }
        combinacion_actual.add(numeros[posicion]);
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, suma+numeros[posicion], posicion+1);
        combinacion_actual.remove(combinacion_actual.size()-1);
        generarObjetivo(numeros, objetivo, resultados, combinacion_actual, suma, posicion+1);

    }

    


}
