public class Torneo {
    public static void generarFixture(String[] equipos) {
        int n = equipos.length;
        if ((n & (n - 1)) != 0) { // solo potencias de 2
            System.out.println("El número de equipos debe ser potencia de 2.");
            return;
        }

        int jornadas = n - 1;
        int partidosPorJornada = n / 2;

        String[] rotacion = new String[n - 1];
        for (int i = 0; i < n - 1; i++) {
            rotacion[i] = equipos[i + 1];
        }

        for (int fecha = 0; fecha < jornadas; fecha++) {
            System.out.println("Fecha " + (fecha + 1));

            System.out.println(equipos[0] + " - " + rotacion[n - 2]);

            for (int i = 0; i < partidosPorJornada - 1; i++) {
                System.out.println(rotacion[i] + " - " + rotacion[n - 3 - i]);
            }

            String ultimo = rotacion[n - 2];
            for (int i = n - 2; i > 0; i--) {
                rotacion[i] = rotacion[i - 1];
            }
            rotacion[0] = ultimo;

            System.out.println();
        }
    }
}
