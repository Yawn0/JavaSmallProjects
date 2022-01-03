package lampadina;
import  java.io.*;

/**
 * UsaInterruttore , classe main del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class UsaInterruttore {

    /**
     *
     * Creo due interruttori associati alla stessa lampadina
     * e faccio interagire l'utente con essi
     */
    public static void main(String[] args){

        Interruttore inter1 = new Interruttore(new Lampadina(5));
        Interruttore inter2 = new Interruttore(inter1);

        int x=0;
        BufferedReader input = new BufferedReader( new InputStreamReader(System.in));

        do{
            String selezione = "";

            try{
                System.out.println("""
                                    
                                    Digitare '1' per interagire con il primo interruttore 
                                    Digitare '2' per interagire con il secondo interruttore
                                    Digitare 'X' per terminare la sessione
                                    """);
                selezione = input.readLine();
            }
            catch (IOException e ){
                System.err.println("Si é verificato un errore I/O");
                e.printStackTrace();
            }

            switch (selezione){
                case "1" -> inter1.getLampadina().click();
                case "2" -> inter2.getLampadina().click();
                case "X", "x" -> x=1;
                default -> System.out.println("Digitazione scorretta\n");
            }
        }while(x!=1);
    }
}
