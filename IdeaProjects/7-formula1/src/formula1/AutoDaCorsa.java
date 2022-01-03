package formula1;

/**
 * Esercizio sul package formula1 e le relative classi : Pilota , AutoDaCorsa , ClassificaAutoDaCorsa<br>
 * Classe AutoDaCorsa
 * @author Leonardo Tocchet
 * @version 1.0
 */

public class AutoDaCorsa {
    private String scuderia;
    private int maxVel;
    private Pilota pilota;

    /**
     * Istanzia una nuova auto da corsa, a cui assegna i valori passati come parametri
     *
     * @param scuderia nome della scuderia a cui appartiene l'auto
     * @param maxVel   velocita' massima dell'auto
     * @param pilota   pilota che viene assegnato all'auto
     */
    public AutoDaCorsa(String scuderia, int maxVel, Pilota pilota){
        setScuderia(scuderia);
        setMaxVel(maxVel);
        setPilota(pilota);
    }

    /**
     * Imposta la scuderia, in base al valore passato come parametro
     *
     * @param scuderia stringa passata come paramentro
     */
    public void setScuderia(String scuderia) {
        this.scuderia = scuderia;
    }

    /**
     * Imposta la velocita' massima, in base al valore passato come parametro
     *
     * @param maxVel velocita' massima dell'auto
     */
    public void setMaxVel(int maxVel) {
        this.maxVel = maxVel;
    }

    /**
     * Imposta il pilota, in base al valore passato come parametro
     *
     * @param pilota oggetto pilota appartenente all'auto
     */
    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    /**
     * Restituisce la stringa con il nome della scuderia
     *
     * @return stringa col nome della scuderia
     */
    public String getScuderia() {
        return scuderia;
    }

    /**
     * Restituisce il valore della velocita' massima dell'auto
     *
     * @return valore della velocita' massima
     */
    public int getMaxVel() {
        return maxVel;
    }

    /**
     * Restituisce l'oggetto Pilota dell'auto
     *
     * @return oggetto di tipo Pilota dell'auto
     */
    public Pilota getPilota() {
        return pilota;
    }

    /**
     * Restituisce una stringa con i dati dell'auto esclusi quelli del pilota
     *
     * @return stringa con scuderia e velocita' massima
     */

    public String toString(){
        return ("         "+getScuderia()+"         "+getMaxVel());
    }
}
