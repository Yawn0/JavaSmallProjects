package compravendita;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente implements Runnable {

    private final int TCP_PORT;
    private final String SERVER_NAME;

    public Cliente(int iPort,String sIntermediario){
        TCP_PORT = iPort;
        SERVER_NAME = sIntermediario;
    }

    @Override
    public void run() {
        try {

            Socket oSocket = new Socket(InetAddress.getByName(SERVER_NAME),TCP_PORT);

            Random oRandom = new Random();
            int iIdProdotto = oRandom.nextInt(0,Venditore.Prodotti.values().length);
            int iQuantita = oRandom.nextInt(1,1000);

            Richiesta oRichiesta = new Richiesta(iIdProdotto,iQuantita);

            ObjectOutputStream oOutStream = new ObjectOutputStream(oSocket.getOutputStream());
            oOutStream.writeObject(oRichiesta);
            oOutStream.flush();

            ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());

            while(true){

                Risposta oRisposta = (Risposta) oInputStream.readObject();
                oInputStream.close();

                if(oRisposta.oRichiesta.equals(oRichiesta)){

                    System.out.printf("""
                    >> Richiesta accettata!
                    Prodotto :      %d
                    Quantità:       %d Kg
                    Prezzo Totale:  %d €
                    \n""", oRisposta.oRichiesta.getiIdProdotto()
                            ,oRisposta.oRichiesta.getQuantita()
                            ,oRisposta.getPrezzoTotale());

                }
                else{
                    System.out.println("...");
                }
            }

        } catch (IOException | ClassNotFoundException oException) {
            oException.printStackTrace();
        }
    }
}
