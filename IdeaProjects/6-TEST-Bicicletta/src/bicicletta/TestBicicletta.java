package bicicletta;

/**
 * Compito sulla classe Bicicletta
 * 10/12/20
 * Classe per testare la classe 'Bicicletta'
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */

public class TestBicicletta {
    /**
     * Utilizzando i metodi implementati nella classe Bicicletta esegue i seguenti punti <br>
     *
     * 1) Creare una bicicletta di colore verde, con velocita' pari a 10 e il cambio di valore 1 <br>
     * 2) Impostare il cambio a 4 <br>
     * 3) Frenare la bici di 2 <br>
     * 4) Stampa a video "Sono una bici verde e la mia velocita' e' pari a 8" <br>
     * 5) Stampa a video "Il mio cambio e' impostato a 4"
     */
    public static void main(String[] args) {
        //  1
        Bicicletta Brava = new Bicicletta(10,"verde",1);

        //  2
        Brava.setCambio(4);

        //  3
        Brava.frena(2);

        //  4
        System.out.println(Brava.toString());

        //  5
        System.out.println("Il mio cambio é impostato a "+Brava.getCambio());
    }
}
