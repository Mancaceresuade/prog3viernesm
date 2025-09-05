public class Divide {

    public static boolean estaOrdenadaDyV(String s, int ini, int fin) {
        if (ini >= fin) {
            return true;
        }
        int mid = (ini + fin) / 2;
        boolean izq = estaOrdenadaDyV(s, ini, mid);
        boolean der = estaOrdenadaDyV(s, mid + 1, fin);
        // Verificar el punto de unión
        boolean union = (mid < s.length() - 1) ? s.charAt(mid) <= s.charAt(mid + 1) : true;
        return izq && der && union;
    }// metodo = division
    // a = 2 b= 2 k = 0
    // T(n) = 2T(n/2) + O(1)
    // opcion del metodo = tercero
    // O( n^(log b de a) => O(n^(log 2 de 2) => O(n^1) => O(n)
}
