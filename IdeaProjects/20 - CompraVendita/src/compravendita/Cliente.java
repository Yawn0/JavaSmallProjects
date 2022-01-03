package compravendita;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente implements Runnable {

    @Override
    public void run() {
        try {

            Socket oSocket = new Socket(InetAddress.getLocalHost(),2345);






        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
