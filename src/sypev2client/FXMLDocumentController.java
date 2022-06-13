/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sypev2client;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Juaky
 */
public class FXMLDocumentController implements Initializable {

    //Declaramos los hilos de cliente y servidor
    Thread client;
    Thread server;

    //Declaramos la variable nombre donde guardaremos el nombre del usuario
    String name;

    //Instanciamos la variable scanner
    Scanner sc = new Scanner(System.in);

    //Declaramos los elementos del FXML
    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    private void handleButtonAction(ActionEvent event) throws InterruptedException {
        System.out.println("Enviando mensaje... \n");
        String texto = textField.getText();

        //Inicializamos el hilo del cliente y le damos start
        client = new Thread(new ClientThread(textArea, texto, name));
        client.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando... \n");

        //Pedimos al usuario que introduzca su nombre de usuario
        System.out.println("Introduce tu nombre: ");
        name = sc.nextLine();

        //Inicializamos el hilo y le damos a start
        server = new Thread(new ServerThread(textArea));
        server.start();

    }

}
