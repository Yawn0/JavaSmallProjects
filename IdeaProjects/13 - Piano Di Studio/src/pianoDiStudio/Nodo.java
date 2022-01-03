package pianoDiStudio;
/**
 * Classe Nodo , utilizzata per definire i nodi della lista
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
class Nodo {
    private Esame info;
    private Nodo nodo;

    /**
     * Crea un nuovo nodo con la parte informativa e un link di valore null
     *
     * @param esame Esame appartenente al piano di studio
     */
    public Nodo(Esame esame) {
        setInfo(esame);
        setNodo(null);
    }

    /**
     * Imposta la parte informativa del nodo
     *
     * @param esame parte informativa del nodo
     */
    public void setInfo(Esame esame) {
        info = new Esame(esame);
    }

    /**
     * Restituisce la parte informativa di un nodo
     *
     * @return parte informativa del nodo su cui viene invocato il metodo
     */
    public Esame getInfo() {
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
     * ritorna il nodo a cui punta il Nodo su cui viene invocato
     *
     * @return nodo a cui punta
     */
    public Nodo getNodo() {
        return nodo;
    }
}
