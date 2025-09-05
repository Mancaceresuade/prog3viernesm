public class Partidos {

    void generarPartidos(String[] equipos) {
        if (equipos == null || equipos.length < 2) return; // con uno no se puede generar partidos

        int n = equipos.length;
        for (int i = 0; i < n - 1; i++) { // 1 + 3(n+1) + n = 1+3n+3+n = 4n + 5
            for (int j = i + 1; j < n; j++) { // gauss n(n-1)/2
                System.out.println(equipos[i] + " - " + equipos[j]);
            }
        }
    }
}
// prueba de escritorio
// i = 0
// j = 1   river,boca
// j = 2   river,racing
// j = 3   river,velez
// i = 1
// j = 2   boca,racing
// j = 3   boca,velez
// i = 2
// j = 3   racing,velez
// i = 3

