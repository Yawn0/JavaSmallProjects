package formula1;

import java.time.*;

/**
 * Esercizio sul package formula1 e le relative classi : Pilota , AutoDaCorsa , ClassificaAutoDaCorsa<br>
 * Classe main ClassificaAutoDaCorsa
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class ClassificaAutoDaCorsa {

    private static final int MAX = 5;

    /**
     * Gestisce una classifica di 5 auto da corsa,
     * ordinate in base alla velocità massima (dalla piu' alta alla piu' bassa)<br>
     *    1) Creo 5 oggetti Pilota<br>
     *    2) Creo un array e lo riempio con 5 oggetti AutoDaCorsa<br>
     *    3) Stampo i partecipanti, li ordino in base alla loro velocita' massima e stampo la classifica finale
     */
    public static void main(String[] args) {

        //  1

        Pilota p1 = new Pilota("The    ","IceMan","     FI",LocalDate.of(1979,8,17));
        Pilota p2 = new Pilota("El     ","Nano  ","     SP",LocalDate.of(1981,7,29));
        Pilota p3 = new Pilota("Britney","      ","     DE",LocalDate.of(1985,6,27));
        Pilota p4 = new Pilota("Kaiser ","Schumi","     DE",LocalDate.of(1969,1,3));
        Pilota p5 = new Pilota("The    ","Doctor","     IT",LocalDate.of(1979,2,19));

        //  2

        AutoDaCorsa[] Ranking = new AutoDaCorsa[MAX];

        Ranking[0] = new AutoDaCorsa("Ferrari",666,p1);
        Ranking[1] = new AutoDaCorsa("Renault",321,p2);
        Ranking[2] = new AutoDaCorsa("Mercedes",1,p3);
        Ranking[3] = new AutoDaCorsa("Ferrari",999,p4);
        Ranking[4] = new AutoDaCorsa("McLaren",998,p5);

        //  3

        printPiloti(Ranking);

        sortRanking(Ranking);

        printRanking(Ranking);
    }

    /**
     * Stampa i partecipanti
     * @param Ranking array da cui prendere i partecipanti
     */

    private static void printPiloti(AutoDaCorsa[] Ranking) {
        System.out.println("\n   Partecipanti al Gran Premio della Quarantena \n");
        for (int i = 0; i < Ranking.length; i++) {
            System.out.println(Ranking[i].getPilota().presentaPilota());
        }
    }

    /**
     * Stampa la classifica finale
     * @param Ranking array da cui prendere i partecipanti
     */

    private static void printRanking(AutoDaCorsa[] Ranking) {
        System.out.println("\n   Classifica Gran Premio della Quarantena     ");
        System.out.println("\n   Nome           | Nazionalità  | Scuderia | Velocità massima\n");
        for(int i=0;i<Ranking.length;i++){
            System.out.println((i+1)+"° "+ Ranking[i].getPilota()+" "+Ranking[i].toString());
        }
    }

    /**
     * Algoritmo per ordinare la classifica in base alla velocita' massima delle auto
     * @param Ranking array di auto da cui recuperare la velocita' massima
     */

    private static void sortRanking(AutoDaCorsa[] Ranking) {
        int k,dim=Ranking.length;
        do{
            k=0;
            for(int i=0;i<dim-1;i++)
                if (Ranking[i].getMaxVel() < Ranking[i + 1].getMaxVel()) {
                    AutoDaCorsa aux = Ranking[i];
                    Ranking[i] = Ranking[i + 1];
                    Ranking[i + 1] = aux;
                    k = 1;
                }
            dim--;
        }while(k==1);
    }
}
