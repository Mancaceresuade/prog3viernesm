import java.util.ArrayList;

public class MochilaNoche {
    final static int capacidadMochila = 10;
    static int mejorSolucion = 0;
    static ArrayList<ObjetoMochila> mejorCombinacion = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<ObjetoMochila> objetos = new ArrayList<>();
        cargarObjetos(objetos);
        // ordenar
        ArrayList<ObjetoMochila> com_actual = new ArrayList<>();
        combinar(objetos,com_actual,0);
        System.out.println(mejorSolucion);
        System.out.println(mejorCombinacion);
    }

    private static void combinar(ArrayList<ObjetoMochila> objetos, ArrayList<ObjetoMochila> com_actual, int i) {
        // poda
        int peso_actual = com_actual.stream().mapToInt(n -> n.peso).sum();
        int valor_actual = com_actual.stream().mapToInt(n -> n.valor).sum();
        if(peso_actual > capacidadMochila) return;
        // caso base
        if(i == objetos.size()) {
            if(valor_actual > mejorSolucion) { // no es poda es filtro
                mejorSolucion = valor_actual;
                mejorCombinacion = new ArrayList<>(com_actual);
            }
            //com_actual.forEach(obj -> System.out.print(obj+" "));
            //System.out.println(" ");
            return;
        }
        // cota  para poda y optimizacion
        // ramificacion
        com_actual.add(objetos.get(i));
        combinar(objetos, com_actual, i+1);
        com_actual.remove(com_actual.size()-1); // truco backtring
        combinar(objetos, com_actual, i+1);
    }

    private static void cargarObjetos(ArrayList<ObjetoMochila> objetos) {
        objetos.add(new ObjetoMochila("A", 40, 2));
        objetos.add(new ObjetoMochila("B", 30, 5));
        objetos.add(new ObjetoMochila("C", 50, 10));
    }    
}

class ObjetoMochila {
    String desc;
    int valor;
    int peso;
    public ObjetoMochila(String desc, int valor, int peso) {
        this.desc = desc;
        this.valor = valor;
        this.peso = peso;
    }
    
}
