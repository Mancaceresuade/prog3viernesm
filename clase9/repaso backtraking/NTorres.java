
public class NTorres {
	public static void main(String[] args) {
		int n = 4;
		int[][] tablero = new int[n][n];		
		nTorres(0,n,tablero);
	}

	private static void nTorres(int fila,int n, int[][] tablero ) {
		if(fila==n) {
			imprimir(tablero);
			return;
		}
		for (int c = 0; c < tablero.length; c++) {
			if(valido(tablero,fila,c)) {
				colocarTorre(tablero,fila,c);
				nTorres( fila+1, n, tablero);
				quitarTorre(tablero,fila,c); // back
			}
		}
	}

	private static void quitarTorre(int[][] tablero, int fila, int c) {
		tablero[fila][c] = 0;		
	}

	private static void colocarTorre(int[][] tablero, int fila, int c) {
		tablero[fila][c] = 1;		
	}

	private static boolean valido(int[][] tablero, int fila, int c) {
		// revisar
		// revisar fila
		for (int f = 0; f < tablero.length; f++) {
			if(tablero[f][c]==1) return false;
		}
		// revisar columna
		for (int col = 0; col < tablero[0].length; col++) {
			if(tablero[fila][c]==1) return false;
		}
		return true;
	}

	private static void imprimir(int[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				System.out.print(tablero[i][j]+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
