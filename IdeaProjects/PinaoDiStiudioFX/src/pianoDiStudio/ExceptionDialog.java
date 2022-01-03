package pianoDiStudio;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Classe per gestire le eccezioni dell'applicazione
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class ExceptionDialog extends Exception{

    private String errorMessage;

    /**
     * Istanzia un nuovo Exception dialog.
     *
     * @param errorMessage messaggio d'errore da visualizzare
     */
    ExceptionDialog(String errorMessage){
        setErrorMessage(errorMessage);
        showAlert();
    }

    /**
     * Crea l'alert per visualizzare l'errore e la corrispettiva stacktrace
     * (errore dimostratico nella classe GestioneEsami.login)
     */

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE");
        alert.setHeaderText("Brutte robe");
        alert.setContentText(getErrorMessage());

        // crea una expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Exception stacktrace:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // imposta la expandable Exception nel dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message
     *
     * @param errorMessage the error message
     */

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
