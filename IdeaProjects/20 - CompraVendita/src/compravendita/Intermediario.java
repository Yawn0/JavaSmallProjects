package compravendita;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Intermediario {

    public static void main(String[] args) {
        try {

            ServerSocket oServerSocket = new ServerSocket(2345);

            while(true){

                Socket oSocket = oServerSocket.accept();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
