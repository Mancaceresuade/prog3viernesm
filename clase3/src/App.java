public class App {
    public static void main(String[] args) throws Exception {
        // primer parcial 19/9/2025
        // matriz cuadrada, indiciar si todas las columnas de la matriz
        // tienen un numero divisible por otro pasado por parametro, de forma iterativa
        int[][] matriz = {{2,4,5},{10,2,3},{2,15,1}};
        System.out.println(todasLasColumnasConAlMenosUnDivisiblePor(matriz,5)); //  verdadero
        int[][] matriz1 = {{2,4,5},{11,2,3},{2,15,1}};
        System.out.println(todasLasColumnasConAlMenosUnDivisiblePor(matriz1,5)); //  falso
    }

    private static boolean todasLasColumnasConAlMenosUnDivisiblePor(int[][] matriz, int elemento){
        boolean rta = true;
        for (int col = 0; col < matriz[0].length; col++) {
            rta = rta && algunElementoMultiploEnColumna(matriz,col,elemento);
        }
        return rta;
    }

    private static boolean algunElementoMultiploEnColumna(int[][] matriz, int col, int elemento) {
        boolean rta = false;
        for (int f = 0; f < matriz[0].length; f++) {
            rta = rta || matriz[f][col]%elemento==0;
        }
        return rta;
    }

}
