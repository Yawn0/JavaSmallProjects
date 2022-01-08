package compravendita;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;

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
            int iQuantita = oRandom.nextInt(1,999);

            Richiesta oRichiesta = new Richiesta(iIdProdotto,iQuantita);

            ObjectOutputStream oOutStream = new ObjectOutputStream(oSocket.getOutputStream());
            oOutStream.writeObject(oRichiesta);
            oOutStream.close();

            ObjectInputStream oInStream = new ObjectInputStream(oSocket.getInputStream());
            String sRisposta = (String) oInStream.readObject();
            oInStream.close();

            StringTokenizer oTokenizer = new StringTokenizer(sRisposta,Risposta.DELIMITER);

            iIdProdotto = Integer.parseInt(oTokenizer.nextToken());
            iQuantita = Integer.parseInt(oTokenizer.nextToken());

            if(new Richiesta(iIdProdotto,iQuantita).equals(oRichiesta)){

                int iPrezzoTotale = Integer.parseInt(oTokenizer.nextToken());
                int iIdIntermediario = Integer.parseInt(oTokenizer.nextToken());

                if(iPrezzoTotale != -1){

                    System.out.printf("""
                        >> Richiesta accettata!
                        Prodotto :      %d
                        Quantità:       %dKg
                        Prezzo Totale:  %d€
                        \n""", iIdProdotto,iQuantita,iPrezzoTotale);
                }
                else {
                    System.out.println(" >> Richiesta respinta :( \n");
                }
            }
            else{
                System.out.println(">> Risposta non combaciante");
            }

        } catch (IOException | ClassNotFoundException oException) {
            oException.printStackTrace();
        }
    }
}
