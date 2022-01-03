package pianoDiStudio;

/**
 * Classe Esame , utilizzata per definire la parte informativa dei nodi della lista
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Esame {
    private String nome;
    private int crediti;
    private String docente;

    /**
     * Crea una nuova istanza della calasse Esame
     *
     * @param nome    nome dell'esame
     * @param crediti numero crediti associati all'esame
     * @param docente nome del docente associato all'esame
     */
    public Esame(String nome,int crediti,String docente){
        setNome(nome);
        setCrediti(crediti);
        setDocente(docente);
    }

    /**
     * Crea una copia di un' istanza della classe esame
     *
     * @param esame
     */

    public Esame(Esame esame){
        setNome(esame.nome);
        setCrediti(esame.crediti);
        setDocente(esame.docente);
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets docente.
     *
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets crediti.
     *
     * @param crediti the crediti
     */
    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }

    /**
     * Sets docente.
     *
     * @param docente the docente
     */
    public void setDocente(String docente) {
        this.docente = docente;
    }

    @Override
    public String toString() {
        return "Esame{" +
                "nome='" + nome + '\'' +
                ", crediti=" + crediti +
                ", docente='" + docente + '\'' +
                '}';
    }
}
