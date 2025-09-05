class App{
    public static void main(String[] args) {
        char[] secuencia = {'a','b','c','d','e','f','g'};
        System.out.println(solucionRecursiva(secuencia));
    }
    public static boolean solucionRecursiva(char[] secuencia) {
        return solucionRecursivaSimple(secuencia, 0);
    }
    public static boolean solucionRecursivaSimple(char[] secuencia, int i) {
        if (i == secuencia.length - 1) return true;   // caso base: llegamos al final
        if (secuencia[i] > secuencia[i + 1]) return false; // rompe el orden
        return solucionRecursivaSimple(secuencia, i + 1);  // llamamos con el siguiente índice
    } // T(n) = T(n-1) + O(1)
    // metodo = sustraccion
    // a= 1  b= 1 k= 0
    // segunda opcion , O(n^(0+1)  =>  O(n)

    public static boolean solucionIterativa(char[] secuencia){
        for (int i=0; i<secuencia.length-1;i++){ // 1 + 3(n+1) + 1 = 3n + 5
            if(secuencia[i]>secuencia[i+1]){ // 3n
                return false; // 1
            }
        }
        return true; //1
    } // T(n) = 3n + 3n + 7  = 6n+7
    // T(n) <= c g(n) para n>=n0
    // 6n + 7 <= c n
    // 6n/n + 7/n <= 7n/n
    // 6 + 7/n <= 7
    // para n0=1 cumple ? 13 <= 7 no cumple
    // ...
    // para n0=7 cumple ? 6 + 1 <= 7 si cumple
    // f(n) es O(n) desde n0=7 y c=7

    // sumar todos los numeros , para 6= 1+2+3+4+5+6 = 21
    public void sumar(int n) {
        sumarRec(n);
    }

    public void sumarRec(int n) {
        if (n == 1) {
            System.out.println(1);
            return;
        }
        sumarRec(n - 1);
        System.out.println(n + (n - 1));
    } // T(n) = a T(n-1) + O(1) //  O(1)  es k=0
    // metodo = sustraccion
    // a=1 b=1 k=0

    private void SumerRec(int n, int inicio,int fin) {
        if (inicio == fin) {
            System.out.println(inicio);
            return;
        }
        int mid = (inicio + fin) / 2; // grado 0
        SumerRec(n, inicio, mid);
        SumerRec(n, mid + 1, fin);
        System.out.println("suma " + inicio + " + " + fin + " = " + (inicio + fin));
    } // T(n) = 2T(n/2) + O(1)
    // metodo = division
    // a=2 b=2 k=0
    // caso 3 del metodo a > b^k  =>  2 > 2^0
    // O(n ^(log b de a)) => O(n^(log 2 de 2)) => O(n^1) => O(n)
}








}