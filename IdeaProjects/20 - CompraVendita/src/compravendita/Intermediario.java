package compravendita;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Intermediario implements Runnable{

    private static Intermediario SINGLETON = null;
    private static final String HOST_NAME = "intermediario.eu";
    private final int TCP_PORT;
    private final int UDP_PORT;
    private static ConcurrentSkipListSet<Integer> lstQuantita = new ConcurrentSkipListSet<>();
    private static ConcurrentHashMap<Integer,TreeSet<Richiesta>> hmRichieste = new ConcurrentHashMap<>();
    private final int BUFFER_LENGTH = 512;

    public static Intermediario getSingleton(int iPort,int iPort2){
        if(SINGLETON == null){
            SINGLETON = new Intermediario(iPort,iPort2);
        }
        return SINGLETON;
    }

    private Intermediario(int iPort,int iPort2){
        TCP_PORT = iPort;
        UDP_PORT = iPort2;
    }

    @Override
    public void run() {
        new Thread(this::manageRequests).start();
    }

    private void manageRequests(){
        try {

            ServerSocket oServerSocket = new ServerSocket(TCP_PORT);
            MulticastSocket oMulticastSocket = new MulticastSocket(UDP_PORT);

            oMulticastSocket.joinGroup(new InetSocketAddress(InetAddress.getByName(HOST_NAME), UDP_PORT),null); // ????????????

            byte[] abBuffer = new byte[BUFFER_LENGTH];

            DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer, abBuffer.length);
            oMulticastSocket.receive(oDatagramPacket);

            ByteArrayInputStream oData = new ByteArrayInputStream(oDatagramPacket.getData());
            ObjectInputStream oInputStream = new ObjectInputStream(oData);


            while(true){

                TreeSet<Richiesta> stRichieste;

                Socket oSocket = oServerSocket.accept();
                oInputStream = new ObjectInputStream(oSocket.getInputStream());

                Richiesta oRichiesta = (Richiesta) oInputStream.readObject();

                stRichieste = hmRichieste.get(oRichiesta.getId());

                if(stRichieste != null){

                    stRichieste.add(oRichiesta);
                    lstQuantita.add(oRichiesta.getQuantita());

                }
            }

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void mangaeResponses(){

    }
}
