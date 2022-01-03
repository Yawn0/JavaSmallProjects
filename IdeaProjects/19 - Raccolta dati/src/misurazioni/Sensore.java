package misurazioni;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//    public class Sensore implements Runnable {
//    private final int ID;
//    private InetAddress oAddress;
//
//    public Sensore(int iId, InetAddress oAddress) {
//        ID= iId;
//        this.oAddress = oAddress;
//    }
//    public void run() {
//        try {
//            Socket oSocket = new Socket(oAddress, 4000);
//                BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
//
//                String sResponse = oBufferedReader.readLine();
//                oBufferedReader.close();
//
//                if(sResponse.equals("REQUEST")){
//                    ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream());
//                    oOutputStream.writeObject(new Misura());
//                }
//
//                oSocket.close();
//
//            DatagramSocket oDatagramSocket = new DatagramSocket();
//
//            Misura oMisura = new Misura("Temperatura", 35.7);
//            byte[] aBytes = oMisura.toString().getBytes();
//        } catch (Exception oException) {
//            // TODO: handle exception
//            oException.printStackTrace();
//        }
//    }
//    public void addToSystem(InetAddress oAddress) {
//        try {
//
//            Socket oSocket = new Socket(oAddress, 4000);
//            PrintWriter oWriter =  new  PrintWriter(oSocket.getOutputStream());
//
//            oWriter.println(ID + " " + 1000L);
//            oWriter.close();
//            oSocket.close();
//
//        } catch (Exception oException) {
//            // TODO: handle exception
//            oException.printStackTrace();
//        }
//    }
//}

public class Sensore implements Runnable {
    private final int ID;
    private InetAddress oAddress;

    public Sensore(int iId, InetAddress oAddress) {
        ID = iId;
        this.oAddress = oAddress;
    }
    public void run() {
        try {
            DatagramSocket oDatagramSocket = new DatagramSocket();
            Misura oMisura = new Misura("Temperatura", 35.7);

            byte[] abBuffer;
            abBuffer = oMisura.toString().getBytes();

            DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer, abBuffer.length, oAddress, 4000);
            oDatagramSocket.send(oDatagramPacket);

            oDatagramSocket.close();

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }
}