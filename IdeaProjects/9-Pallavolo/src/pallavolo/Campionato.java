package pallavolo;

import java.time.Year;

/**
 * Classe Campionato<br>
 *    rappresentante un campionato contenente 3 squadre e appartenente ad un anno
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Campionato {

    private final Year anno;
    private final Squadra[] squadre;

    /**
     * Instanzia un nuovo campionato con anno e 3 squadre
     *
     * @param anno anno dato in input
     * @param s1   squadra 1 data in input
     * @param s2   squadra 2 data in input
     * @param s3   squadra 3 data in input
     */
    public Campionato(Year anno, Squadra s1, Squadra s2, Squadra s3){
        this.anno = anno;
        squadre = new Squadra[3];
        squadre[0] = s1;
        squadre[1] = s2;
        squadre[2] = s3;
    }

    /**
     * Stampa le squadre appartenenti al campionato
     */
    public void stampaSquadre(){
        System.out.println("\nSquadre iscritte al campionato del "+anno);
        for (Squadra s : squadre){
            System.out.println("\n___________________________________");
            System.out.println("\nNome squadra : "+s.getNome().toUpperCase() +"\nCittà : "+s.getCitta().toUpperCase() +"\n\nGiocatori:");
            s.visualizzaTeam();
        }
    }

    /**
     * Restituisce un array contenente le squadre partecipanti al campionato
     *
     * @return array contenente le squadre partecipanti al campionato
     */
    public Squadra[] getSquadre() {
        return squadre;
    }
}
