public class Main {
    static int numFecha = 1; // contador global de fechas
    public static void main(String[] args) {
        String[] equipos = {"River", "Boca", "Racing", "Velez"};
        generarFixture(equipos, 0, equipos.length);
    }
    public static void generarFixture(String[] eq, int ini, int fin) {
        int n = fin - ini;
        if (n == 2) { // caso base
            System.out.println("Fecha " + (numFecha++));
            System.out.println(eq[ini] + "-" + eq[ini + 1]);
            return;
        }
        int mitad = n / 2;
        // busqueda binaria
        generarFixture(eq, ini, ini + mitad);
        generarFixture(eq, ini + mitad, fin);

        // fechas a cruzar
        for (int r = 0; r < mitad; r++) {
            System.out.println("Fecha " + (numFecha++));
            for (int i = 0; i < mitad; i++) {
                System.out.println(eq[ini + i] + "-" + eq[ini + mitad + (i + r) % mitad]);
            }
        }
    }
}
