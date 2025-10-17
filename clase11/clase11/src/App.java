import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[] numeros = {2,3,5}; // 235 23 2 3 35 5 25
        int objetivo = 5;  // 
        ArrayList<Integer> com_actual = new ArrayList<>();
        combinar(numeros,com_actual,0, objetivo);
    }
    private static void combinar(int[] numeros, ArrayList<Integer> com_actual, int i, int objetivo) {
        // poda
        if(com_actual.stream().mapToInt(Integer::intValue).sum() > objetivo) return;
        // caso base
        if(i==numeros.length) {
            // filtro
            if(objetivo == com_actual.stream().mapToInt(Integer::intValue).sum()){
                com_actual.forEach(n-> System.out.print(n+" "));
                System.out.println(" ");
            }
            return;
        }
        com_actual.add(numeros[i]);
        combinar(numeros, com_actual, i+1,objetivo);
        com_actual.remove(com_actual.size()-1); // TRUCO
        combinar(numeros, com_actual, i+1,objetivo);
    } // sustraccion a=2  b=1 k=0   => O(2**n)
    
}
