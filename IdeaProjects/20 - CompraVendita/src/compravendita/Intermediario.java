package compravendita;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Intermediario implements Runnable{

    private Richiesta oRichiesta;
    private static LinkedList<Venditore> lstVenditori = new LinkedList<>();
    private static ConcurrentSkipListSet<Integer> lstQuantita = new ConcurrentSkipListSet<>();
    private static ConcurrentHashMap<Integer,TreeSet<Richiesta>> hmRichieste = new ConcurrentHashMap<>();

    @Override
    public void run() {
        new Thread(() -> {
            try {
                ServerSocket oServerSocket = new ServerSocket(2345);

                while(true){

                    TreeSet<Richiesta> stRichieste;

                    Socket oSocket = oServerSocket.accept();
                    String sInput;
                    ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());

                    oRichiesta = (Richiesta) oInputStream.readObject();

                    stRichieste = hmRichieste.get(oRichiesta.getId());

                    if(stRichieste != null){

                        stRichieste.add(oRichiesta);
                        lstQuantita.add(oRichiesta.getQuantita());

                    }

                }

            } catch (Exception oException) {
                oException.printStackTrace();
            }
        }).start();
    }
}
