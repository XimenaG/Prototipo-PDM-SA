/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class escribirCSV {

    JFileChooser archivo;

    public escribirCSV() {
    }

    /**
     * @param args
     */
    public void EscribirMatrizTranscision(DefaultTableModel LI) {

        archivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
        archivo.setFileFilter(filtro);
        archivo.showSaveDialog();
        
        File guarda = archivo.getSelectedFile();
        String ruta = "";
        if (guarda != null) {
            /*guardamos el archivo y le damos el formato directamente,
             * si queremos que se guarde en formato csv lo definimos como .doc*/
            ruta = guarda+"";
            System.out.println(guarda);
            boolean alreadyExists = new File(ruta).exists();
            if (alreadyExists) {
                File ficheroUsuarios = new File(ruta);
                ficheroUsuarios.delete();
            }
        }
        CsvWriter writer=null;
 try {
         writer = new CsvWriter(new FileWriter(ruta, true), ';');
            for (int i = 0; i < LI.tamano(); i++) {
                writer.write(LI.getDato(i).getCodigo() + "");
                writer.write(LI.getDato(i).getDescripcion());
                writer.write(LI.getDato(i).getUnidaddemedida());
                writer.write(Double.parseDouble(LI.getDato(i).getCantidad()+"")+"");
                writer.write(LI.getDato(i).getMarca() + "");
                writer.write(LI.getDato(i).getCostoactual() + "");
                writer.write(LI.getDato(i).isEstado() + "");
                writer.endRecord();
            }

        } catch (IOException e) {

// TODO Auto-generated catch block
            e.printStackTrace();

        } finally {

            writer.close();

        }

    }
}
