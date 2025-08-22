public class App {
    public static void main(String[] args) throws Exception {
        // primer parcial 19/9/2025
        // matriz cuadrada, indiciar si todas las columnas de la matriz
        // tienen un numero divisible por otro pasado por parametro, 
        // 1 - de forma iterativa
        // 2 - de forma recursiva 
        int[][] matriz = {{2,4,5},{10,2,3},{2,15,1}};
        //System.out.println(todasLasColumnasConAlMenosUnDivisiblePor(matriz,5)); //  verdadero
        int[][] matriz1 = {{2,4,5},{11,2,3},{2,15,1}};
        //System.out.println(todasLasColumnasConAlMenosUnDivisiblePor(matriz1,5)); //  falso
        System.out.println(elementoEnColumna(matriz1, 0, 12));
    }

    public static boolean todasLasColumnasConUnElemDivisiblePor(int[][] matriz, int columna, int elemento) {
        // realizar verificaciones, matriz no nula, cuadrada, no vacia...
        return todasLasColumnasConUnElemDivisiblePorRec(matriz,columna,elemento,0);
    }    

    private static boolean todasLasColumnasConUnElemDivisiblePorRec(int[][] matriz, int columna, int elemento, int i) {
        if(matriz[0].length==i) return true;  
        if(!elementoEnColumna(matriz,columna,elemento)) return false;  // O(n) => k=1
        return todasLasColumnasConUnElemDivisiblePorRec(matriz,columna,elemento,i+1);
    } // metodo: sustraccion, a=1, b=1, k=1  =>  O(n^(k+1)) => O(n^(1+1)) => O(n^2)

    public static boolean elementoEnColumna(int[][] matriz, int columna, int elemento) {
        // realizar verificaciones, matriz no nula, cuadrada, no vacia...
        return elementoEnColumnaRec(matriz,columna,elemento,0);
    }    

    private static boolean elementoEnColumnaRec(int[][] matriz, int columna, int elemento, int i) {
        if(matriz.length==i) return false;
        if(matriz[i][columna]%elemento==0) return true; // k=0
        return elementoEnColumnaRec(matriz,columna,elemento,i+1);
    } // metodo sustraccion, a=1  b=1  k=0  =>  O(n^(k+1)) => O(n^(0+1)) => O(n)

    private static boolean todasLasColumnasConAlMenosUnDivisiblePor(int[][] matriz, int elemento){
        boolean rta = true; // 1
        for (int col = 0; col < matriz[0].length; col++) { // 1 + 2(n + 1) + n = 1 + 2n + 2 + n = 3+3n
            rta = rta && algunElementoMultiploEnColumna(matriz,col,elemento);
            // n * ( 1 + 1 + j(n) ) = // n * ( 1 + 1 + 5 + 8n ) = n + n + 5n + 8n^2
        }
        return rta; // 1
    } //f(n) = 3 + 7n + 8n^2 
    //  f(n^2) <= c g(n^2 )   => desmostrar que es cuadratico de manera asintotica
    //  3 + 7n + 8n^2 <= 9 n^2   // termino dominante mas 1
    //  3 + 7n + 8n^2 <= 9 n^2   // divido todo por n^2  y luego simplifico
    //  3/n^2 + 7n/n^2 + 8n^2/n^2 <= 9 n^2/n^2  
    //  3/n^2 + 7/n + 8 <= 9   
    //  n0 = 1   3/1 + 7/1 + 8 <= 9   =>  18 <= 9  no es menor, no cumple para n0=1
    //  n0 = 2   ... no cumple
    //  n0 = 8   8,92 <= 9, cumple por lo que, f(n^2) pertenece a O(n^2) para c = 9  y n0 >= 8

    private static boolean algunElementoMultiploEnColumna(int[][] matriz, int col, int elemento) {
        boolean rta = false; // 1
        for (int f = 0; f < matriz[0].length; f++) { // 1 + 2(n + 1) + n = 1 + 2n + 2 + n = 3+3n
            rta = rta || matriz[f][col]%elemento==0; // 5 n
        }
        return rta; // 1
    } // j(n) = 5 + 8n

}
