package compravendita;

import java.io.Serial;
import java.io.Serializable;

public class Risposta implements Serializable,Comparable<Risposta> {

    @Serial
    private static final long serialVersionUID = 1L;
    public static final String DELIMITER = ";";

    Richiesta oRichiesta;

    private int iPrezzoTotale;

    public Risposta(Richiesta oRichiesta, int iPrezzoTotale) {
        this.oRichiesta = oRichiesta;
        setPrezzoTotale(iPrezzoTotale);
    }

    public int getPrezzoTotale() {
        return iPrezzoTotale;
    }

    public void setPrezzoTotale(int iPrezzoTotale) {
        this.iPrezzoTotale = iPrezzoTotale;
    }

    @Override
    public String toString() {

        return oRichiesta.getiIdProdotto() +
                DELIMITER +
                oRichiesta.getQuantita() +
                DELIMITER +
                iPrezzoTotale;
    }

    @Override
    public int compareTo(Risposta oRisposta) {
        return Integer.compare(iPrezzoTotale, oRisposta.iPrezzoTotale);
    }
}
