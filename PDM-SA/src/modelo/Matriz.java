package modelo;

import javax.swing.table.DefaultTableModel;

public final class Matriz {

    private int nren, ncol;
    private double datos[][];

    /**
     * Crea una matriz nula
     *
     */
    public Matriz() {
        this.nren = 0;
        this.ncol = 0;
    }

    /**
     * Crea una matriz con n renglones y m columnas de puros ceros
     *
     * @param n numero de renglones
     * @param m numero de columnas
     *
     */
    public Matriz(int n, int m) {
        inicializa(n, m);
    }

    /**
     * Inicializa las variables para los constructores.
     *
     * @param r int
     * @param c int
     */
    private void inicializa(int r, int c) {
        // Si la matriz es nula reserva memoria.
        if ((nren == 0 && ncol == 0) || this == null) {
            this.nren = r;
            this.ncol = c;

            this.datos = new double[this.nren][this.ncol];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    this.datos[i][j] = 0;
                }
            }
        }
    }

    public Matriz(DefaultTableModel modelo) {
        // crea una matriz con n renglones y m columnas

        nren = modelo.getRowCount();
        ncol = modelo.getColumnCount();

        datos = new double[nren][ncol];
        for (int i = 0; i < nren; i++) {
            for (int j = 1; j < ncol; j++) {
                datos[i][j] = Double.parseDouble(modelo.getValueAt(i, j).toString());
            }
        }
        System.out.println(imprime());
    }

    /**
     * Imprime el contenido en una matriz
     */
    private String imprime() {
        int i, j;
        double aux;
        String res = "";
        if ((nren == 0) && (ncol == 0)) {

            return "No tiene informacion la matriz";
        }

        for (i = 0; i < this.nren; i++) {
            for (j = 0; j < this.ncol; j++) {
                res += Double.parseDouble(redondea(datos[i][j], 4)) + "\t";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Rutina para redondear un numero
     *
     * @param dato valor a redondear
     * @param max tamano del numero a imprimir
     */
    String redondea(double dato, int max) {
        String salida = "", aux = "";
        float a;

        a = (Math.round(dato * 10000)) / 10000.0f;

        aux += a;
        max -= aux.length();
        for (int i = 0; i < max; i++) {
            salida += " ";
        }
        salida += aux;
        return salida;
    }

    private double getDato(int i, int j) {
        return this.datos[i][j];
    }

    /**
     * Algoritmo para multiplicacion de matrices
     *
     * @param b Matriz por la que se multiplica
     * @return resul producto de la matriz que hace el llamado y la matriz b
     */
    public Matriz por(Matriz b) {
        int i, j;

        double suma;

        if (this.ncol == b.nren) {
            Matriz resul = new Matriz(this.nren, b.ncol);
            for (i = 0; i < resul.nren; i++) {
                for (j = 0; j < resul.ncol; j++) {
                    suma = 0;
                    for (int k = 0; k < this.ncol; k++) {
                        suma += this.datos[i][k] * b.datos[k][j];
                    }
                    resul.datos[i][j] = suma;

                }
            }
            return resul;
        } else {
            System.out.println("Matrices con diferente tamano");
        }
        return null;
    }

    public Matriz inversa() {
        Matriz resul = this;
        if (resul.nren == resul.ncol && this != null) {
            int n = resul.nren;
            int k, j, i;
            for (k = 0; k < n; k++) {
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        if ((i != k) && (j != k)) {
                            resul.datos[i][j] -= (resul.datos[i][k] * resul.datos[k][j])
                                    / resul.datos[k][k];
                        }
                    }
                }

                for (j = 0; j < n; j++) {
                    if (j != k) {
                        resul.datos[k][j] = -resul.datos[k][j] / resul.datos[k][k];
                    }
                }

                for (i = 0; i < n; i++) {
                    if (i != k) {
                        resul.datos[i][k] = resul.datos[i][k] / resul.datos[k][k];
                    }
                }

                resul.datos[k][k] = 1 / resul.datos[k][k];
            }
            return resul;
        } else {
            System.out.println("no se pudo");
        }
        return null;
    }

    /**
     * Metodo para poner un dato en la matriz
     *
     * @param i renglon
     * @param j columna
     * @param d dato
     */
    public void inserta(int i, int j, double d) {
        this.datos[i][j] = d;
    }

    /**
     * Copia una matriz en otra
     *
     * @param A matriz a copiar en la matriz que hace el llamado
     */
    public static Matriz igual_a(Matriz A) {
        Matriz resul = new Matriz(A.nren, A.ncol);
        for (int i = 0; i < A.nren; i++) {
            for(int j = 0; j < A.ncol; j++) {
                resul.datos[i][j] = A.datos[i][j];
            }
        }
        return resul;
    }

    public double[][] getDatos() {
        return datos;
    }

    public void setDatos(double[][] datos) {
        this.datos = datos;
    }

    public void guardarMatrizTranscisionArchivo(DefaultTableModel defaultTableModel) {
            
    }
    
}