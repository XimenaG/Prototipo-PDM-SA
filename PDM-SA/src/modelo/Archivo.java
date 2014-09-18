/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import vista.PanelMDI;

/**
 *
 * @author cristhian
 */
public class Archivo {

    private Matriz MatrizDeTransicion;
    private MatrizCostos MatrizCostos;
    private JFileChooser ruta;

    public Archivo() {
        MatrizDeTransicion = new Matriz();
    }

    public Archivo(DefaultTableModel MatrizDeTransicion) {
        this.MatrizDeTransicion = new Matriz(MatrizDeTransicion);

    }

    public Matriz getMatrizDeTransicion() {
        return MatrizDeTransicion;
    }

    public void setMatrizDeTransicion(Matriz MatrizDeTransicion) {
        this.MatrizDeTransicion = MatrizDeTransicion;
    }

    public MatrizCostos getMatrizCostos() {
        return MatrizCostos;
    }

    public void setMatrizCostos(MatrizCostos MatrizCostos) {
        this.MatrizCostos = MatrizCostos;
    }

    public JFileChooser getArchivo() {
        return ruta;
    }

    public void setArchivo(JFileChooser archivo) {
        this.ruta = archivo;
    }

    /**
     * @param args
     */
    public void EscribirArchivo(DefaultTableModel MT) {

        this.ruta = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        this.ruta.setFileFilter(filtro);

        this.ruta.showSaveDialog(PanelMDI.jDesktopPane);
        File archivo = ruta.getSelectedFile();

        if (archivo != null) {
            /*guardamos el archivo y le damos el formato directamente,
             * si queremos que se guarde en formato csv lo definimos como .doc*/

            System.out.println(archivo.getName());
            boolean alreadyExists = new File(archivo.getName()).exists();
            if (alreadyExists) {
                File ficheroUsuarios = new File(archivo.getName());
                ficheroUsuarios.delete();
            }
        }

        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(ruta.getSelectedFile(), false), ';');

            for (int i = 0; i < MT.getRowCount(); i++) {
                for (int j = 1; j < MT.getColumnCount(); j++) {
                    writer.write(MT.getValueAt(i, j).toString() + "");

                }
                writer.endRecord();
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            writer.close();

        }

    }

    public void leerCSV() throws FileNotFoundException {

        if (!this.ruta.equals("")) {
            CsvReader reader = new CsvReader(ruta.getName());
            reader.setDelimiter(';');
            /*
             try {
             // reader.readRecord();
             while (reader.readRecord()) {
             Ingrediente i = new Ingrediente();

             int Codigo = Integer.parseInt(reader.get(0));
             i.setCodigo(Codigo);

             i.setDescripción(reader.get(1));

             i.setUnidaddemedida(reader.get(2));

             i.setCantidad(Double.parseDouble(reader.get(3)));

             i.setMarca(reader.get(4));

             i.setCostoactual(Double.parseDouble(reader.get(5)));

             i.setEstado(Boolean.parseBoolean(reader.get(6)));
                    
             }
             } catch (IOException e) {
             e.printStackTrace();
             }*/

            reader.close();
        }
    }

    public void NuevoArchivo(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion) {
        montarTablaTransicion(jInternalFrameMAtrizTransicion, jTablaTransicion);
    }

    private void montarTablaTransicion(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion) {
        Vector vector = new Vector(18);

        for (int i = 2; i <= 20; i++) {
            vector.add(i - 2, i);
        }
        JComboBox cantidadEstados = new JComboBox(vector);
        JOptionPane.showMessageDialog(PanelMDI.jDesktopPane, cantidadEstados, "Definir Cantidad De Estados,", WIDTH);

        int nEstados = Integer.parseInt(cantidadEstados.getSelectedItem().toString());
        System.out.println(nEstados);
        DefaultTableModel modelo = new DefaultTableModel(matrizInicial(nEstados), vectorEstados(nEstados + 1));

        jTablaTransicion.setModel(modelo);
        jInternalFrameMAtrizTransicion.setVisible(true);


    }

    private Object[] vectorEstados(int nEstados) {
        Object[] vectorEstados = new Object[nEstados];
        vectorEstados[0] = " ";
        for (int i = 1; i < vectorEstados.length; i++) {
            vectorEstados[i] = i - 1;
        }
        return vectorEstados;
    }

    private Object[][] matrizInicial(int nEstados) {

        Object[][] matrizInicial = new Object[nEstados][nEstados + 1];
        for (int i = 0; i < nEstados; i++) {
            for (int j = 0; j < nEstados + 1; j++) {
                matrizInicial[i][j] = 0;
            }
        }
        for (int i = 0; i < nEstados; i++) {
            matrizInicial[i][0] = i;
        }
        return matrizInicial;
    }
}
