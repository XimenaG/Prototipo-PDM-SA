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
    
    private Archivo Archivo;
    

    public Control() {
        this.Archivo = new Archivo();
    }
    public void Actualizar(DefaultTableModel modelo){
        this.Archivo.setMatrizDeTransicion( new Matriz(modelo));
        //this.Archivo.getMatrizDeTransicion().setNcol(estados);
        //this.Archivo.getMatrizDeTransicion().setNren(estados);
        //this.Archivo.getMatrizDeTransicion().setDatos(modelo); 
    }
   
    public void guardarMatrizTranscision(DefaultTableModel defaultTableModel) {
        this.Archivo.EscribirMatrizTranscision(defaultTableModel);
        System.out.println("entro al control");
    }
    
    
    
}
