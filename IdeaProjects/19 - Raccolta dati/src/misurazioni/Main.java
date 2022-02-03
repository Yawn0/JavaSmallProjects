package misurazioni;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    private static final String sServerIP = "192.168.0.109";
    private static final int iNumeroSensori = 2;
    private static final int iNumeroClient = 2;

    public static void main(String[] args) throws UnknownHostException {

        new Thread(new Server()).start();

        for(int iIndex = 0; iIndex < iNumeroSensori; iIndex++)
            new Thread(new Sensore(iIndex, InetAddress.getLocalHost())).start();

        for(int iIndex = 0; iIndex < iNumeroClient; iIndex++)
            new Thread(new Client(iNumeroSensori,InetAddress.getLocalHost())).start();
    }
}

