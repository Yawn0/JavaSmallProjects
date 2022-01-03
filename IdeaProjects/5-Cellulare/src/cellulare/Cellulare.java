package cellulare;

/**
 * Esercizio sulla classe 'Cellulare'
 * Classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Cellulare {

    private double credito;
    private int numeroChiamate;

    /**
     * Costruttore di default che assegna i valori di credito = 0.0 e numeroChiamate = 0
     */
    public Cellulare(){
        credito = 0.0;
        numeroChiamate = 0;
    }

    /**
     * Imposta il valore iniziale del credito e il valore iniziale del numero delle chiamate
     * @param creditoIniziale  valore credito iniziale
     * @param chiamateIniziali numero chiamate iniziali
     */
    public Cellulare(double creditoIniziale, int chiamateIniziali){
        credito = creditoIniziale;
        numeroChiamate = chiamateIniziali;
    }

    /**
     * Restituisce il valore del credito attuale
     * @return valore del credito
     */
    public double getCredito(){
        return credito;
    }

    /**
     * Restituisce il numero delle chiamate effettuate
     * @return numero delle chiamate effettuate
     */
    public int getNumeroChiamate(){
        return numeroChiamate;
    }

    /**
     * Aggiorna il valore 'credito'
     * @param unaRicarica valore che modifica il credito
     */
    public void ricarica(double unaRicarica){
        credito += unaRicarica;
    }

    /**
     * Aggiorna il valore 'credito' in base ai minuti di chiamata effettuati
     * @param minutiChiamata minuti chiamata effettuati
     */
    public void chiama(double minutiChiamata){
        credito -= (minutiChiamata * 0.20);
        numeroChiamate++;
    }

    /**
     * Azzera il numero di chiamate effettuate
     */
    public void azzeraChiamate(){
        numeroChiamate = 0;
    }

    /**
     * Restituisce una stringa con i dati 'credito' e 'chiamate effettuate'
     * @return stringa
     */

    public String toString(){
        return "Credito attuale: "+getCredito()+"\nChiamate effettuate: "+getNumeroChiamate();
    }

}
