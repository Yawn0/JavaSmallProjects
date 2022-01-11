package compravendita;

import javax.management.timer.Timer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class Intermediario implements Runnable{

    private static final ConcurrentLinkedQueue<Richiesta> lstRichieste = new ConcurrentLinkedQueue<>();
    private static final ConcurrentSkipListSet<Risposta> lstRisposte = new ConcurrentSkipListSet<>(Risposta::compareTo);
    private static final String HOST_NAME = "intermediario.eu";
    private static Intermediario oIntermediario = null;
    private static final int BUFFER_LENGTH = 1024;
    private static boolean bOnTime = true;
    private final int TCP_PORT;
    private final int UDP_PORT;

    public static Intermediario getSingleton(int iPortTCP, int iPortUDP){
        if(oIntermediario == null){
            oIntermediario = new Intermediario(iPortTCP, iPortUDP);
        }
        return oIntermediario;
    }

    private Intermediario(int iPort,int iPort2){
        TCP_PORT = iPort;
        UDP_PORT = iPort2;
    }

    @Override
    public void run() {
        new Thread(this::manageClients).start();
        new Thread(this::manageVendors).start();
    }

    private void manageClients(){
        try {

            System.out.println("Inizio ricezione richieste");

            while(bOnTime){

                ServerSocket oServerSocket = new ServerSocket(TCP_PORT);
                Socket oSocket = oServerSocket.accept();
                ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());

                Richiesta oRichiesta = (Richiesta) oInputStream.readObject();
                lstRichieste.add(oRichiesta);

                System.out.println(oRichiesta.toString());

                oSocket.close();
            }

            MulticastSocket oMulticastSocket = new MulticastSocket(TCP_PORT);
            ByteArrayOutputStream abBuffer = new ByteArrayOutputStream();
            ObjectOutputStream oOutputStream = new ObjectOutputStream(abBuffer);

            oOutputStream.writeObject( lstRisposte.isEmpty() ? new Risposta(new Richiesta(-1,-1),-1) : lstRisposte.first());

            DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer.toByteArray(),
                    abBuffer.toByteArray().length,
                    InetAddress.getByName(HOST_NAME),
                    TCP_PORT);

            oMulticastSocket.send(oDatagramPacket);

            oMulticastSocket.close();

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void manageVendors(){
        try{

            long lStartTimetamp = System.currentTimeMillis();

            while((System.currentTimeMillis() - lStartTimetamp) <= Timer.ONE_MINUTE){

                MulticastSocket oMulticastSocket = new MulticastSocket(UDP_PORT);

                if(!lstRichieste.isEmpty()){

                    ByteArrayOutputStream oByteArrayOutput = new ByteArrayOutputStream();
                    ObjectOutputStream oOutputStream = new ObjectOutputStream(oByteArrayOutput);

                    oOutputStream.writeObject(lstRichieste.poll());

                    DatagramPacket oDatagramPacket = new DatagramPacket(oByteArrayOutput.toByteArray(),
                            oByteArrayOutput.toByteArray().length,
                            InetAddress.getByName(HOST_NAME),
                            UDP_PORT);

                    oMulticastSocket.send(oDatagramPacket);
                }

                oMulticastSocket.joinGroup(new InetSocketAddress("230.0.0.1",UDP_PORT),null);

                byte[] abBuffer = new byte[BUFFER_LENGTH];
                DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer, abBuffer.length);

                oMulticastSocket.receive(oDatagramPacket);

                ByteArrayInputStream oData = new ByteArrayInputStream(oDatagramPacket.getData());
                ObjectInputStream oInputStream = new ObjectInputStream(oData);
                Risposta oRisposta = (Risposta) oInputStream.readObject();

                lstRisposte.add(oRisposta);
            }

            bOnTime = false;
        }
        catch (Exception oException){
            oException.printStackTrace();
        }
    }
}
