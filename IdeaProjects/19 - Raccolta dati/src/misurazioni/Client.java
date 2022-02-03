package misurazioni;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

//public class Client implements Runnable {
//    private final int iNumeroSensori;
//    private InetAddress oAddress;
//
//    public Client(int iNumeroSensori, InetAddress oAddress) {
//        this.iNumeroSensori = iNumeroSensori;
//        this.oAddress = oAddress;
//    }
//
//    public void run() {
//        Random oRandomGenerator = new Random();
//        if(oRandomGenerator.nextDouble()<0.5)
//            simpleRequest(oRandomGenerator.nextInt(iNumeroSensori));
//        else
//            complexRequest(oRandomGenerator.nextInt(iNumeroSensori)
//                            ,System.currentTimeMillis()+(oRandomGenerator.nextInt(1000)+1000)
//                            ,System.currentTimeMillis()+(oRandomGenerator.nextInt(1000)+1000));
//    }
//
//    private void simpleRequest(int iId) {
//        try {
//            Socket oSocket = new Socket(oAddress,2000);
//            PrintWriter oPrintWriter = new PrintWriter(oSocket.getOutputStream());
//            oPrintWriter.println(iId);
//            oPrintWriter.close();
//            ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());
//            System.out.println((Misura) oInputStream.readObject());
//            oSocket.close();
//        } catch (Exception oException) {
//            // TODO: handle exception
//            oException.printStackTrace();
//        }
//    }
//
//    private void complexRequest(int iId, long lTimeStamp1, long lTimeStamp2) {
//        try {
//            Socket oSocket = new Socket(oAddress,3000);
//            PrintWriter oPrintWriter = new PrintWriter(oSocket.getOutputStream());
//            oPrintWriter.println(iId +" "+ lTimeStamp1 +" "+ lTimeStamp2);
//            oPrintWriter.close();
//            ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());
//            System.out.println((Misura) oInputStream.readObject());
//            oSocket.close();
//        } catch (Exception oException) {
//            // TODO: handle exception
//            oException.printStackTrace();
//        }
//    }
//}

public class Client implements Runnable {
    private final int iNumeroSensori;
    private InetAddress oAddress;

    public Client(int iNumeroSensori, InetAddress oAddress) {
        this.iNumeroSensori = iNumeroSensori;
        this.oAddress = oAddress;
    }

    public void run() {
        Random oRandom = new Random();
        if(oRandom.nextDouble() < 0.5)
            simpleRequest(oRandom.nextInt(iNumeroSensori));
        else
            complexRequest(oRandom.nextInt(iNumeroSensori),System.currentTimeMillis(),System.currentTimeMillis() + 1000);
    }

    private void simpleRequest(int iId) {
        try {
            System.out.println("client id:" + iId);

            Socket oSocket = new Socket(oAddress,2000);

            PrintWriter oPrintWriter = new PrintWriter(oSocket.getOutputStream());
            oPrintWriter.println(iId);

            ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());
            System.out.println(oInputStream.readObject().toString());

            oInputStream.close();

            oSocket.close();

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }

    private void complexRequest(int iId, long lTimeOne, long lTimeTwo) {
        try {

            System.out.println("client id:" + iId + " address : " + oAddress.toString());

            Socket oSocket = new Socket(oAddress,3000);

            PrintWriter oPrintWriter = new PrintWriter(oSocket.getOutputStream());

            oPrintWriter.println(iId +" "+ lTimeOne +" "+lTimeTwo);

            ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());
            System.out.println(oInputStream.readObject());

            oInputStream.close();

            oSocket.close();
            System.out.println("client");

        } catch (Exception oException) {
            oException.printStackTrace();
        }
    }
}