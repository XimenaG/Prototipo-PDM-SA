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
        inicializa(nren, ncol);
    }

    /**
     * Crea una matriz con n renglones y m columnas de puros ceros
     *
     * @param n numero de renglones
     * @param m numero de columnas
     *
     */
    /**
     * Inicializa las variables para los constructores.
     *
     * @param r int
     * @param c int
     */
    public void inicializa(int r, int c) {
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

        this.nren = modelo.getRowCount();
        this.ncol = modelo.getColumnCount()-1;

        this.datos = new double[this.nren][this.ncol];
        for (int i = 0; i < nren; i++) {
            for (int j = 1; j < ncol; j++) {
                datos[i][j-1] = Double.parseDouble(modelo.getValueAt(i, j).toString());
            }
        }
        System.out.println(nren+""+ncol);
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
            Matriz resul = new Matriz();
            resul.inicializa(this.nren, b.ncol);
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


    public double[][] getDatos() {
        return datos;
    }

    public void setDatos(double[][] datos) {
        this.datos = datos;
    }

    

    public int getNren() {
        return nren;
    }

    public void setNren(int nren) {
        this.nren = nren;
    }

    public int getNcol() {
        return ncol;
    }

    public void setNcol(int ncol) {
        this.ncol = ncol;
    }

    public void guardarMatrizTranscisionArchivo(DefaultTableModel defaultTableModel) {
    }
}