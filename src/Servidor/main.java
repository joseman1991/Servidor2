/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.util.Scanner;


public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String puerto;
        String numeroClientes;
        Scanner entrada = new Scanner(System.in);
        int PUERTO=0;
        int N=0;

        do {
            System.out.println("Ingrese el puerto");
            puerto = entrada.next();
            if (!validarNumero(puerto)) {
                System.out.println("Error de puerto");
            } else {
                PUERTO = Integer.parseInt(puerto);
            }
        } while (!validarNumero(puerto));

        do {
            System.out.println("Ingrese el numero de clientes permitidos");
            numeroClientes = entrada.next();
            if (!validarNumero(numeroClientes)) {
                System.out.println("Error de puerto");
            } else {
                N = Integer.parseInt(numeroClientes);
            }
        } while (!validarNumero(numeroClientes));

        SckServidor serverSocked= new SckServidor();
        serverSocked.setPUERTO(PUERTO);
        serverSocked.setnClientes(N);
        try {
            serverSocked.conectar();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    static boolean validarNumero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
