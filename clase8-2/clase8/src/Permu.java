import java.util.Arrays;

public class Permu {
    public static void main(String[] args) {
        int[] numeros = {2, 3, 5}; 
        System.out.println("Permutaciones (Enfoque Swap Optimizado):");
        // Iniciamos la recursión con el array y la posición inicial (0)
        permu(numeros, 0); 
    } 
    
    // Función central: 'i' representa la posición actual que estamos intentando fijar.
    // Ya no necesita el ArrayList<Integer> perm.
    private static void permu(int[] numeros, int i) {
        
        // Caso Base: El índice ha llegado al final. El array 'numeros' 
        // ya contiene una permutación completa lista para imprimir.
        if (i == numeros.length) { 
            imprimir(numeros); // Imprime el array directamente
            return;
        }
        
        // Iteramos para intercambiar el elemento en la posición 'i' 
        // con todos los elementos desde 'i' hasta el final (j).
        for (int j = i; j < numeros.length; j++) { 
            
            // 1. Intercambio (Swap): Fija el elemento en la posición 'i'
            swap(numeros, i, j);
            
            // 2. Llamada Recursiva: Avanza a la siguiente posición a fijar (i + 1)
            permu(numeros, i + 1);
            
            // 3. Backtracking (Deshacer Swap): Restaura el array a su estado anterior
            swap(numeros, i, j); 
        }
    }

    // Función auxiliar para intercambiar dos elementos
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // La función imprimir ahora recibe y maneja un array int[]
    private static void imprimir(int[] numeros) {
        // Usa Arrays.toString para una impresión limpia y clara
        System.out.println(Arrays.toString(numeros).replace('[', '(').replace(']', ')'));
    }
}