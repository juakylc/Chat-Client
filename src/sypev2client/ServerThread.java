/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sypev2client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Juaky
 */
/**
 *
 * @author Juaky
 */
public class ServerThread implements Runnable {

    TextArea textArea;

    public ServerThread(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            //Asocio el socket al puerto 5010
            DatagramSocket socket = new DatagramSocket(6011);
            //Declaramos un String en el que almacenaremos el mensaje que nos mande el cliente
            String mensaje;
            do {
                //Construyo datagrama a recibir
                byte[] bufer = new byte[1024];
                DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
                //Recibo datagrama
                socket.receive(recibo);
                //Obtengo el paquete en String
                String paquete = new String(recibo.getData());
                //Obtengo el mensaje
                mensaje = paquete.split(": ")[1];

                System.out.println("Contenido del Paquete: " + paquete);
                System.out.println("=============================");

                //Lo escribimos en el textArea
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.setText(textArea.getText() + "\n" + paquete);
                    }
                });

                //Se cerrara el servidor cuando le mandemos STOP
            } while (!mensaje.equals("STOP"));
            //Cerramos el socket
            socket.close();
        } catch (SocketException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
