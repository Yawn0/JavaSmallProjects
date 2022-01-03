package bicicletta;

/**
 * Compito sulla classe Bicicletta
 * 10/12/20
 * Classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Bicicletta {

    private int velocita;
    private String colore;
    private int cambio;

    /**
     * Crea un'istanza della classe Bicicletta, a cui assegna i valori passati come parametri
     *
     * @param velocitaIniziale valore della velocita' passato come parametro
     * @param colore           valore del colore passato come parametro
     * @param cambio           valore del cambio passato come parametro
     */

    public Bicicletta(int velocitaIniziale, String colore, int cambio) {
        setVelocita(velocitaIniziale);
        setColore(colore);
        setCambio(cambio);
    }

    /**
     * Restituisce il valore della velocita' della bicicletta
     *
     * @return valore della velocita'
     */
    public int getVelocita() {
        return velocita;
    }

    /**
     * Restituisce il colore della bicicletta
     *
     * @return valore del colore
     */
    public String getColore(){
        return colore;
    }

    /**
     * Restituisce il valore del cambio della bicicletta
     *
     * @return valore del cambio
     */
    public int getCambio(){
        return cambio;
    }

    /**
     * Imposta il valore della velocita', in base al valore passato come parametro
     *
     * @param velocita valore della velocita' passato come parametro
     */
    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }

    /**
     * Imposta il colore, in base al valore passato come parametro
     *
     * @param colore valore del colore passato come parametro
     */
    public void setColore(String colore) {
        this.colore = colore;
    }

    /**
     * Imposta il valore del cambio, in base al valore passato come parametro
     *
     * @param newCambio valore del cambio passato come parametro
     */
    public void setCambio(int newCambio) {
        this.cambio = newCambio;
    }

    /**
     * Riduce la velocita' in base al valore passato come parametro
     *
     * @param decremento valore per ridurre la velocita'
     */
    public void frena(int decremento){
        velocita-=decremento;
    }

    /**
     * Restituisce una stringa con i dati di colore e velocita' della bicicletta
     *
     * @return Stringa con i valori di colore e velocita'
     */
    public String toString(){
        return ("Sono una bici "+getColore()+" e la mia velocità é pari a "+getVelocita());
    }

}
