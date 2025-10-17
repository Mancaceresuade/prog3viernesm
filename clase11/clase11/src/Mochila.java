import java.util.ArrayList;
import java.util.Comparator;

public class Mochila {
    static int capacidad = 10;
    static int maximoValor = 0;
    static ArrayList<ObjetoMochila> mejorCombinacion = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<ObjetoMochila> objetoMochilas = new ArrayList<>();
        cargarObjetosMochila(objetoMochilas);
        // ordenar para seguir metodo branch and bound
        objetoMochilas.sort(Comparator.comparingInt(objeto -> -1 * objeto.valor/objeto.peso));
        ArrayList<ObjetoMochila> comb_actual = new ArrayList<>();
        combinarMaximarBound(objetoMochilas,comb_actual,0);
        System.out.println("Maximo valor sin superar " + capacidad + " es " + maximoValor);
        System.out.println("Mejor combinacion "+ mejorCombinacion);
    }

    private static void combinarMaximarBound(ArrayList<ObjetoMochila> objetoMochilas, ArrayList<ObjetoMochila> comb_actual, int i) {
        int peso_actual = comb_actual.stream().mapToInt(ObjetoMochila::getPeso).sum();
        int valor_actual = comb_actual.stream().mapToInt(ObjetoMochila::getValor).sum();
        // poda
        if(peso_actual > capacidad) return;
        // caso base
        if(i==objetoMochilas.size()) {
            if(valor_actual > maximoValor) {
                maximoValor = valor_actual;
                mejorCombinacion = new ArrayList<>(comb_actual);
            }
            //comb_actual.stream().forEach(n -> System.out.print(n+" "));
            //System.out.println("");
            return;
        }
        // cotas (bound)
        int cota = valor_actual + calcularCota(objetoMochilas,capacidad-peso_actual,i);
        if( cota < maximoValor) return;

        // ramificacion
        comb_actual.add(objetoMochilas.get(i));
        combinarMaximarBound(objetoMochilas, comb_actual, i+1);
        comb_actual.remove(comb_actual.size()-1);
        combinarMaximarBound(objetoMochilas, comb_actual, i+1);
    }

    private static int calcularCota(ArrayList<ObjetoMochila> objetoMochilas, int capacidadRestante, int i2) {
        // metod0 greedy
        int valorCota = 0;
        for (int j = 0; j < objetoMochilas.size() && capacidadRestante > 0; j++) {
            ObjetoMochila objetoMochila = objetoMochilas.get(j);
            if(objetoMochila.peso <= capacidadRestante) {
                valorCota += objetoMochila.valor;
                capacidadRestante -= objetoMochila.peso;
            } else {
                valorCota += objetoMochila.valor / objetoMochila.peso * capacidadRestante;
                break;
            }
        }
        return valorCota;
    }

    private static void cargarObjetosMochila(ArrayList<ObjetoMochila> objetoMochilas) {
        // datos de prueba
        objetoMochilas.add(new ObjetoMochila("A",40,2));
        objetoMochilas.add(new ObjetoMochila("B",30,5));
        objetoMochilas.add(new ObjetoMochila("C",50,10));

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
    
    public String getDesc() {
        return desc;
    }

    public int getValor() {
        return valor;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return desc + " " + valor + " " + peso;
    }

}
