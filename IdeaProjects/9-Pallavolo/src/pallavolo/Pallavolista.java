package pallavolo;

/**
 * Classe Pallavolista<br>
 *     rappresentante un pallavolista con nome,numero e ruolo
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Pallavolista {
    private String nome;
    private int numero;
    private String ruolo;
    private boolean titolare;

    /**
     * Instanzia un nuovo Pallavolista.
     *
     * @param nome     nome del pallavolista
     * @param numero   numero maglia del pallavolista
     * @param ruolo    ruolo del pallavolista
     * @param titolare identifica i giocatori in campo
     */
    public Pallavolista(String nome, int numero, String ruolo,boolean titolare){
        setNome(nome);
        impostaNumeroMaglia(numero);
        setRuolo(ruolo);
        this.titolare = titolare;
    }

    /**
     * Metodo per deteriminare se un giocatore é in campo.
     *
     * @return valore booleano che determina se il giocatore è titolare o meno.
     */
    public boolean isTitolare(){
        return titolare;
    }

    /**
     * cambia il valore dell’attributo titolare (se e' true lo imposta a false,
     * altrimenti lo imposta a true);
     */
    public void cambiaTitolare(){
        this.titolare = !this.titolare;
    }

    /**
     * Imposta il numero maglia con il valore del parametro passato in input.
     *
     * @param n numero dato in input
     */
    public void impostaNumeroMaglia(int n){
        this.numero = n;
    }

    /**
     * Restituizce una stringa con nome, numero e ruolo del pallavolista
     *
     * @return Stringa con dati del pallavolista
     */
    public String toString(){
        return (getNome()+" ; "+getNumero()+" ; "+getRuolo());
    }

    /**
     * Restuituisce il nome del pallavolista
     *
     * @return nome del pallavolista
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del pallavolista
     *
     * @param nome stringa data in input
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il numero maglia del pallavolista
     *
     * @return numero maglia
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Imposta il ruolo del pallavolista
     *
     * @param ruolo Stringa data in input
     */
    public void setRuolo(String ruolo){
        this.ruolo = ruolo;
    }

    /**
     * Restituisce il ruolo del protagonista
     *
     * @return Stringa che specifica il ruolo del pallavolista
     */
    public String getRuolo() {
        return ruolo;
    }
}
