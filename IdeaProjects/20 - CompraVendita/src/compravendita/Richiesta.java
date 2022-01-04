package compravendita;

public class Richiesta {
    private int iId;
    private int iQuantita;

    public Richiesta(int iId) {
        setId(iId);
    }

    public int getQuantita() {
        return iQuantita;
    }

    public void setQuantita(int iQuantita) {
        this.iQuantita = iQuantita;
    }

    public int getId() {
        return iId;
    }

    public void setId(int iId) {
        this.iId = iId;
    }

    @Override
    public String toString() {
        return "Richiesta{" +
                "iId=" + getId() +
                ", iQuantita=" + getQuantita() +
                '}';
    }

    @Override
    public boolean equals(Object oObject) {
        if(!(oObject instanceof Richiesta oRichiesta))
            return false;

        if(this == oObject)
            return true;

        return iId == oRichiesta.getId() && iQuantita == oRichiesta.getQuantita() ;
    }
}
