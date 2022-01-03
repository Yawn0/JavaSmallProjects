package viaggio;

/**
 * Classe viaggiatore, ognuno con codice,nome e numero di telefono
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Viaggiatore {

    private int codice;
    private String nome;
    private String telefono;

    /**
     * Crea un nuovo Viaggiatore
     *
     * @param nome     nome del viaggiatore
     * @param codice   codice identificativo del viaggiatore
     * @param telefono numero di telefono del viaggiatore
     */
    public Viaggiatore(String nome, int codice, String telefono) {
        setNome(nome);
        setTelefono(telefono);
        setCodice(codice);
    }

    /**
     * Crea un nuovo viaggiatore copiando le informazioni di un altro Viaggiatore
     *
     * @param viaggiatore viaggiatore da cui copia le informazioni
     */
    public Viaggiatore(Viaggiatore viaggiatore) {
        setNome(viaggiatore.getNome());
        setTelefono(viaggiatore.getTelefono());
        setCodice(viaggiatore.getCodice());
    }

    /**
     * Imposta il nome del viaggiatore
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta il codice del viaggiaotre
     *
     * @param codice the codice
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }

    /**
     * Ritorna il nome del viaggiatore
     *
     * @return nome del viaggiatore
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il telefono del viaggiatore
     *
     * @param telefono telefono del viaggiatore
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * ritorna il codice del viaggiatore
     *
     * @return codice del viaggiatore
     */
    public int getCodice() {
        return codice;
    }

    /**
     * ritorna il telefono del viaggiatore
     *
     * @return telefono del viaggiatore
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Crea una stringa con le informazioni del viaggiatore
     *
     * @return nome + codice + numero di telefono
     */

    public String toString() {
        return getNome() + " _ " + getCodice() + " _ " + getTelefono();
    }
}