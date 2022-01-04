package compravendita;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Venditore implements Runnable {


    @Override
    public void run() {
        new Thread(() -> {

            try {

                ServerSocket oServerSocket = new ServerSocket(6789);

                while(true){

                    Socket oSocket = oServerSocket.accept();

                }



            } catch (IOException oException) {
                oException.printStackTrace();
            }

        }).start();
    }
}
