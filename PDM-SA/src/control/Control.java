/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author cristhian
 */
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import javax.swing.table.DefaultTableModel;
import modelo.*;
public class Control {
    
    private Matriz MatrizTransicion;
    private MatrizCostos Costos;
    private leerCSV Leerarchivo;
    private escribirCSV EscribirArchivo;

    public Control() {
        this.MatrizTransicion = new Matriz();
        this.Costos = new MatrizCostos();
        //this.Leerarchivo = new leerCSV();
        //this.EscribirArchivo = new escribirCSV();
    }
    public void cargarDatos(DefaultTableModel modelo){
        this.MatrizTransicion = new Matriz(modelo); 
    }

    public void guardarMatrizTranscision(DefaultTableModel defaultTableModel) {
        this.MatrizTransicion.guardarMatrizTranscisionArchivo(defaultTableModel);
    }
    
    
    
}
