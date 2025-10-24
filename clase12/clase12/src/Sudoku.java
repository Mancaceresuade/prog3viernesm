public class Sudoku {
    public static void main(String[] args) {
        int[][] tablero = {{3,0,1,0,0,0,0,8,9},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0},
                           {0,0,0,0,0,0,0,0,0}};
        resolverSudoku(tablero);
        imprimirTablero(tablero);
    }

    private static boolean resolverSudoku(int[][] tablero) {
        int[] vacia = buscarCeldaVacia(tablero);
        // caso base
        if(vacia == null) return true; // no hay mas celdas vacias -> Sudoku completo
        // ramificacion
        int fila = vacia[0]; // primer posicion fila
        int col = vacia[1];  // segunda posicion columna
        for (int num = 1; num <= 9; num++) {  // probando numeros
            if(esValido(tablero,fila,col,num)) {
                tablero[fila][col] = num;
                if(resolverSudoku(tablero)) return true;
            }
            tablero[fila][col] = 0; // TRUCO  back
        }
        return false; // ningun numero valido -> back
    }

    private static boolean esValido(int[][] tablero, int fila, int col, int num) {
        boolean rta = true;
        // verificar por fila
        for (int i = 0; i < tablero.length; i++) {
            if(num == tablero[fila][i] && i != col) return false;
        }
        // verificar por columna
        for (int i = 0; i < tablero.length; i++) {
            if(num == tablero[i][col] && i != fila) return false;
        }
        // verificar cuadrantes
        int inicioFila = (fila/3) *3 ;
        int inicioCol = (col/3) *3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(tablero[inicioFila+i][inicioCol+j]==num && 
                (i != fila || j != col)) return false;
            }
        } 
        return rta;
    }

    private static int[] buscarCeldaVacia(int[][] tablero) {
        int[] celdaVacia = null;
        // buscando por posicion
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(tablero[i][j] == 0) {
                    /*
                    celdaVacia = new int[2];
                    celdaVacia[0] = i;
                    celdaVacia[1] = j;
                     */
                    return new int[] {i,j};    
                }
            }
        }
        return celdaVacia;
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}    
