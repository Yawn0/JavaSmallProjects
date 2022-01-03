package lampadina;
import java.io.*;

/**
 * UsaLampadina , classe main dell'esercizio Lampadina
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class UsaLampadina {
    /**
     *
     * Creo un oggetto Lampadina e faccio interagire l'utente con essa
     */

    public static void main(String[] args) {

        Lampadina L1 = new Lampadina(3);

        int x = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        do {
            String selezione = "";

            try {
                System.out.println("""
                                   
                                   Digita 'C' per interagire con la lampadina
                                   Digita 'A' per terminare l'operazione
                                   """);
                selezione = input.readLine();
            }
            catch (IOException e) {
                System.err.println("Si è verificato un errore I/O");
                e.printStackTrace();
            }

            switch (selezione) {
                case "C", "c" -> L1.click();
                case "A", "a" -> x = 1;
                default -> System.out.println("Digitazione scorretta\n");
            }
        }while(x!=1);
    }
}
