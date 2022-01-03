package formula1;

import java.time.*;

/**
 * Esercizio sul package formula1 e le relative classi : Pilota , AutoDaCorsa , ClassificaAutoDaCorsa<br>
 * Classe Pilota
 * @author Leonardo Tocchet
 * @version 1.0
 */

public class Pilota {

    private String nome;
    private String cognome;
    private String nazione;
    private LocalDate dataNascita;

    /**
     * Istanzia un nuovo pilota, a cui assegna i valori passati come parametri
     *
     * @param nome        nome del pilota
     * @param cognome     cognome del pilota
     * @param nazione     nazionalita' del pilota
     * @param dataNascita data di nascita del pilota
     */
    public Pilota(String nome, String cognome, String nazione, LocalDate dataNascita){
        setNome(nome);
        setCognome(cognome);
        setNazione(nazione);
        setDataNascita(dataNascita);
    }

    /**
     * Imposta il nome del pilota,in base alla stringa passata come paramentro
     * @param nome nome del pilota
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta il cognome del pilota,in base alla stringa passata come paramentro
     * @param cognome cognome del pilota
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Imposta la nazionalita' del pilota,in base alla stringa passata come paramentro
     * @param nazione nazionalita' del pilota
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * Imposta la data di nascita del pilota,in base alla data passata come paramentro
     * @param dataNascita data di nascita del pilota formato 'yyyy - mm - dd'
     */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce la stringa del nome del pilota
     * @return nome del pilota
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce la stringa del cognome del pilota
     * @return cognome del pilota
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Restituisce la stringa della nazionalita' del pilota
     * @return nazionalita' del pilota formato 'NN'
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Restituisce la data di nascita del pilota
     * @return data di nascita del pilota formato 'yyyy - mm - dd'
     */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Restituisce una stringa che presenta il pilota
     * @return stringa con i dati del pilota compresa l'eta'
     */
    public String presentaPilota(){
        return ("Pilota : "+toString()+"     età: "+(LocalDate.now().getYear()-getDataNascita().getYear()));
    }

    /**
     * Restituisce una stringa con nome, cognome, nazionalita'
     * @return stringa con i dati del pilota senza l'eta'
     */

    public String toString(){
        return (getNome()+" "+getCognome()+" "+ getNazione());
    }
}
