/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author cristhian
 */
import javax.swing.JInternalFrame;
import javax.swing.JTable;
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
   
    public void guardarMatrizTransicion(DefaultTableModel Model) {
        this.Archivo.EscribirArchivo(Model);
       
    }

    public void NuevoArchivo(JInternalFrame jInternalFrameMAtrizTransicion, JTable jTablaTransicion) {
        this.Archivo.NuevoArchivo(jInternalFrameMAtrizTransicion,jTablaTransicion);
    }
    
    
    
}
