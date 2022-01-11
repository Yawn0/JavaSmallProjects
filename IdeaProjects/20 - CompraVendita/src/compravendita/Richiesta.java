package compravendita;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Richiesta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int iIdProdotto;
    private int iQuantita;
    private Date oData;

    public Richiesta(int iIdProdotto) {
        setId(iIdProdotto);
    }

    public Richiesta(int iIdProdotto, int iQuantita){
        setId(iIdProdotto);
        setQuantita(iQuantita);
        oData = new Date();
    }

    public int getQuantita() {
        return iQuantita;
    }

    public void setQuantita(int iQuantita) {
        this.iQuantita = iQuantita;
    }

    public int getiIdProdotto() {
        return iIdProdotto;
    }

    public void setId(int iId) {
        this.iIdProdotto = iId;
    }

    public Date getDate() {
        return oData;
    }

    @Override
    public boolean equals(Object oObject) {
        if(!(oObject instanceof Richiesta oRichiesta))
            return false;

        if(this == oObject)
            return true;

        return iIdProdotto == oRichiesta.getiIdProdotto() && iQuantita == oRichiesta.getQuantita() ;
    }

    @Override
    public String toString() {
        return getiIdProdotto() + ";" + getQuantita();
    }
}
