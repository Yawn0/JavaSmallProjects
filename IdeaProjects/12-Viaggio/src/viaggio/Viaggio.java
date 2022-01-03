package viaggio;

/**
 * Classe viaggio<br>
 * Rappresenta una lista composta di Viaggiatori
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Viaggio {
    private Nodo head;
    private int elementi;

    /**
     * Crea una lista Viaggio vuota
     */
    public Viaggio() {
        head = null;
        elementi = 0;
    }

    /**
     * Ritorna la posizione del nodo della lista interessato
     *
     * @param posizione posizione del nodo della lista
     * @return nodo della lista
     * @exception ViaggioException eccezione che puo' avvenire in caso di lista vuota<br>
     *                             o se viene cercato un nodo inesistente
     */

    private Nodo getLinkPosizione(int posizione) throws ViaggioException {
        int n = 1;
        Nodo p = head;
        if (head == null)
            throw new ViaggioException("Lista vuota");
        if ((posizione > elementi) || (posizione < 1))
            throw new ViaggioException("Posizione errata");
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
     * Inseriscie il primo viaggiatore della lista(viaggio)
     *
     * @param persona viaggiatore da aggiungere
     */
    public void inserisciInTesta(Viaggiatore persona) {
        head = new Nodo(persona);
        elementi++;
    }

    /**
     * Inserisce un nuovo viaggiatore nella lista(viaggio)
     *
     * @param persona viaggiatore da aggiungere alla lista
     */
    public void prenotazione(Viaggiatore persona) {
        if (elementi == 0)
            inserisciInTesta(persona);
        else {
            Nodo p = getLinkPosizione();
            p.setNodo(new Nodo(persona));
            elementi++;
        }
    }

    /**
     * Elimina il primo viaggiatore della lista(viaggio)
     *
     * @exception ViaggioException errore possibile se la lista é vuota
     */

    private void eliminaInTesta() throws ViaggioException {
        if (head == null)
            throw new ViaggioException("Lista vuota");

        head = head.getNodo();
        elementi--;
    }

    /**
     * Elimina un viaggiatore in base alla posizione data
     *
     * @param posizione viaggiatore da eliminare
     * @exception ViaggioException errore possibile se il viaggiatore da eliminare non esiste
     */
    public void eliminaPrenotazione(int posizione) throws ViaggioException {
        if (posizione == 1)
            eliminaInTesta();

        else {
            try {
                Nodo ps = getLinkPosizione(posizione);
                Nodo pp = getLinkPosizione(posizione - 1);
                pp.setNodo(ps.getNodo());
                elementi--;
            } catch (ViaggioException e) {
                System.err.println("Si é verificato un errore");
            }
        }
    }

    /**
     * Visualizza la lista di viaggiatori (in modo ricorsivo)<br>
     * attraverso l'utilizzo del metodo ' elenco() '
     *
     * @param p viaggiatore da visualizzare
     * @return stringa con le informazioni del viaggiatore
     */

    private String visita(Nodo p) {
        if (p == null)
            return "";
        return p.getInfo().toString() + "\n" + visita(p.getNodo());
    }

    /**
     * Visualizza la lista di viaggiatori (in modo ricorsivo)<br>
     * attraverso l'utilizzo del metodo ' visita() '
     *
     * @return stringa con le informazioni del viaggiatore
     */
    public String elenco() {
        return visita(head);
    }

    /**
     * Crea la visualizzazione della lista(viaggio)<br>
     * di nodi(viaggiatori)
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