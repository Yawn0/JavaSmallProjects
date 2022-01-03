package viaggio;

/**
 * Classe di test per il package viaggio
 *
 * @author Leonardo Tocchet
 */
public class TestaViaggio {
    /**
     * 1. Creo dei viaggiatori<br>
     * 2. creo un viaggio<br>
     * 3. aggiungo i viaggiatori al viaggio<br>
     * 4. visualizzo il risultato
     *
     * @param args main
     */
    public static void main(String[] args) {

        Viaggiatore i1 = new Viaggiatore("Bianchi Giovanni", 1, "0586 854822");
        Viaggiatore i2 = new Viaggiatore("Rossi Marta", 2, "0586 844853");
        Viaggiatore i3 = new Viaggiatore("Neri Marco", 3, "0586 444722");
        Viaggiatore i4 = new Viaggiatore("Verdi Roberta", 4, "0586 974824");

        Viaggio f = new Viaggio();

        f.prenotazione(i1);
        f.prenotazione(i2);
        f.prenotazione(i3);
        f.prenotazione(i4);

//        System.out.println(f.head.getInfo());

        System.out.println("Elenco viaggiatori: ");
        System.out.println(f.elenco());
        System.out.println("------------------");
        System.out.println(f.toString());
        System.out.println("------------------");
        try {
            f.eliminaPrenotazione(4);
            System.out.println(f.toString());
            System.out.println("------------------");
            f.eliminaPrenotazione(1);
            System.out.println(f.toString());
            System.out.println("------------------");
        } catch (ViaggioException exception) {
            System.out.println(exception.getError());
        }

        System.out.println("Elenco viaggiatori: ");
        System.out.println(f.elenco());
    }
}
