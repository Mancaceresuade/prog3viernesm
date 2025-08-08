import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    boolean todosPares(int[] arr) {
        boolean rta = true;  // 1
        int aux = arr.length; // 1
        for(int i = 1; i < aux; i++) { // 1 + n +1 + n
            rta = rta && (arr[i]%2)==0; // 5n
        }
        return rta; //  1
    } //f(n) = 7n + 5
    // f(n) <= c g(n)
    // 7n + 5 <= 8n  // busco el termino dominante y sumo uno a la constante
    // 7n/ n + 5/n <= 8 // divido todo por n
    // 7 + 5/n <= 8
    // para n0 = 1 se cumple ? 12 <= 8 no se cumple
    // para n0 = 2 se cumple ? 7 + 2.5 <= 8  no si se cumple
    // para n0 = 3 se cumple ? 7 + 1.66 <= 8 si se cumple
    // ....
    // para n0 = 5 se cumple ? 7 + 1 <= 8 si se cumple
    // f(n) PERTENECE A 0(n)  PARA n0 >= 5 Y c = 8

    public static void main(String[] args) {
        

        /*
        System.out.println(Persona.getSaludo());
        Jugador jugador = new Jugador(1, "", "Paris Saint-Germain", 30);
        jugador.contratar();

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        jugadores.add(new Jugador(2, "Cristiano Ronaldo", "Al Nassr", 7));

        for (Jugador j : jugadores) {
            System.out.println("Jugador: " + j.getNombre() + ", Equipo: " + j.getEquipo() + ", Camiseta: " + j.getNumeroCamiseta());
        }

        jugadores.stream().forEach(j -> {
            System.out.println("Jugador: " + j.getNombre() + ", Equipo: " + j.getEquipo() + ", Camiseta: " + j.getNumeroCamiseta());
        });
        jugadores.stream().filter(j -> j.getNumeroCamiseta() > 10)
                .forEach(j -> System.out.println("Jugador con camiseta > 10: " + j.getNombre()));

        HashMap<Integer, Jugador> mapaJugadores = new HashMap<>();
        mapaJugadores.put(jugador.getId(), jugador);
        mapaJugadores.put(2, new Jugador(2, "Cristiano Ronaldo", "Al Nassr", 7));
        mapaJugadores.forEach((id, j) -> {
            System.out.println("ID: " + id + ", Jugador: " + j.getNombre() + ", Equipo: " + j.getEquipo());
        });
        mapaJugadores.values().stream()
                .filter(j -> j.getEquipo().equals("Al Nassr"))
                .forEach(j -> System.out.println("Jugador en Al Nassr: " + j.getNombre()));
         */    
        /*
        try {
            int numero = 10/0;      
        } catch (Exception e) {
            System.out.println("Intente mas tarde, hubo un error: " + e.getMessage());
        }
        try {
            FileReader fileReader = new FileReader("src/archivo.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
         */
        /*
        int i = 10;
        Integer j = 20;
        System.out.println("Primitive int: " + i);
        System.out.println("Wrapper Integer: " + j);
        double k = 30.5;
        Double l = 40.5;        
        String str = "Hello, World!";
        StringBuilder sb = new StringBuilder("Hello, StringBuilder!");
         */
    }
}
