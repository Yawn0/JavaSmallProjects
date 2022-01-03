package albergo;

/**
 * classe Chiave dipendente dalla classe Albergo<br>
 * ogni chiave rappresenta una camera d'albergo
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Chiave {

    private String nome;
    private final int numero;

    /**
     * Istanzia una chiave senza nome e con il numero passato come parametro
     *
     * @param numero numero da assegnare alla chiave
     */
    public Chiave(int numero) {
        setNome("");
        this.numero = numero;
    }

    /**
     * Assegna il valore al parametro numero
     *
     * @param s definisce il nome assegnato alla chiave
     */
    public void setNome(String s) {
        nome = s;
    }

    /**
     * ritorna il la stringa rappresentante il nome associato alla chiave
     *
     * @return nome associato alla chiave
     */
    public String getNome() {
        return nome;
    }

    /**
     * ritorna il numero associato alla chiave
     *
     * @return numero associato alla chiave
     */
    public int getNum() {
        return numero;
    }

    /**
     * Ritorna una stringa con il numero e il nome della chiave
     * @return stringa con nome e numero
     */

    @Override
    public String toString() {
        return (getNum() + " " + getNome());
    }
}
