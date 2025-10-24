import java.util.ArrayList;

public class MochilaNocheFernadezMendez {
    static int capacidadMochila = 10;
    static int mejorSolucion = 0;
    static int valor_actual = 0;
    static ArrayList<ObjetoMochila> mejorCombinacion = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<ObjetoMochila> objetos = new ArrayList<>();
        cargarObjetos(objetos);
        // ordenar
        //objetos.sort(Comparator.comparing(null));
        ArrayList<ObjetoMochila> com_actual = new ArrayList<>();
        combinar(objetos,com_actual,0);
        System.out.println(mejorSolucion);
        System.out.println(mejorCombinacion);
    }
    private static void combinar(ArrayList<ObjetoMochila> objetos, ArrayList<ObjetoMochila> com_actual, int i) {
        // poda
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
		if(capacidadMochila < objetos.get(i).peso)return;
		if(mejorSolucion > objetos.get(i).valor) return;
		valor_actual = valor_actual+objetos.get(i).valor;
		capacidadMochila = capacidadMochila - objetos.get(i).peso;
        com_actual.add(objetos.get(i));

        combinar(objetos, com_actual, i+1);
        com_actual.remove(com_actual.size()-1); // truco backtring
//		capacidadMochila = capacidadMochila - objetos.get(com_actual.size()-1).peso;
		valor_actual = valor_actual+objetos.get(com_actual.size()-1).valor;
        combinar(objetos, com_actual, i+1);
    }
    private static void cargarObjetos(ArrayList<ObjetoMochila> objetos) {
        objetos.add(new ObjetoMochila("C", 50, 10));
        objetos.add(new ObjetoMochila("A", 40, 2));
        objetos.add(new ObjetoMochila("B", 30, 5));
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