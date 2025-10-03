import java.util.ArrayList;

public class Combina {
    public static void main(String[] args) {
        int[] numeros = {2,3,5} ;
        combina(numeros);   
    }
    private static void combina(int[] numeros) {
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>();
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        combina(numeros,combinacion_actual,0,resultados);
        resultados.forEach(r -> System.out.println(r));
    }
    private static void combina(int[] numeros, ArrayList<Integer> combinacion_actual, int i,
            ArrayList<ArrayList<Integer>> resultados) {
        if(i==numeros.length)        {
            resultados.add(new ArrayList<>(combinacion_actual));
            return;
        }
        combinacion_actual.add(numeros[i]);
        combina(numeros, combinacion_actual, i+1, resultados);
        combinacion_actual.remove(combinacion_actual.size()-1);
        combina(numeros, combinacion_actual, i+1, resultados);
    }
    
    
}
