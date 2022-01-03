package misurazioni;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

//public class Server implements Runnable {
//    private ConcurrentHashMap<Integer, Misura> mapMisureDataBase = new ConcurrentHashMap<>();   //memorizzo per ogni sensore la misura più recente disponibile
//
//    public void run() {
//        new Thread(() -> {
//            try {
//                ServerSocket oServerSocket = new ServerSocket(3000);
//                Socket oSocket;
//                while(true) {
//                    oSocket = oServerSocket.accept();
//                    BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
//
//                    String sResponse = oBufferedReader.readLine();
//
//                    oBufferedReader.close();
//                    StringTokenizer oTokenizer = new StringTokenizer(sResponse, " ");
//
//                    Integer iId = Integer.parseInt(oTokenizer.nextToken());
//                    long lInitialTimestamp =Long.parseLong(oTokenizer.nextToken());
//                    long lFinalTimeStamp = Long.parseLong(oTokenizer.nextToken());
//
//                    //suppongo che lInitialTimestamp abbia sempre un valore che sia più grande del tempo corrente in millisecondi
//                    Thread.sleep(lFinalTimeStamp - lInitialTimestamp);
//                    Misura oFristMisura = mapMisureDataBase.get(iId);
//                    Thread.sleep(lFinalTimeStamp - lInitialTimestamp);
//                    Misura oMisura = mapMisureDataBase.get(iId);                // apetto che la misura venga sovrascritta dal sensore prima di rilevarla nuovamente
//
//                    ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream());
//
//                    oOutputStream.writeObject(new Misura(oMisura.getGrandezza(),(oFristMisura.getValore()+ oMisura.getValore())/2,System.currentTimeMillis()));
//
//                    oSocket.close();
//                }
//            }catch(Exception oException) {
//                oException.printStackTrace();
//            }
//        }).start();
//
//        new Thread(new Ascoltatore4()).start();
//
//        new Thread(() ->  {
//            try {
//                ServerSocket oServerSocket = new ServerSocket(2000);
//                Socket oSocket;
//                for(;;) {
//                    oSocket = oServerSocket.accept();
//                    BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
//
//                    String sResponse = oBufferedReader.readLine();
//
//                    oBufferedReader.close();
//
//                    int iId = Integer.parseInt(sResponse);
//
//                    ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream());
//
//                    oOutputStream.writeObject(mapMisureDataBase.get(iId));
//
//                    oSocket.close();
//                }
//            } catch (Exception oException) {
//                // TODO: handle exception
//                oException.printStackTrace();
//            }
//        }).start();
//    }
//    private class Ascoltatore4 implements Runnable{
//        public void run() {
//            try {
//                ServerSocket oServerSocket = new ServerSocket(4000);
//
//                while(true) {
//                    Socket oSocket = oServerSocket.accept();
//                    BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
//
//                    String sResponse = oBufferedReader.readLine();
//
//                    oBufferedReader.close();
//
//                    StringTokenizer oTokenizer = new StringTokenizer(sResponse, " ");
//
//                    InetAddress oAddress = oSocket.getInetAddress();
//
//                    int iId = Integer.parseInt(oTokenizer.nextToken());
//
//                    mapMisureDataBase.put(iId, null);
//
//                    new Thread(new Controller(iId, oAddress,Long.parseLong(oTokenizer.nextToken())));
//
//                    DatagramSocket oDatagramSocket = new DatagramSocket();
//                    byte aBytes[] = new byte[256];
//                    DatagramPacket oDatagramPacket = new DatagramPacket(aBytes, aBytes.length);
//                    oDatagramSocket.receive(oDatagramPacket);
//
//                    String sData = Arrays.toString(oDatagramPacket.getData());
//
//                    StringTokenizer oStringTokenizer = new StringTokenizer()
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        }
//    }
//    private class Controller implements Runnable{
//        private int sensore;
//        private InetAddress indirizzo;
//        private long intervallo;
//
//        public Controller(int s, InetAddress ip, long i) {
//            sensore=s;
//            indirizzo=ip;
//            intervallo=i;
//        }
//        public void run() {
//            try {
//                Socket s;
//                for(;;) {
//                    s=new Socket(indirizzo,5000);
//                    PrintWriter pw = new PrintWriter(s.getOutputStream());
//                    pw.println("REQUEST");
//                    pw.close();
//                    ObjectInputStream reader = new ObjectInputStream(s.getInputStream());
//                    Misura corrente = (Misura)reader.readObject();
//                    mapMisureDataBase.put(sensore,corrente);
//                    s.close();
//                    Thread.sleep(intervallo);
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        }
//    }
//}

public class Server implements Runnable {

    private ConcurrentHashMap<Integer, Misura> hmMisure = new ConcurrentHashMap<>();  //memorizzo per ogni sensore la misura più recente disponibile

    public void run() {

        new Thread(() -> {
            try {

                ServerSocket oServerSocket = new ServerSocket(3000);
                Socket oSocket;

                while(true) {

                    oSocket = oServerSocket.accept();
                    String sInput;

                    try (BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(oSocket.getInputStream()))) {
                        sInput = oBufferedReader.readLine();
                    }

                    StringTokenizer sTokenizer = new StringTokenizer(sInput, " ");

                    Integer iId = Integer.parseInt(sTokenizer.nextToken());
                    long lStarTime= Long.parseLong(sTokenizer.nextToken());
                    long lEndTime = Long.parseLong(sTokenizer.nextToken());

                    Thread.sleep(lEndTime-lStarTime);
                    Misura oMsiuraFirst = hmMisure.get(iId);

                    Thread.sleep(lEndTime-lStarTime);
                    Misura oMisuraSecond = hmMisure.get(iId);

                    try(ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream())){
                        oOutputStream.writeObject(new Misura(oMisuraSecond.getGrandezza(),(oMsiuraFirst.getValore()+ oMisuraSecond.getValore())/2 ,System.currentTimeMillis()));
                    }

                    oSocket.close();
                }

            }catch(Exception oException) {
                oException.printStackTrace();
            }

        }).start();

        new Thread(() ->  {

            try {
                ServerSocket oServerSocket = new ServerSocket(2000);
                Socket oSocket;

                for(;;) {

                    oSocket = oServerSocket.accept();
                    String sInput;

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(oSocket.getInputStream()))) {
                        sInput = br.readLine();
                    }

                    int iId = Integer.parseInt(sInput);

                    try (ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream())) {
                        oOutputStream.writeObject(hmMisure.get(iId));
                    }

                    oSocket.close();
                }

            } catch (Exception oException) {
                oException.printStackTrace();
            }

            try {
                ServerSocket oServerSocket = new ServerSocket(4000);

                while (true) {
                    DatagramSocket oDatagramSocket = new DatagramSocket();

                    byte[] abInput = new byte[512];
                    DatagramPacket oDatagramPacket = new DatagramPacket(abInput, abInput.length);

                    oDatagramSocket.receive(oDatagramPacket);

                    String sInput = Arrays.toString(oDatagramPacket.getData());

                    StringTokenizer oTokenizer = new StringTokenizer(sInput, " ");
                    Integer iId = Integer.parseInt(oTokenizer.nextToken());
                    String sGrandezza = oTokenizer.nextToken();
                    double dValore=Double.parseDouble(oTokenizer.nextToken());

                    Misura oMisura = new Misura (sGrandezza, dValore, System.currentTimeMillis());

                    hmMisure.put(iId, oMisura);

                    oDatagramSocket.close();
                }

            } catch (Exception oException) {
                oException.printStackTrace();
            }

        }).start();
    }
}