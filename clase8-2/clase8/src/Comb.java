import java.util.ArrayList;

public class Comb {
    public static void main(String[] args) {
        int[] numeros = {2,3,5}; 
        ArrayList<Integer> combi_actual = new ArrayList<Integer>();
        combi(numeros,0, combi_actual);
    }

    private static void combi(int[] numeros, int i, ArrayList<Integer> combi_actual) {
        if(i==numeros.length){
            imprimir(combi_actual);
            return;
        }
        combi_actual.add(numeros[i]);
        combi(numeros, i+1, combi_actual);
        combi_actual.remove(combi_actual.size()-1);
        combi(numeros, i+1, combi_actual);
    }

    private static void imprimir(ArrayList<Integer> combi_actual) {
        combi_actual.forEach(r -> System.out.print(r));
        System.out.println(" ");
    }
    
}
