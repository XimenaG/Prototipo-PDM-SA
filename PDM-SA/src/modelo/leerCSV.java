/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public final class leerCSV {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    ListaI Ingredientes;

    public leerCSV(ListaI ingredientes, String ruta) throws FileNotFoundException {
        this.Ingredientes = ingredientes;
        leerIngredientes(ruta);
        //leerRecetas();

    }

    public leerCSV() {
        this.Ingredientes = new ListaI();
    }

    public void leerIngredientes(String ruta) throws FileNotFoundException {

        if (!ruta.equals("")) {
            CsvReader reader = new CsvReader(ruta);
            reader.setDelimiter(';');
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
                    Ingredientes.anadir(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            reader.close();
        }
    }
}
