import java.util.*;

public class Ejercicios {

    public static List<List<String>> fixtureDyC(String[] equipos) {
        if (equipos == null || equipos.length < 2) return Collections.emptyList();
        int n = equipos.length;
        if ((n & (n - 1)) != 0) throw new IllegalArgumentException("n debe ser potencia de 2");
        return construir(equipos, 0, n);
    }

    // Divide & Conquista puro y recursivo:
    // 1) Caso base len==2: una fecha con ese único partido.
    // 2) Recursión en mitades (left/right) => m-1 fechas internas combinadas en paralelo.
    // 3) Cruces entre mitades: se generan con una función recursiva auxiliar (sin bucles externos).
    private static List<List<String>> construir(String[] eq, int inicio, int len) {
        if (len == 2) {
            List<List<String>> base = new ArrayList<>();
            base.add(List.of(eq[inicio] + " - " + eq[inicio + 1]));
            return base;
        }

        int m = len / 2;
        // 1) Resolver recursivamente cada mitad
        List<List<String>> left  = construir(eq, inicio, m);        // m-1 fechas
        List<List<String>> right = construir(eq, inicio + m, m);    // m-1 fechas

        // 2) Combinar fechas internas en paralelo de forma recursiva
        List<List<String>> res = new ArrayList<>();
        combinarInternasRec(res, left, right, 0, m - 1); // O(n^2)

        // 3) Agregar fechas de cruces (m fechas) con rotación, de forma recursiva
        agregarCrucesRec(res, eq, inicio, m, 0);

        return res; // total = (m-1)+m = len-1
    }  // a = 2 b = 2 k = 2   => division => O(n^k)

    // Combina las fechas internas de izquierda y derecha recursivamente (índice f de 0..m-2)
    private static void combinarInternasRec(List<List<String>> res,
                                            List<List<String>> left,
                                            List<List<String>> right,
                                            int f, int limite) {
        if (f >= limite) return;
        List<String> fecha = new ArrayList<>(left.get(f).size() + right.get(f).size());
        fecha.addAll(left.get(f));  // k=1
        fecha.addAll(right.get(f));
        res.add(fecha);
        combinarInternasRec(res, left, right, f + 1, limite);
    } // a= 1 b = 1 k = 1  => sustraccion, O(n^(1+1)) => O(n^2)

    // Agrega las m fechas de cruces entre mitades recursivamente (rotación)
    private static void agregarCrucesRec(List<List<String>> res, String[] eq, int inicio, int m, int r) {
        if (r >= m) return;
        List<String> fecha = new ArrayList<>(m);
        // construir una fecha de cruces usando otra recursión en i
        construirFechaCrucesRec(fecha, eq, inicio, m, r, 0);
        res.add(fecha);
        agregarCrucesRec(res, eq, inicio, m, r + 1);
    }

    // Construye recursivamente los m partidos de la fecha de cruce r (i de 0..m-1)
    private static void construirFechaCrucesRec(List<String> fecha, String[] eq, int inicio, int m, int r, int i) {
        if (i >= m) return;
        String local  = eq[inicio + i];
        String visita = eq[inicio + m + ((i + r) % m)];
        fecha.add(local + " - " + visita);
        construirFechaCrucesRec(fecha, eq, inicio, m, r, i + 1);
    }

    // Helper para imprimir con formato "Fecha k"
    public static void imprimirFixture(List<List<String>> fixture) {
        for (int f = 0; f < fixture.size(); f++) {
            System.out.println("Fecha " + (f + 1));
            System.out.println(String.join("  ", fixture.get(f)));
        }
    }

    // Demo rápida
    public static void main(String[] args) {
        String[] equipos = {"a", "b", "c", "d"};
        List<List<String>> fixture = fixtureDyC(equipos);
        imprimirFixture(fixture);
        // Esperado:
        // Fecha 1
        // a - b  c - d
        // Fecha 2
        // a - c  b - d
        // Fecha 3
        // a - d  b - c
    }
}
 