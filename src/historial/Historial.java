/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package historial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Historial {

    private FileWriter archivo = null;
    private PrintWriter escribir = null;
    private String ruta;

    public Historial(String ruta) {
        try {
            this.ruta = ruta;
            archivo = new FileWriter(this.ruta);
            escribir = new PrintWriter(archivo);
            escribir.close();
        } catch (IOException e) {
            System.out.println("");
        }
    }

    public void escribirArchivo(String linea) throws IOException {
        archivo = new FileWriter(ruta, true);
        escribir = new PrintWriter(archivo);
        escribir.println(linea);
        escribir.close();
    }

}
