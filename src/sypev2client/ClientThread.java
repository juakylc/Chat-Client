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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Juaky
 */
public class ClientThread implements Runnable {

    TextArea textArea;
    String text;
    String name;

    public ClientThread(TextArea textArea, String text, String name) {
        this.textArea = textArea;
        this.text = text;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //Puerto al que enviaremos el mensaje
            int port = 5010;
            //Ip del servidor
            String me = "127.0.0.1";
            //Instanciamos la InetAddress de destino
            InetAddress destino = InetAddress.getByName("localhost");
            //Crearemos el mensaje que enviaremos al servidor
            String Saludo = name + ": " + text;
            //Lo codificamos a bytes para enviarlo
            byte[] mensaje = Saludo.getBytes();
            //Construyo el datagrama a enviar
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
            //Instanciamos el puerto local que no tiene que ver nada con el destino
            DatagramSocket socket = new DatagramSocket(6010);

            //Envio datagrama a destino y puerto
            socket.send(envio);
            //Cierro socket
            socket.close();
        } catch (SocketException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
