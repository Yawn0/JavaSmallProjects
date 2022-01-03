package pianoDiStudio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

/**
 * Controller del popup di modifica dell'esame selezionato
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class ModificaEsamiController {

    /**
     * Messaggio di validazione utilizzato per comunicare l'avvenuta modifica dell'esame
     */
    public Label messaggioValidazione;
    private Esame esame;

    @FXML private TextField campoMateria;
    @FXML private TextField campoCrediti;
    @FXML private TextField campoDocente;

    /**
     * Start.
     */
    @FXML
    public void start() {}

    /**
     * Metodo gestione evento salvataggio modifiche esame.
     *
     * @param mouseEvent click bottone di salvataggio
     */
    public void handleSalvaModifiche(MouseEvent mouseEvent) {
        String materia = campoMateria.textProperty().get();
        String crediti = campoCrediti.textProperty().get();
        String docente = campoDocente.textProperty().get();
        if (validaForm(materia, crediti, docente)) {
            esame.setMateria(materia.toUpperCase());
            esame.setCrediti(Integer.parseInt(crediti));
            esame.setDocente(docente);
            GestioneEsami.modificaEsame(esame);
        }
    }

    /**
     * metodo utilizzato per controllare le informazioni da aggiornare
     * @param materia nome materia dell'esame
     * @param crediti crediti dell'esame
     * @param docente docente dell'esame
     * @return informazioni valide ? true : false
     */

    private boolean validaForm(String materia, String crediti, String docente) {
        boolean valido = true;
        String msg = "";
        String ok = "Modifiche salvate con successo";
        if (materia == null || materia.isEmpty()) {
            msg += "Inserisci materia\n";
            valido = false;
        }
        if (crediti == null || crediti.isEmpty() || !crediti.matches("\\d{1,2}")) {
            msg += "Inserisci crediti\n";
            valido = false;
        }
        if (docente == null || docente.isEmpty()) {
            msg += "Inserisci Docente\n";
            valido = false;
        }
        messaggioValidazione.setText(valido ? ok : msg);
        return valido;
    }

    /**
     * Imposta i valori testuali dei campi da modificare
     *
     * @param esame esame selezionato da cui prendere i valori
     */
    public void setEsame(Esame esame) {
        this.esame = esame;
        campoMateria.textProperty().setValue(esame.getMateria());
        campoCrediti.textProperty().setValue(String.valueOf(esame.getCrediti()));
        campoDocente.textProperty().setValue(esame.getDocente());
    }
}
