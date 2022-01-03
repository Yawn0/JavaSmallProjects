package chat;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket conn = null;
        String server = "localhost";
        int port = 2345;
        InputStreamReader in, input;
        BufferedReader sIN, tastiera;
        OutputStream out;
        PrintWriter sOUT;
        String nomeDaInviare;
        String telefRicevuto;

        try{
            conn = new Socket(server,port);
            System.out.println("Connessione eseguita.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //flusso in ingresso da socket
            in = new InputStreamReader(conn.getInputStream());
            sIN = new BufferedReader(in);
            //flusso in uscita da socket
            out = conn.getOutputStream();
            sOUT = new PrintWriter(out);
            // flusso in ingresso da tastiera
            input = new InputStreamReader(System.in);
            tastiera = new BufferedReader(input);
            System.out.println("Servizio di ricerca inizializzato.");

            while(true){
                //legge il messaggioda tastiera
                System.out.println("nome richiesto (* = fine)");
                nomeDaInviare = tastiera.readLine();

                //interrompe la ricerca
                if(nomeDaInviare.equals("*")){
                    break;
                }
                //invia il messaggio
                sOUT.println(nomeDaInviare);
                sOUT.flush();
                //stampa il messaggio ricevuto
                telefRicevuto = sIN.readLine();
                System.out.println("telefono: " + telefRicevuto);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            conn.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
