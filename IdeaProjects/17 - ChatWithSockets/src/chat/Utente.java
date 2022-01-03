package chat;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class Utente implements Runnable{

    private Socket conn = null;
    private InputStreamReader in;
    private BufferedReader sIN;
    private OutputStream out;
    private PrintWriter sOUT;
    private FileReader fr;
    private BufferedReader frIN;
    Contatto contact;
    String string;
    StringTokenizer st;
    String phoneNumberToSend;
    String nameRecived;

    public Utente(Socket conn){
        this.conn = conn;
        try{
            //apre il flusso in uscita e in ingresso dalla socket
            out = conn.getOutputStream();
            sOUT = new PrintWriter(out);
            in = new InputStreamReader(conn.getInputStream());
            sIN = new BufferedReader(in);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        new Thread(this).start();
        System.out.println("Il cliente ha avviato una ricerca");
    }

    @Override
    public void run() {
        try{
            while(true){
                nameRecived = sIN.readLine();
                System.out.println(nameRecived);

                if(nameRecived == null){
                    System.out.println("Il cliente ha chiuso la connessione");
                    break;
                }

                //cerca nome in rubrica
                fr = new FileReader("agenda.txt");
                frIN = new BufferedReader(fr);
                contact = new Contatto();
                boolean found = false;
                string = frIN.readLine();

                //legge una riga
                while((string != null) && !found){
                    st = new StringTokenizer(string, ";");
                    contact.nome = st.nextToken();
                    contact.telefono = st.nextToken();

                    if(contact.nome.equals(nameRecived)){
                        phoneNumberToSend = contact.telefono;
                        found = true;
                    }

                    string = frIN.readLine();
                }
                fr.close();

                if(!found){
                    phoneNumberToSend = "not found";

                }

                sOUT.println(phoneNumberToSend);
                sOUT.flush();
            }

            conn.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
