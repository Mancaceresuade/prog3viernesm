import java.util.List;

public class GameScoreTest {
    public static void main(String[] args) {
        // Crear el árbol AVL para el ranking de jugadores
        AVL<AVL.Jugador> ranking = new AVL<>();
        
        // Insertar jugadores con sus puntajes
        System.out.println("=== SISTEMA DE RANKING GAMESCORE ===");
        System.out.println("Insertando jugadores...\n");
        
        ranking.insertar(new AVL.Jugador("Carlos", 1500));
        ranking.insertar(new AVL.Jugador("Ana", 1800));
        ranking.insertar(new AVL.Jugador("Luis", 1200));
        ranking.insertar(new AVL.Jugador("María", 2000));
        ranking.insertar(new AVL.Jugador("Pedro", 1600));
        ranking.insertar(new AVL.Jugador("Sofia", 1400));
        ranking.insertar(new AVL.Jugador("Juan", 1700));
        ranking.insertar(new AVL.Jugador("Elena", 1900));
        ranking.insertar(new AVL.Jugador("Roberto", 1100));
        ranking.insertar(new AVL.Jugador("Carmen", 1300));
        
        System.out.println("Ranking completo (ordenado por puntaje descendente):");
        ranking.inOrder();
        System.out.println();
        
        // Prueba 1: Buscar los 3 mejores jugadores en el rango [1400, 1800]
        System.out.println("=== PRUEBA 1 ===");
        System.out.println("Buscando los 3 mejores jugadores en el rango [1400, 1800]:");
        List<AVL.Jugador> resultado1 = ranking.buscarMejoresJugadoresEnRango(1400, 1800, 3);
        for (AVL.Jugador jugador : resultado1) {
            System.out.println("- " + jugador);
        }
        System.out.println();
        
        // Prueba 2: Buscar los 5 mejores jugadores en el rango [1200, 1600]
        System.out.println("=== PRUEBA 2 ===");
        System.out.println("Buscando los 5 mejores jugadores en el rango [1200, 1600]:");
        List<AVL.Jugador> resultado2 = ranking.buscarMejoresJugadoresEnRango(1200, 1600, 5);
        for (AVL.Jugador jugador : resultado2) {
            System.out.println("- " + jugador);
        }
        System.out.println();
        
        // Prueba 3: Buscar los 2 mejores jugadores en el rango [1900, 2100]
        System.out.println("=== PRUEBA 3 ===");
        System.out.println("Buscando los 2 mejores jugadores en el rango [1900, 2100]:");
        List<AVL.Jugador> resultado3 = ranking.buscarMejoresJugadoresEnRango(1900, 2100, 2);
        for (AVL.Jugador jugador : resultado3) {
            System.out.println("- " + jugador);
        }
        System.out.println();
        
        // Prueba 4: Comparar con el método in-order optimizado
        System.out.println("=== PRUEBA 4 - COMPARACIÓN ===");
        System.out.println("Usando método in-order optimizado para rango [1400, 1800], k=3:");
        List<AVL.Jugador> resultado4 = ranking.buscarMejoresJugadoresEnRangoInOrder(1400, 1800, 3);
        for (AVL.Jugador jugador : resultado4) {
            System.out.println("- " + jugador);
        }
        System.out.println();
        
        // Prueba 5: Rango sin jugadores
        System.out.println("=== PRUEBA 5 ===");
        System.out.println("Buscando jugadores en el rango [3000, 4000] (rango vacío):");
        List<AVL.Jugador> resultado5 = ranking.buscarMejoresJugadoresEnRango(3000, 4000, 3);
        if (resultado5.isEmpty()) {
            System.out.println("No se encontraron jugadores en este rango.");
        } else {
            for (AVL.Jugador jugador : resultado5) {
                System.out.println("- " + jugador);
            }
        }
        System.out.println();
        
        // Análisis de complejidad
        System.out.println("=== ANÁLISIS DE COMPLEJIDAD ===");
        System.out.println("1. ¿Por qué un AVL es adecuado para este problema?");
        System.out.println("   - Balance automático: garantiza altura O(log n)");
        System.out.println("   - Búsqueda eficiente: O(log n) en el peor caso");
        System.out.println("   - Ordenamiento natural: facilita búsquedas por rango");
        System.out.println("   - Flexibilidad: permite implementar recorridos personalizados");
        System.out.println();
        
        System.out.println("2. Complejidad de la búsqueda:");
        System.out.println("   - Tiempo para encontrar el rango: O(log n + k)");
        System.out.println("   - Tiempo para obtener k jugadores: O(k)");
        System.out.println("   - Complejidad total: O(log n + k)");
        System.out.println("   - Donde n = número total de jugadores, k = jugadores solicitados");
        System.out.println();
        
        System.out.println("3. Optimizaciones implementadas:");
        System.out.println("   - No se visitan nodos fuera del rango [p_min, p_max]");
        System.out.println("   - Parada temprana cuando se encuentran k jugadores");
        System.out.println("   - Priorización de subárboles según el rango");
        System.out.println("   - Recorrido in-order optimizado para ordenamiento natural");
    }
}

