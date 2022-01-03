package pianoDiStudio;

/**
 * Classe di test per il package pianoDiStudio
 *
 * @author Leonardo Tocchet
 */
public class TestaPianoStudio {
    /**
     * 1. Creo degli esami<br>
     * 2. creo un piano di studio<br>
     * 3. aggiungo gli esami al piano di studio<br>
     * 4. visualizzo il risultato
     *
     * @param args main
     */

    static PianoStudio p = new PianoStudio();

    public static void main(String[] args) {

        Esame n1 = new Esame("ALGEBRA LINEARE", 15, "De Villa");
        Esame n2 = new Esame("MATEMATICA DISCRETA", 45, "Fernández");
        Esame n3 = new Esame("PROJECT MANAGEMENT", 1, "Tichete");
        Esame n4 = new Esame("ALGORITMI E STRUTTURE DATI", 150, "Tachi");
        Esame n5 = new Esame("SISTEMI OPERATIVI", 1500, "Tac");

        p.aggiungiEsame(n1);
        p.aggiungiEsame(n2);
        p.aggiungiEsame(n3);
        p.aggiungiEsame(n4);
        p.aggiungiEsame(n5);

        System.out.println("Piano di Studio: ");
        System.out.println(p.elenco());
        System.out.println("------------------");
        System.out.println(p.toString());
        System.out.println("------------------");
        try {
            p.eliminaEsame(4);
            System.out.println(p.toString());
            System.out.println("------------------");
            p.eliminaEsame(1);
            System.out.println(p.toString());
            System.out.println("------------------");
        } catch (PianoStudioException exception) {
            System.out.println(exception.getError());
        }

        p.aggiungiEsame(n5);

        System.out.println("Piano di Studio: ");
        System.out.println(p.elenco());
        System.out.println("------------------");
        System.out.println(p.toString());
    }
}