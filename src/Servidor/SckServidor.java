/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import historial.Historial;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SckServidor {

    private int PUERTO;
    private int nClientes;
    private final Historial historico;

    public SckServidor() {
        historico = new Historial("historial.log");
    }

    public void conectar() throws IOException {

        historico.escribirArchivo(fechaActual() + " Servidor iniciado: ");

        ServerSocket skServidor = new ServerSocket(PUERTO);
        System.out.println("Escucho el puerto " + PUERTO);
        historico.escribirArchivo(fechaActual() + " Escucho el puerto " + PUERTO + ": ");

        for (int numCli = 1; numCli <= nClientes; numCli++) {
            Socket skCliente = skServidor.accept(); // Crea objeto

            InetAddress inetAddress = skCliente.getInetAddress();
            String nombre = inetAddress.getHostName();
            String IP = inetAddress.getHostAddress();

            System.out.println("Sirvo al cliente " + numCli);
            System.out.println("Nombre " + nombre);
            System.out.println("IP " + IP);
            historico.escribirArchivo(fechaActual() + " Sirvo al cliente: " + numCli);
            historico.escribirArchivo(fechaActual() + " Nombre: " + nombre);
            historico.escribirArchivo(fechaActual() + " IP: " + IP);

            OutputStream aux = skCliente.getOutputStream();
            DataOutputStream flujo = new DataOutputStream(aux);
            flujo.writeUTF("Hola cliente " + numCli);

            historico.escribirArchivo(fechaActual() + " mensaje enviado: " + "Hola cliente " + numCli);
            historico.escribirArchivo("--------------------------------------------------------------------");

            skCliente.close();
        }
        historico.escribirArchivo(fechaActual() + " ya no se pueden atender mas clientes por hoy: ");
        System.out.println("Demasiados clientes por hoy");

    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public void setnClientes(int nClientes) {
        this.nClientes = nClientes;
    }

    private String fechaActual() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd-MMMM-yyyy hh:mm:ss");
        return format.format(new Date());
    }

}
