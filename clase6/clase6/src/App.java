public class App {

    public static int calcular(int[] arr, int i, int f) {
        if (i == f) return arr[i];
        int n = (i + f) / 2;
        int da = calcular(arr, i, n);
        int ha = calcular(arr, n + 1, f);
        return da + ha;
    } // metodo division, a=2,b=2,k=0  caso? a>b*k   =>  O(n)


    public static int sumaCompleja(int[] arr, int inicio, int fin) {
        if (inicio == fin) return arr[inicio];  
        int medio = (inicio + fin) / 2;
        int sumaIzquierda = sumaCompleja(arr, inicio, medio);    
        int sumaDerecha   = sumaCompleja(arr, medio + 1, fin);  
        int sumaExtra = 0;
        for (int i = inicio; i <= fin; i++) {  
            sumaExtra += arr[i] * 2;            
        }
        return sumaIzquierda + sumaDerecha + sumaExtra;
    } // metodo division, a=2,b=2,k1  caso a=b*k  =>  O(n log n)

    public static void main(String[] args) {
        int matriz1[][] = {{2,3,4},{3,5,2},{7,2,4}};
        int vector1[] = {3,5,2};
        System.out.println(verificarCoincidencia(matriz1, vector1, 3, 3)); // verdadero

        int matriz2[][] = {{2,3,4},{3,5,2},{7,2,4}};
        int vector2[] = {3,5,1};
        System.out.println(verificarCoincidencia(matriz2, vector2, 3, 3)); // falso
    }

    public static boolean verificarCoincidencia(int[][] matriz, int[] vector, int filas, int columnas) {
        for (int i = 0; i < filas; i++) { // 1+2n
            if (!verificarFila(matriz, vector, i, columnas)) {
            // n ( v(n) )    
                return false; // 1
            }
        }
        return true; // 1
    } // f(n) = 1+2n+n(v(n))+1+1= 1+2n+ n(3+4n+3n**2) + 1+1
    // f(n) = 3+2n+ 3n+ 4n**2 + 3n**3 = 3+5n+4n**2 + 3n**3
    // f(n) <= c g(n)
    // 3+5n+4n**2 + 3n**3 <= 4n**3
    // 3/n**3 + 5n/n**3 + 4n**2/n**3 + 3n**3/n**3 <= 4n**3/n**3
    // 3/n**3 + 5/n**2 + 4/n + 3 <= 4
    // para n0=1 
    // ...
    // para n0=6  cumple   por lo que f(n) pertenece a O(n**3) para c=4 y desde n0 = 6


    private static boolean verificarFila(int[][] matriz, int[] vector, int fila, int columnas) {
        for (int j = 0; j < columnas; j++) { // 1+2n
            if (!compararCeldaVariasVeces(matriz[fila][j], vector[j], columnas)) {
            // n ( h(n) )    
                return false; // 1
            }
        }
        return true; // 1
    } // v(n) = 1+2n+n ( h(n) )+1+1 = 1+2n+n ( 2+3n )+1+1 = 1+2n+2n+3n**2+1+1 = 3+4n+3n**2   

    private static boolean compararCeldaVariasVeces(int valorCelda, int valorVector, int repeticiones) {
        for (int k = 0; k < repeticiones; k++) { // 1+n+n
            if (valorCelda != valorVector) { // n
                return false; // 1
            }
        }
        return true; // 1
    } // h(n) = 2+3n
}
