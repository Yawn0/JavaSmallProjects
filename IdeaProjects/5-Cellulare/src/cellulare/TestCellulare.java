package cellulare;

/**
 * Classe per testare la classe 'Cellulare'
 */
public class TestCellulare {
    public static void main(String[] args) {

        Cellulare Nokia = new Cellulare();
        Cellulare Tarokia = new Cellulare(20.5 ,4 );

        System.out.println("Nokia : "+Nokia.toString());
        System.out.println("Tarokia : "+Tarokia.toString());

        System.out.println();

        Nokia.ricarica(25);
        Tarokia.chiama(10);

        System.out.println("Nokia : "+Nokia.toString());
        System.out.println("Tarokia : "+Tarokia.toString());

        System.out.println();

        Nokia.chiama(15);

        Tarokia.azzeraChiamate();

        System.out.println("Nokia : "+Nokia.toString());
        System.out.println("Tarokia : "+Tarokia.toString());

    }
}
