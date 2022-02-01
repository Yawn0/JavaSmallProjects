package compravendita;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Venditore implements Runnable {

    private final int UDP_PORT;
    private final String SERVER_NAME;
    private final int BUFFER_LENGTH = 1024;

    public enum Prodotti{
        Banana,
        Pera,
        Mela,
        Ciliega,
        Ananas,
        Arancia
    }

    public Venditore(int iPort, String sIntermediario){
        UDP_PORT = iPort;
        SERVER_NAME = sIntermediario;
    }

    @Override
    public void run() {
        new Thread(this::manageRequests).start();
    }

    private void manageRequests(){

        final byte[] abInputData = new byte[BUFFER_LENGTH];

        try {

            DatagramSocket oDatagramSocket = new DatagramSocket();
            DatagramPacket oDatagramPacket = new DatagramPacket(abInputData, abInputData.length);

//            oDatagramSocket.connect(InetAddress.getByName(SERVER_NAME),UDP_PORT);

            while(true){

                oDatagramSocket.receive(oDatagramPacket);

                ByteArrayInputStream oData = new ByteArrayInputStream(oDatagramPacket.getData());
                ObjectInputStream oInputStream = new ObjectInputStream(oData);

                Richiesta oRichiesta = (Richiesta) oInputStream.readObject();

                Random oRandom = new Random();

                if(oRichiesta != null){

                    Risposta oRisposta = new Risposta(oRichiesta, oRandom.nextInt());

                    ByteArrayOutputStream oByteArrayOutput = new ByteArrayOutputStream();
                    ObjectOutputStream oOutputStream = new ObjectOutputStream(oByteArrayOutput);

                    oOutputStream.writeObject(oRisposta);

                    oDatagramPacket = new DatagramPacket(oByteArrayOutput.toByteArray(),
                            oByteArrayOutput.toByteArray().length,
                            InetAddress.getByName(SERVER_NAME),
                            UDP_PORT);

                    oDatagramSocket.send(oDatagramPacket);
                }
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }
}