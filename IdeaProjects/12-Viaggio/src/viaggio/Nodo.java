package viaggio;

/**
 * Classe Nodo , utilizzata per definire i nodi della lista
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
class Nodo {
    private Viaggiatore info;
    private Nodo nodo;

    /**
     * Crea un nuovo nodo con la parte informativa e un link di valore null
     *
     * @param persona Viaggiatore partecipante al viaggio
     */
    public Nodo(Viaggiatore persona) {
        setInfo(persona);
        setNodo(null);
    }

    /**
     * Imposta la parte informativa del nodo
     *
     * @param persona parte informativa del nodo
     */
    public void setInfo(Viaggiatore persona) {
        info = new Viaggiatore(persona);
    }

    /**
     * Restituisce la parte informativa di un nodo
     *
     * @return parte informativa del nodo su cui viene invocato il metodo
     */
    public Viaggiatore getInfo() {
        return info;
    }

    /**
     * Imposta il link del nodo
     *
     * @param nodo nodo a cui punta questo nodo
     */
    protected void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    /**
     * ritorna il nodo a cui punta
     *
     * @return nodo a cui punta
     */
    public Nodo getNodo() {
        return nodo;
    }
}