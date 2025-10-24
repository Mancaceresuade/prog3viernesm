import java.util.ArrayList;
public class App {
    static int cantidadCombinaciones = 0;
    static int optimo = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        int[] proyectos = {4,5,3,2,1,3,3};
        int empleados = 3; // emp 0  4y3  emp 1 5y2  emp 2 3,3y1  todos maximo 7 
        // cantidad de combinciones posibles: 3**7  = 2187
        ArrayList<Integer> com_acutal = new ArrayList<>();
        optenerOptimo(proyectos,empleados,com_acutal,0);
        System.out.println("Cantidad de combinaciones "+cantidadCombinaciones);
        System.out.println("Optimo "+ optimo);
        System.out.println(resultado);
    }
    private static void optenerOptimo(int[] proyectos, int empleados, ArrayList<Integer> com_acutal, int i) {
        int cantidaHoras = calcularHorasDeCombinacion(proyectos,empleados,com_acutal);
        // caso base
        if(proyectos.length==i){
            // Filtro por fuerza bruta
            if(cantidaHoras<optimo){
                resultado.clear();
                resultado.add(new ArrayList<>(com_acutal));
                optimo = cantidaHoras;
            } else if (cantidaHoras == optimo) {
                // optimo posible
                resultado.add(new ArrayList<>(com_acutal));
            }
            // System.out.println(com_acutal);
            cantidadCombinaciones++;
            // {0,0,0,0,0,0,0}  primera vez  21 horas a empleado 0
            // {1,0,1,0,2,2,2}  un optimo  7
            // {2,2,2,2,2,2,2}  ultima combinacion, todos a empleado 2
            return;
        }
        // ramificacion
        for (int j = 0; j < empleados; j++) {
            com_acutal.add(j);
            // poda
            if(cantidaHoras > optimo) {
                com_acutal.remove(com_acutal.size()-1);
                continue;
            }
            optenerOptimo(proyectos, empleados, com_acutal, i+1);
            com_acutal.remove(com_acutal.size()-1); // TRUCO back
        }
    }
    private static int calcularHorasDeCombinacion(int[] proyectos, int empleados, ArrayList<Integer> com_acutal) {
        // {4,5,3,2,1,3,3}  proyectos 
        // {0,0,0,0,0,0,0}  primera vez  21 horas a empleado 0
        // {1,0,1,0,2,2,2}  un optimo  7    emp 0 5+2=7  emp 1 4+3=7  emp 2 1+3+3=7
        // [0,1,2,1,2,0,2]  emp 0 4+3  emp 1 5+2  emp 2 3+1+3      7
        // {2,2,2,2,2,2,2}  ultima combinacion, todos a empleado 2
        // agrupar por empleado
        int[] horasProyectoPorEmpleado = new int[empleados];
        for (int i = 0; i < com_acutal.size(); i++) {
            horasProyectoPorEmpleado[com_acutal.get(i)] += proyectos[i];
        }
        // optener maximo horas proyecto por empleado
        int maximoDeProyectos = 0;
        for (int i = 0; i < horasProyectoPorEmpleado.length; i++) {
            if(horasProyectoPorEmpleado[i]>maximoDeProyectos)
                maximoDeProyectos = horasProyectoPorEmpleado[i];
        }
        return maximoDeProyectos;
    }
}
