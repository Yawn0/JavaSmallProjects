package compravendita;

import java.io.Serial;
import java.io.Serializable;

public class Risposta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    public static final String DELIMITER = ";";

    Richiesta oRichiesta;
    int iPrezzoTotale;
    int iIdIntermediario;

    public Risposta(Richiesta oRichiesta, int iPrezzoTotale, int iIdIntermediario) {
        this.oRichiesta = oRichiesta;
        this.iPrezzoTotale = iPrezzoTotale;
        this.iIdIntermediario = iIdIntermediario;
    }

    @Override
    public String toString() {

        return oRichiesta.getId() +
                DELIMITER +
                oRichiesta.getQuantita() +
                DELIMITER +
                iPrezzoTotale +
                DELIMITER +
                iIdIntermediario;
    }
}
