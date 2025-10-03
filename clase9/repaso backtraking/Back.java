import java.util.ArrayList;

public class Back {

	public static void main(String[] args) {
		int[] numeros = {1,2,3};
		ArrayList<Integer> comb_actual = new ArrayList<>();
		int objetivo = 2;  // mostrar solo combinacion que sumen tres
		combina(numeros,0, comb_actual,objetivo,0);
	}

	private static void combina(int[] numeros, int i, ArrayList<Integer> comb_actual,
			int objetivo, int suma) {
		if(suma>objetivo) {
			System.out.println("poda "+comb_actual.size());
			return;  // poda			
		}
		if(numeros.length==i) {
			if(suma==objetivo) { // filtro
				comb_actual.stream().forEach(e -> System.out.print(e+" "));
				System.out.println(" ");
			}
			return;
		}
		comb_actual.add(numeros[i]);
		combina(numeros, i+1, comb_actual, objetivo, suma+numeros[i]);
		// truco back
		comb_actual.remove(comb_actual.size()-1);
		combina(numeros, i+1, comb_actual, objetivo, suma);
		
	}
	
}
