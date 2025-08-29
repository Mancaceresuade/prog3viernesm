class App{
    public static void main(String[] args) {
        char[] secuencia = {'a','b','c','d','e','f','g'};
        System.out.println(solucionIterativa(secuencia));
    }
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

}