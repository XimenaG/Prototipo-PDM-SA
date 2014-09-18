/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
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
    public void EscribirMatrizTranscision(DefaultTableModel MT) {
 System.out.println("lego al modelo");
        this.ruta = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        this.ruta.setFileFilter(filtro);
        System.out.println("mostrar");
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
            writer = new CsvWriter(new FileWriter(archivo.getName(), true), ';');
            
           for(int i = 0 ; i <= MT.getRowCount();i++ ){
               for(int j = 1; j <= MT.getColumnCount();j++){
                writer.write(MT.getValueAt(i,j).toString()+ "");
                
               }
               writer.endRecord();
            }

        } catch (IOException e) {

// TODO Auto-generated catch block
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

   
}
