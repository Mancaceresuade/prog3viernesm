import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        /*
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(8));
         */
        HashMap<Integer,Long> map = new HashMap<>();
        System.out.println(fibonacciOptimizado(5000, map));

    }

    private static Long fibonacciOptimizado(int numero, HashMap<Integer,Long> map) {
        if(map.containsKey(numero)) return map.get(numero); // 2   porque busqueda en hash es O(1)
        if(numero==0) return 0L; // 1
        if(numero==1) return 1L; // 1
        Long resultado = fibonacciOptimizado(numero-1,map)+fibonacciOptimizado(numero-2,map); // n
        map.put(numero, resultado); // 1
        return resultado; // 1
    }// no se puede usar metod de sustraccion porque es algoritmo mixto (recursivo e iterativo)
    //  f(n) es O(n)


    // ejercicio desafio, mejorar la complejidad de fibonacci recursivo, 


    private static int fibonacci(int numero) {
        System.out.println("Calculando fibonacci");
        if(numero==0) return 0;
        if(numero==1) return 1;
        return fibonacci(numero-1)+fibonacci(numero-2);
    }// sustraccion, a=2  b=1  K=0    
    // caso 3   O(a**(n div b))   =>   O(2**(n div 1))  => O(2**n)

    // ejercicio en clase, implementar fibonacci iterativo, calcular complejidad
    public static void fibonacciIterativo(int n) {

        if (n <= 1) {
            System.out.println("Fibonacci: " + n);
            return;
        } // 2

        int a = 0, b = 1, c = 0; // 3
        for (int i = 2; i <= n; i++) { // 1 + n-1 + n-1
            System.out.println("Calculando fibo iterativo");
            c = a + b; // 2 n-1
            a = b; // n -1
            b = c; // n -1
        }

        System.out.println("Fibonacci: " + b); // 1
    } // f(n) = 1 + 6n  =>   O(n)    para c= 7  y desde n0 mayor a 3





}
