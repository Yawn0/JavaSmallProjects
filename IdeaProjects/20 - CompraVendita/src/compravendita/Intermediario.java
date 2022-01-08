package compravendita;

import javax.management.timer.Timer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Intermediario implements Runnable{

    private static Intermediario SINGLETON = null;
    private static final String HOST_NAME = "intermediario.eu";
    private final int TCP_PORT;
    private final int UDP_PORT;
    private static final ConcurrentLinkedQueue<Richiesta> queRichieste = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<Risposta> queRisposte = new ConcurrentLinkedQueue<>();
    private final int BUFFER_LENGTH = 512;

    public static Intermediario getSingleton(int iPortTCP, int iPortUDP){
        if(SINGLETON == null){
            SINGLETON = new Intermediario(iPortTCP, iPortUDP);
        }
        return SINGLETON;
    }

    private Intermediario(int iPort,int iPort2){
        TCP_PORT = iPort;
        UDP_PORT = iPort2;
    }

    @Override
    public void run() {
        new Thread(this::catchRequest).start();
        new Thread(this::sendRequest).start();
        new Thread(this::catchResponse).start();
        new Thread(this::sendResponse).start();
    }

    private void catchRequest(){
        try {

            ServerSocket oServerSocket = new ServerSocket(TCP_PORT);

            while(true){

                try{

                    Socket oSocket = oServerSocket.accept();
                    ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());

                    Richiesta oRichiesta = (Richiesta) oInputStream.readObject();
                    queRichieste.add(oRichiesta);
                }
                catch (Exception oException){
                    oException.printStackTrace();
                }
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void sendRequest(){

        try{

            MulticastSocket oMulticastSocket = new MulticastSocket(UDP_PORT);
            ByteArrayOutputStream oByteArrayOutput = new ByteArrayOutputStream();
            ObjectOutputStream oOutputStream = new ObjectOutputStream(oByteArrayOutput);
            DatagramPacket oDatagramPacket;

            while(true){

                if(!queRichieste.isEmpty()){

                    oOutputStream.writeObject(queRichieste.poll());

                    oDatagramPacket = new DatagramPacket(oByteArrayOutput.toByteArray(),
                                                         oByteArrayOutput.toByteArray().length,
                                                         InetAddress.getByName(HOST_NAME),
                                                         UDP_PORT);

                    oMulticastSocket.send(oDatagramPacket);
                }
            }
        }
        catch (Exception oException){
            oException.printStackTrace();
        }
    }

    private void catchResponse(){
        try {

            MulticastSocket oMulticastSocket = new MulticastSocket(UDP_PORT);

            oMulticastSocket.joinGroup(new InetSocketAddress(InetAddress.getByName(HOST_NAME), UDP_PORT),null); // ????????????

            byte[] abBuffer = new byte[BUFFER_LENGTH];

            DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer, abBuffer.length);

            while(true){

                oMulticastSocket.receive(oDatagramPacket);

                ByteArrayInputStream oData = new ByteArrayInputStream(oDatagramPacket.getData());
                ObjectInputStream oInputStream = new ObjectInputStream(oData);
                Risposta oRisposta = (Risposta) oInputStream.readObject();

                queRisposte.add(oRisposta);
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void sendResponse(){

        try{

            long lStartTiemetamp = System.currentTimeMillis();

            MulticastSocket oMulticastSocket = new MulticastSocket(TCP_PORT);
            ByteArrayOutputStream abBuffer = new ByteArrayOutputStream();
            ObjectOutputStream oOutputStream = new ObjectOutputStream(abBuffer);
            while(true){

                if((System.currentTimeMillis() - lStartTiemetamp) <= Timer.ONE_MINUTE){

                    for(Risposta oRisposta : queRisposte) {
                        oOutputStream.writeObject(oRisposta);

                        DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer.toByteArray(),
                                                                            abBuffer.toByteArray().length,
                                                                            InetAddress.getByName(HOST_NAME),
                                                                            TCP_PORT);

                        oMulticastSocket.send(oDatagramPacket);

                        queRisposte.remove(oRisposta);

                        lStartTiemetamp = System.currentTimeMillis();
                    }
                }
            }

        }catch(Exception oException){
            oException.printStackTrace();
        }
    }
}
