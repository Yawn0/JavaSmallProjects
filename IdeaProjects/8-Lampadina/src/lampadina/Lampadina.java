package lampadina;

/**
 * Lampadina , classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Lampadina {

    private Boolean stato;
    private int counter;
    private final int maxClick;

    /**
     * Costruttore di default
     *
     * @param maxClick numero massimo di click che é possibile eseguire sull'oggetto
     * */

    public Lampadina(int maxClick) {
        stato = false;
        this.maxClick = maxClick;
        counter = 0;
    }

    /**
     * Restituisce una stringa in base allo stato della lampadina
     *
     * @return Stringa che espone lo stato della lampadina
     */

    public String toString(){
        if(getCounter() > getMaxClick())
            return ("La lampadina é rotta, per favore vada a comprarne un'altra :D");
        else if(getStato())
            return ("La lampadina é accesa");
        else
            return ("La lampadina é spenta");
    }

    /**
     * Cambia lo stato della lampadina , aggiunge la modifica eseguita al contatore
     * e stampa lo stato della lampadina utilizzando toString
     */

    public void click(){
        counter++;
        stato = !stato;
        System.out.println(toString());
    }

    /**
     * Restituisce il valore del contatore di interazioni con la lampadina
     *
     * @return valore del contatore
     */

    public int getCounter() {
        return counter;
    }

    /**
     * Restituisce la costante che determina il numero massimo di interazioni con la lampadina
     *
     * @return valore del numero massimo di click
     */

    public int getMaxClick() {
        return maxClick;
    }

    /**
     * Restituisce lo stato della lampadina
     * @return stato della lampadina
     */

    public Boolean getStato() {
        return stato;
    }
}
