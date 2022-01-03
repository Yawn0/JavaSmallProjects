package pianoDiStudio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Random;

/**
 * Controller dello stage principale dell'applicazione
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class TabellaEsamiController {

    @FXML public Button closeButton;
    @FXML Button buttonCancel;
    @FXML private BarChart esamiChartLine;
    @FXML private Label LblMessaggioSalva;

    @FXML private TableView<Esame> tabellaEsami;
    @FXML private TableColumn<Esame, String> colonnaMateria;
    @FXML private TableColumn<Esame, Integer> colonnaCrediti;
    @FXML private TableColumn<Esame, String> colonnaDocente;

    @FXML private TextField campoMateria;
    @FXML private TextField campoCrediti;
    @FXML private TextField campoDocente;

    @FXML private Label LblMessaggioNuovoEsame;

    /**
     * metodo per l'inizializzazione dello stage
     */

    @FXML
    private void initialize() {
        tabellaEsami.setItems(GestioneEsami.getEsami());
        colonnaMateria.setCellValueFactory(new PropertyValueFactory<Esame, String>("materia"));
        colonnaCrediti.setCellValueFactory(new PropertyValueFactory<Esame, Integer>("crediti"));
        colonnaDocente.setCellValueFactory(new PropertyValueFactory<Esame, String>("docente"));
        campoMateria.setPromptText("Inserisci materia");
        campoCrediti.setPromptText("Inserisci crediti");
        campoDocente.setPromptText("Inserisci docente");
        gestioneEsamiChartLine();
    }

    /**
     * Metodo di gestione evento di aggiunta nuovo esame<br>
     * Con validazione dati prima dell'inserimento
     *
     * @param mouseEvent click botttone di aggiunta esame
     */
    public void handleNuovoEsame(MouseEvent mouseEvent){
        String esame = campoMateria.textProperty().getValue();
        String crediti = campoCrediti.textProperty().getValue();
        String docente = campoDocente.textProperty().getValue();
        if (validaForm(esame, crediti, docente)) {
            Random random = new Random(new Date().getTime());
            int idNuovoEsame = random.nextInt();
            Esame exam = new Esame(idNuovoEsame, esame.toUpperCase(), Integer.parseInt(crediti), docente);
            GestioneEsami.aggiungiEsame(exam);
            campoMateria.clear();
            campoCrediti.clear();
            campoDocente.clear();
        }
    }

    /**
     * metodo per la gestione della visualizzazione dl grafico degli esami<br>
     * (missione complessa implementare grafici, anche se non sembra)
     */
    public void gestioneEsamiChartLine(){

        esamiChartLine.getYAxis().setLabel("Voto");
        esamiChartLine.getXAxis().setLabel("Esami");

        XYChart.Series dataSerie1 = new XYChart.Series();
        dataSerie1.setName("De Villa");

        XYChart.Series dataSerie2 = new XYChart.Series();
        dataSerie2.setName("Fernández");

        XYChart.Series dataSerie3 = new XYChart.Series();
        dataSerie3.setName("Tichete");

        XYChart.Series dataSerie4 = new XYChart.Series();
        dataSerie4.setName("Tachi");

        XYChart.Series dataSerie5 = new XYChart.Series();
        dataSerie5.setName("Tac");

        for(Esame esame : GestioneEsami.pianoDiStudio){
            switch (esame.getDocente()){
                case "De Villa" -> dataSerie1.getData().add(new XYChart.Data(esame.getMateria(),esame.getCrediti()));
                case "Fernández" -> dataSerie2.getData().add(new XYChart.Data(esame.getMateria(),esame.getCrediti()));
                case "Tichete" -> dataSerie3.getData().add(new XYChart.Data(esame.getMateria(),esame.getCrediti()));
                case "Tachi" -> dataSerie4.getData().add(new XYChart.Data(esame.getMateria(),esame.getCrediti()));
                case "Tac" -> dataSerie5.getData().add(new XYChart.Data(esame.getMateria(),esame.getCrediti()));
            }
        }

        esamiChartLine.getData().addAll(dataSerie1,dataSerie2,dataSerie3,dataSerie4,dataSerie5);
    }

    /**
     * Metodo di gestione evento di salvataggio su file del piano di studio
     *
     * @param mouseEvent click del pulsante di salvataggio
     */
    public void handleSalvaPianoDiStudio(MouseEvent mouseEvent) throws ExceptionDialog {
        GestioneEsami.salvaPianoDiStudio();
    }

    /**
     * Metodo di gestione evento reset campi di testo dati nuovo esame
     * @param mouseEvent click bottone cancel
     */

    public void handleButtonCancel(ActionEvent mouseEvent){
        campoMateria.clear();
        campoCrediti.clear();
        campoDocente.clear();
    }

    /**
     * Metodo di gestione evento chiusura applicazione
     * @param actionEvent click bottone di chiusura applicazione
     */

    @FXML
    private void closeButtonAction(MouseEvent actionEvent){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo di gestione evento di modifica esame
     *
     * @param mouseEvent click del pulsante di modifica esame
     */
    public void handleModificaEsame(MouseEvent mouseEvent) {
        Esame esameSelezionato = tabellaEsami.getSelectionModel().getSelectedItem();
        if (esameSelezionato == null)
            return;
        GestioneEsami.mostraPopupModifica(esameSelezionato);
    }

    /**
     * Metodo di gestione evento di eliminazione esame
     *
     * @param mouseEvent click del pulsante di eliminazione esame
     */
    public void handleEliminaEsame(MouseEvent mouseEvent) {
        Esame esameSelezionato = tabellaEsami.getSelectionModel().getSelectedItem();
        if (esameSelezionato == null) {
            return;
        }
        GestioneEsami.rimuoviEsame(esameSelezionato);
    }

    /**
     * metodo utilizzato per controllare le informazioni dell'esame da aggiungere
     * @param materia nome materia dell'esame
     * @param crediti crediti dell'esame
     * @param docente docente dell'esame
     * @return informazioni valide ? true : false
     */

    private boolean validaForm(String materia, String crediti, String docente) {
        boolean valido = true;
        String ok = " Nuovo esame aggiunto correttamente :) ";
        String msg = "";
        if (materia == null || materia.isEmpty()) {
            msg += "Inserisci materia\n";
            valido = false;
        }
        if (crediti == null || crediti.isEmpty() || !crediti.matches("\\d{1,2}")) {
            msg += "Inserisci crediti\n";
            valido = false;
        }
        if (docente == null || docente.isEmpty()) {
            msg += "Inserisci docente\n";
            valido = false;
        }
        LblMessaggioNuovoEsame.setText(valido ? ok : msg);
        return valido;
    }
}
