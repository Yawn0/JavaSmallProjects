package pianoDiStudio;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import org.controlsfx.dialog.ProgressDialog;

import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * Classe per la logica di gestione delle informazioni dell'applicazione
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class GestioneEsami extends Application {

    /**
     * @param pianoDiStudio
     * lista contenente tutte le informazioni di tutti gli esami
     */
    public static final ObservableList<Esame> pianoDiStudio = FXCollections.observableArrayList();

    /**
     * @param popupStage
     * popup per modificare un esame
     */
    private static Stage popupStage;

    /**
     * @param popupController
     * controller associato al popup di modifica esami
     */
    private static ModificaEsamiController popupController;

    /**
     * @param FILE
     * file per gestire la persistenza delle informazioni degli esami
     */
    private static final String FILEPATH = "Esami.txt";

    /**
     * @param hasChanged
     * variabile per controllare se la lista é stata modificata
     */
    private static boolean hasChanged = false;

    /**
     * @param isLegit
     * variabile per controllare se il login ha avuto successo
     */
    private static boolean isLegit = false;
    private static boolean wantToClose = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public GestioneEsami() throws ExceptionDialog {
        caricaPianoDiStudio();
    }

    /**
     * Metodo da cui parte l'applicazione
     * @param StagePrimario primarystage dell'applicazione
     * @throws IOException possibile eccezione leggendo e scrivendo sul file
     * @throws ExceptionDialog classe per gestire le eccezioni dell'applicazione
     */

    @Override
    public void start(Stage StagePrimario) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tabellaEsami.fxml")));
        StagePrimario.setTitle("Gestionale piano di studio");
        Scene master = new Scene(root);
//        JMetro jMetro = new JMetro(Style.LIGHT);
//        jMetro.setScene(master);
        StagePrimario.setScene(master);

        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modificaEsame.fxml"));
        Parent popupRoot = fxmlLoader.load();
        popupController = fxmlLoader.getController(); // salva il riferimento al controller del popup
        popupStage.setScene(new Scene(popupRoot));
        popupStage.setTitle("Modifica esame");

        /**
         * (DOCUMENTAZIONE ORACLE)
         * The second Stage has its modality set to Modality.APPLICATION_MODAL
         * meaning it will block all other windows (stages) opened by this JavaFX application.
         * You cannot access any other windows until this Stage window has been closed.
         */
        popupStage.initModality(Modality.APPLICATION_MODAL);

        popupStage.initOwner(StagePrimario);
        StagePrimario.setResizable(false);

        //aggiungo icona allo stage primario
        ((Stage)StagePrimario.getScene()
                            .getWindow())
                            .getIcons()
                            .add(new Image(this.getClass()
                                                .getResource("login.png")
                                                .toString()));

        Window window =  StagePrimario.getScene().getWindow();

        //aggiungo evento allo stage primario per controllare di aver salvato
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
        StagePrimario.getScene()
                     .getWindow()
                     .addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        //controllo dati login prima di avviare lo stage primario
        do{
            try {
                if(login()){
                    StagePrimario.show();
                }
            } catch (Exception error) {
                this.stop();
                throw new ExceptionDialog("Errore durante il login");
            }
        }while(!isLegit && !wantToClose);
    }

    /**
     * metodo utilizzato per creare la finestra di login
     * @return login confermato ? true : false
     * @throws ExceptionDialog eccezioni gestite con la mia classe :)
     */

    private boolean login() throws ExceptionDialog {

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Login");
        dialog.setHeaderText("Inserisci username e password");

        // imposto l'immagine di sfondo
        ImageView imageView = new ImageView(this.getClass().getResource("login.png").toString());
        imageView.setFitWidth(75);
        imageView.setFitHeight(70);
        dialog.setGraphic(imageView);

        // imposto l'icona della finestra
        ((Stage) dialog.getDialogPane()
                        .getScene()
                        .getWindow())
                            .getIcons()
                            .add(new Image(this.getClass()
                                                .getResource("login.png")
                                                .toString()));

        // imposto i bottoni
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButtonType = new ButtonType("Chiudi", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(loginButtonType, closeButtonType);

        // creo username e password label fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.getDialogPane().setContent(grid);

        //disabilito il pulsante di login
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // abilito\disabilito il pulsante di login se si é scritto qualcosa nel textfield dello username
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        //imposto il focus sullo username textfield
        Platform.runLater(username::requestFocus);

        Optional<ButtonType> result = dialog.showAndWait();
        //se ho premuto qualche bottone
        if(result.isPresent()){
            if(result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                final String name = username.textProperty().getValue().trim();
                final String pass = password.textProperty().getValue().trim();
                if(name.equals("leo") && pass.equals("123")){
                    isLegit = true;
                }
            }
            else{
                wantToClose = true;
            }
        }
        if(isLegit){
            //creo un Task
            //per gestire il popup di caricamento
            Task caricamento = new Task() {
                @Override
                protected Object call() throws InterruptedException {
                    updateMessage("Ricerca esami  . . .");
                    Thread.sleep(500);
                    updateProgress(0, 10);
                    for (int i = 0; i < pianoDiStudio.size(); i++) {
                        Thread.sleep((pianoDiStudio.size() * 50L)/pianoDiStudio.size());
                        updateProgress(i + 1,pianoDiStudio.size());
                        updateMessage((i + 1) + " esami trovati !");
                    }
                    Thread.sleep(500);
                    return true;
                }
            };

            ProgressDialog progressDialog = new ProgressDialog(caricamento);
            progressDialog.setContentText(". . .");
            progressDialog.setTitle("Caricamento");
            progressDialog.setHeaderText("Caricamento");
            progressDialog.initStyle(StageStyle.TRANSPARENT);

            try{
                new Thread(caricamento).start();
                progressDialog.showAndWait();
            }catch (Exception e){
                throw new ExceptionDialog("Errore visualizzazione caricamento");
            }
        }
//        if(!dialog.getDialogPane().lookupButton(closeButtonType).isPressed()){
//            throw new ExceptionDialog("Errore dimostrativo");
//        }

        return isLegit;
    }

    /**
     * metodo per gestire la chiusura dell'applicazione
     * @param event evento di chiusura applicazione
     */

    private void closeWindowEvent(WindowEvent event) {

        if(hasChanged) {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.getButtonTypes().setAll(ButtonType.CANCEL,ButtonType.YES);
            dialog.setContentText("Sicuro di non voler salvare?");
            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent()) {
                if(result.get().equals(ButtonType.CANCEL))
                    event.consume();
            }
            dialog.close();
        }
    }

    /**
     * Mostra popup modifica esame
     *
     * @param esame esame da modificare
     */
    public static void mostraPopupModifica(Esame esame) {
        popupController.setEsame(esame);
        popupStage.showAndWait();
    }

    /**
     * ritorna la lista con tutti gli esami
     * usata nell'interazione con il file FXML
     *
     * @return lista degli esammi visualizzati
     */
    public static ObservableList<Esame> getEsami() {
        return pianoDiStudio;
    }

    /**
     * aggiunge un esame alla lista degli esami visualizzati
     *
     * @param prova esame da aggiungere
     */
    public static void aggiungiEsame(Esame prova) {
        pianoDiStudio.add(prova);
        hasChanged = true;
    }

    /**
     * Salva il piano di studio sul file CSV predefinito
     */
    public static void salvaPianoDiStudio() throws ExceptionDialog {
        try {
            BufferedWriter writeR = new BufferedWriter(new FileWriter(FILEPATH));
            for(Esame esame : pianoDiStudio){
                writeR.write(esame.getMateria() + ";" + esame.getCrediti() + ";" + esame.getDocente() +"\n");
            }
            hasChanged = false;
            writeR.close();
        } catch (IOException error) {
            throw new ExceptionDialog("Errore nel salvataggio dati nel file predefinito");
        }
    }

    /**
     * Carica il piano di studio dal file CSV predefinito
     */
    public static void caricaPianoDiStudio() throws ExceptionDialog {

        Random random = new Random(new Date().getTime());
        String row;
        String[] dati;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));

            while((row = reader.readLine()) != null){
                dati = row.split(";");
                pianoDiStudio.add(new Esame(random.nextInt(),
                                            dati[0],
                                            Integer.parseInt(dati[1]),
                                            dati[2]));
            }
            reader.close();
        } catch (IOException error) {
            throw new ExceptionDialog("Errore nel caricamento dati del piano di studio");
        }
    }

    /**
     * Rimuove un esame dalla lista visualizzata.
     *
     * @param prova esame da rimuovere
     */
    public static void rimuoviEsame(Esame prova) {
        GestioneEsami.pianoDiStudio.remove(prova);
        hasChanged = true;
    }

    /**
     * Modifica un esame della lista visualizzata.
     *
     * @param esameAggiornato esame con i dati aggiornati
     */
    public static void modificaEsame(Esame esameAggiornato) {
        for (Esame esame : pianoDiStudio) {
            if (esame.getId() == esameAggiornato.getId()) {
                esame = esameAggiornato;
            }
        }
        hasChanged = true;
    }
}
