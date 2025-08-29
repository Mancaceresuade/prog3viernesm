import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ejercicio {
    public static void main(String[] args) {
        

        Map<Integer,String> capitulos = new HashMap<>();

        Map<Integer,String> capitulos1 = new TreeMap<>();
        
        AVL<Integer> avl = new AVL<>();
        avl.insertar(10);
        avl.inOrder();

    }
}
