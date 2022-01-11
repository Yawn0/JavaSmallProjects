package compravendita;

public class Main {

    private static final int N_VENDITORI = 5;
    private static final int N_CLIENTI = 7;

    private static final int TCP_PORT = 2345;
    private static final int UDP_PORT = 6789;

    private static final String SERVER_NAME = "intermediario.eu";

    public static void main(String[] args) {

        Venditore oVenditore;
        Cliente oCliente;

        new Thread(Intermediario.getSingleton(TCP_PORT,UDP_PORT)).start();

        for(int iIndex = 0; iIndex < N_VENDITORI; iIndex++){

            oVenditore = new Venditore(UDP_PORT, SERVER_NAME);
            new Thread(oVenditore).start();
        }

        for(int iIndex = 0; iIndex < N_CLIENTI; iIndex++){

            oCliente = new Cliente(TCP_PORT, SERVER_NAME);
            new Thread(oCliente).start();
        }
    }
}
