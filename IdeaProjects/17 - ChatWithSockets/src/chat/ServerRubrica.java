package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRubrica {
    public static void main(String[] args) {
        ServerSocket sSocket;
        Socket conn = null;
        int port = 2345;
        try{
            sSocket = new ServerSocket(port);
            System.out.println("In attesa di connessioni ... ");
            while(true){
                conn = sSocket.accept();
                new Utente(conn);
//                try{
//                    conn = sSocket.accept();
//                    new Utente(conn);
//                }catch (Exception exception){
//                    System.out.println(exception.getMessage());
//                }
            }
        }
        catch (IOException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        try{
            conn.close();
        }
        catch (IOException exception){
            System.out.println(exception);
        }
    }
}
