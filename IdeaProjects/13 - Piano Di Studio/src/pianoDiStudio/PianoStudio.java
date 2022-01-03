package pianoDiStudio;

/**
 * Classe PianoStudio<br>
 * Rappresenta una lista composta di esami
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class PianoStudio {
    private Nodo head;
    private int elementi;

    /**
     * Crea una lista Viaggio vuota
     */
    public PianoStudio() {
        head = null;
        elementi = 0;
    }

    /**
     * Ritorna la posizione del nodo della lista interessato
     *
     * @param posizione posizione del nodo della lista
     * @return nodo della lista
     * @exception PianoStudioException eccezione che puo' avvenire in caso di lista vuota<br>
     *                                  o se viene cercato un nodo inesistente
     */

    private Nodo getLinkPosizione(int posizione) throws PianoStudioException {
        int n = 1;
        Nodo p = head;
        if (head == null)
            throw new PianoStudioException("Lista vuota");
        if ((posizione > elementi) || (posizione < 1))
            throw new PianoStudioException("Posizione errata");
        while ((p.getNodo() != null) && (n < posizione)) {
            p = p.getNodo();
            n++;
        }
        return p;
    }

    /**
     * Ritorna la posizione dell' ultimo nodo della lista
     *
     * @return ultimo nodo della lista
     */

    private Nodo getLinkPosizione() {
        Nodo p = head;
        while (p.getNodo() != null)
            p = p.getNodo();

        return p;
    }

    /**
     * Inseriscie il primo esame della lista(PianoStudio)
     *
     * @param esame esame da aggiungere
     */
    private void inserisciInTesta(Esame esame) {
        head = new Nodo(esame);
        elementi++;
    }

    /**
     * Inserisce un nuovo esame nella lista(PianoStudio)
     *
     * @param esame esame da aggiungere alla lista
     */
    public void aggiungiEsame(Esame esame) {
        if (elementi == 0)
            inserisciInTesta(esame);
        else {
            Nodo p = getLinkPosizione();
            p.setNodo(new Nodo(esame));
            elementi++;
        }
    }

    /**
     * Elimina il primo esame della lista(PianoStudio)
     *
     * @exception PianoStudioException errore possibile se la lista é vuota
     */

    private void eliminaInTesta() throws PianoStudioException {
        if (head == null)
            throw new PianoStudioException("Lista vuota");

        head = head.getNodo();
        elementi--;
    }

    /**
     * Elimina un esame in base alla posizione data
     *
     * @param posizione esame da eliminare
     * @exception PianoStudioException errore possibile se l'esame da eliminare non esiste
     */
    public void eliminaEsame(int posizione) throws PianoStudioException {
        if (posizione == 1)
            eliminaInTesta();

        else {
            try {
                Nodo ps = getLinkPosizione(posizione);
                Nodo pp = getLinkPosizione(posizione - 1);
                pp.setNodo(ps.getNodo());
                elementi--;
            } catch (PianoStudioException e) {
                System.err.println("Si é verificato un errore");
            }
        }
    }

    /**
     * Visualizza la lista di esami (in modo ricorsivo)<br>
     * attraverso l'utilizzo del metodo ' elenco() '
     *
     * @param p esame da visualizzare
     * @return stringa con le informazioni dell'esame
     */

    private String visita(Nodo p) {
        if (p == null)
            return "";
        return p.getInfo().toString() + "\n" + visita(p.getNodo());
    }

    /**
     * Visualizza la lista degli esami (in modo ricorsivo)<br>
     * attraverso l'utilizzo del metodo ' visita() '
     *
     * @return stringa con le informazioni dell'esame
     */
    public String elenco() {
        return visita(head);
    }

    /**
     * Crea la visualizzazione della lista(PianoStudio)<br>
     * di nodi(esami)
     *
     * @return Stringa rappresentante la lista
     */

    public String toString() {
        Nodo p = head;
        String lista = "head->";
        if (p == null)
            return lista + "null";
        while (p != null) {
            lista = lista + " [ " + p.getInfo().toString() + " | ";
            if (p.getNodo() == null)
                lista = lista + "null ]";
            else
                lista = lista + "+ ] ->";
            p = p.getNodo();
        }
        return lista;
    }
}
