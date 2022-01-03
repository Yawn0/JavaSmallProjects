package lampadina;

/**
 * Interruttore , classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Interruttore {

    private Lampadina lampadina;

    /**
     * Istanzia un nuovo interruttore assoiciato ad una lampadina
     *
     * @param lampadina oggetto della classe Lampadina
     */
    public Interruttore(Lampadina lampadina) {
        setLampadina(lampadina);
    }

    /**
     * Istanzia una copia di un altro interruttore
     *
     * @param interruttore interruttore origine
     */
    public Interruttore(Interruttore interruttore){
        setLampadina(interruttore.lampadina);
    }

    /**
     * Imposta la lampadina da associare all'interruttore
     *
     * @param lampadina lampadina associata all'interruttore
     */
    public void setLampadina(Lampadina lampadina) {
        this.lampadina = lampadina;
    }

    /**
     * Restituisce l'oggetto di tipo Lampadina
     *
     * @return oggetto di tipo Lampadina
     */
    public Lampadina getLampadina(){
        return lampadina;
    }
}
