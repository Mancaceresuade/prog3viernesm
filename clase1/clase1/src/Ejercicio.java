public class Ejercicio {

    public static int contarCantidad(int[][] matriz) {
        int n = matriz.length; // 1
        int contador = n * n; // 2
        int valor = 0; // 1
        int i = 0;          // 1
        int j = 0;      // 1
        while (i < matriz.length) { // n + 1
            valor += matriz[i][j];  // 2 n
            j++; // 1 n
    
            if (j == matriz[i].length) {  // 2n
                j = 0; // n
                i++; // n
            }
        }
        return valor / contador; // 2
    } // f(n) = 1 + 2 + 1 + 1 + n + 2n + 1 + n + 2n + n + n + 2
    // f(n) = 8n + 9
    // f(n) <= c g(n)
    // 8n + 9 <= 9n // busco el término dominante y sumo uno a la constante
    // 8 + 9/n <= 9 // divido todo por n y simplifico
    // para n0 = 1 se cumple ? 8 + 9 <= 9 no se cumple
    // para n0 = 9 se cumple
    // f(n) PERTENECE A O(n) PARA n0 >= 9 Y c = 9






    public static int mean(int [][]arr){
        int n = arr.length; // 1
        int suma = 0;   // 1
        int cantidad = n * n ; // 2
        for (int i = 0; i < n; i++) {  // 1 + n + 1 + n
            for (int j = 0; j < n; j++) { // n (1 + n + 1 + n)
                suma+= arr[i][j]; // n ( 2n )
            }
        }
        int prom = suma/cantidad; // 2
        return prom; // 1
    } // f(n) = 1+1+2+1 + n + 1 + n+n (1 + n + 1 + n)+ n ( 2n )+2+1   // distribución de términos
    //  f(n) = 1+1+2+1 + n + 1 + n+ n + n^2 + n + n^2 + 2n^ + 2 + 1
    // f(n) = 9 + 4n + 6n^2
    // f(n) <= c g(n)
    //  9 + 4n + 6n^2 <= 7n^2 // busco el término dominante y sumo uno a la constante
    //  9/n^2 + 4n/n^2 + 6n^2/n^2 <= 7n^2/n^2 // divido todo por n^2
    //  9/n^2 + 4/n + 6 <= 7
    // para n0 = 1 se cumple ? 9 + 4 + 6 <= 7 no se cumple
    // para n0 = 2 se cumple ? 9/4 + 4/2 + 6 <= 7 no se cumple
    // para n0 = 3 se cumple ? 9/9 + 4/3 + 6 <= 7 no se cumple
    // para n0 = 4 se cumple ? 9/16 + 4/4 + 6 <= 7 no se cumple
    // f(n) PERTENECE A O(n^2) PARA n0 >= 6 Y c = 7
    
    

    
    
    public static void main(String[] args) {
        
    }
}
