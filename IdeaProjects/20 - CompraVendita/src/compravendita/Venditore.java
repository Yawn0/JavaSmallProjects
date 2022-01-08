package compravendita;

import javax.management.timer.Timer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Venditore implements Runnable {

    private final int UDP_PORT;
    private final String SERVER_NAME;
    private final int BUFFER_LENGTH = 512;
    private ConcurrentLinkedQueue<Richiesta> queRichieste = new ConcurrentLinkedQueue<>();

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
        new Thread(this::getRequests).start();
        new Thread(this::sendResponse).start();
    }

    private void getRequests(){

        final byte[] abInputData = new byte[BUFFER_LENGTH];

        DatagramPacket oDatagramPacket;
        DatagramSocket oDatagramSocket;

        try {

            oDatagramSocket = new DatagramSocket(UDP_PORT,InetAddress.getByName(SERVER_NAME));
            oDatagramPacket = new DatagramPacket(abInputData,abInputData.length);

            while(true){

                oDatagramSocket.receive(oDatagramPacket);

                String sInput = Arrays.toString(oDatagramPacket.getData());
                StringTokenizer oTokenizer = new StringTokenizer(sInput, Risposta.DELIMITER);

                int iIdProdotto = Integer.parseInt(oTokenizer.nextToken());
                int iQuantita = Integer.parseInt(oTokenizer.nextToken());

                Richiesta oRichiesta = new Richiesta(iIdProdotto, iQuantita);

                queRichieste.add(oRichiesta);
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void sendResponse(){

        final byte[] abOutputData = new byte[BUFFER_LENGTH];

        Random oRandom = new Random();
        DatagramPacket oDatagramPacket;
        DatagramSocket oDatagramSocket;

        try {

            oDatagramSocket = new DatagramSocket(UDP_PORT);
            oDatagramPacket = new DatagramPacket(abOutputData,abOutputData.length);

            while(true){

                Richiesta oRichiesta = queRichieste.poll();

                if(oRichiesta != null && (System.currentTimeMillis() - oRichiesta.getTimestamp()) < Timer.ONE_MINUTE){

                    Risposta oRisposta = new Risposta(oRichiesta, oRandom.nextInt(), oRandom.nextInt());

                    String sOutput = oRisposta.toString();

                    oDatagramPacket.setData(sOutput.getBytes(StandardCharsets.UTF_8));

                    oDatagramSocket.send(oDatagramPacket);
                }
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }
}