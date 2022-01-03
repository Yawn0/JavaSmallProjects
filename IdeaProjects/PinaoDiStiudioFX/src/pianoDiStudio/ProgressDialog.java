package pianoDiStudio;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import static impl.org.controlsfx.i18n.Localization.getString;

/**
 * Visualizza una finestra di dialogo con una barra di caricamento<br>
 * Utilizzata come overloading della loading dialog predefinita javaFX<br>
 * Pensata per esere più dinamica possibile
 *
 * @author Leoanrdo Tocchet
 * @version 1.0
 * @see javafx.scene.control.Dialog
 */

public class ProgressDialog extends Dialog<Boolean> {
    private VBox vbox;
    private Label label;
    private ProgressBar progressBar;

    private boolean isFinished = false;

    /**
     * crea una nuova progress dialog allo 0%
     */
    public ProgressDialog() {
        this(0.0d);
    }

    /**
     * crea una nuova progress dialog con il progresso per labarra dato in input
     *
     * @param progress progresso da applicare alla barra di caricamento.<br>
     *                 deve essere tra 0 e 1
     */
    public ProgressDialog(double progress) {
        final DialogPane dialogPane = getDialogPane();

        // layout manager
        vbox = new VBox();
        vbox.setFillWidth(true);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setAlignment(Pos.CENTER_LEFT);

        // progress bar
        progressBar = new ProgressBar(progress);
        progressBar.prefWidthProperty().bind(vbox.widthProperty().subtract(10));

        // label
        label = createContentLabel(dialogPane.getContentText());
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.textProperty().bind(dialogPane.contentTextProperty());

        dialogPane.contentTextProperty().addListener(o -> updateGrid());

        setTitle("Please Wait");
        dialogPane.setHeaderText("Loading...");
        dialogPane.getStyleClass().add("text-input-dialog");
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        updateGrid();

        setResult(isFinished);
    }

    /**
     * crea una progress dialog dandogli un task (worker)
     *
     * @param worker task da completare (worker)
     */
    public ProgressDialog(final Worker<?> worker) {
        if (worker != null
                && (worker.getState() == Worker.State.CANCELLED
                || worker.getState() == Worker.State.FAILED
                || worker.getState() == Worker.State.SUCCEEDED)) {
            return;
        }
        setResultConverter(dialogButton -> null);

        final DialogPane dialogPane = getDialogPane();

        setTitle(getString("progress.dlg.title")); //$NON-NLS-1$
        dialogPane.setHeaderText(getString("progress.dlg.header")); //$NON-NLS-1$
        dialogPane.getStyleClass().add("progress-dialog"); //$NON-NLS-1$
        dialogPane.getStylesheets().add(ProgressDialog.class.getResource("dialogs.css").toExternalForm()); //$NON-NLS-1$

        final Label progressMessage = new Label();
        progressMessage.textProperty().bind(worker.messageProperty());

        final WorkerProgressPane content = new WorkerProgressPane(this);
        content.setMaxWidth(Double.MAX_VALUE);
        content.setWorker(worker);

        VBox vbox = new VBox(10, progressMessage, content);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setPrefSize(300, 100);
        /**
         * (INFORMAZIONE)
         * Il content Text non può essere impostato prima del costruttore
         * e dato che impostiamo un Content Node, il contentText non sarà visualizzato.
         * Se si vuole mostrare un content text bisogna ricrearlo
         */
        Label contentText = new Label();
        contentText.setWrapText(true);
        vbox.getChildren().add(0, contentText);
        contentText.textProperty().bind(dialogPane.contentTextProperty());
        dialogPane.setContent(vbox);
    }

    //troppo complicato da spiegare ;)
    private static class WorkerProgressPane extends Region {
        private Worker<?> worker;

        private boolean dialogVisible = false;
        private boolean cancelDialogShow = false;

        private ChangeListener<Worker.State> stateListener = new ChangeListener<Worker.State>() {
            @Override public void changed(ObservableValue<? extends Worker.State> observable, Worker.State old, Worker.State value) {
                switch(value) {
                    case CANCELLED:
                    case FAILED:
                    case SUCCEEDED:
                        if(!dialogVisible) {
                            cancelDialogShow = true;
                            end();
                        } else if(old == Worker.State.SCHEDULED || old == Worker.State.RUNNING) {
                            end();
                        }
                        break;
                    case SCHEDULED:
                        begin();
                        break;
                    default: //no-op
                }
            }
        };

        public final void setWorker(final Worker<?> newWorker) {
            if (newWorker != worker) {
                if (worker != null) {
                    worker.stateProperty().removeListener(stateListener);
                    end();
                }

                worker = newWorker;

                if (newWorker != null) {
                    newWorker.stateProperty().addListener(stateListener);
                    if (newWorker.getState() == Worker.State.RUNNING || newWorker.getState() == Worker.State.SCHEDULED) {
                        begin();
                    }
                }
            }
        }
        // Se l'indicatore di progresso del caricamento cambia, bisogna re-inizializzare
        // Se il "Worker" cambia, bisogna re-inizializzare

        private final ProgressDialog dialog;
        private final ProgressBar progressBar;

        public WorkerProgressPane(ProgressDialog dialog) {
            this.dialog = dialog;

            this.progressBar = new ProgressBar();
            progressBar.setMaxWidth(Double.MAX_VALUE);
            getChildren().add(progressBar);

            if (worker != null) {
                progressBar.progressProperty().bind(worker.progressProperty());
            }
        }

        private void begin() {
            cancelDialogShow = false;

            Platform.runLater(() -> {
                if(!cancelDialogShow) {
                    progressBar.progressProperty().bind(worker.progressProperty());
                    dialogVisible = true;
                    dialog.show();
                }
            });
        }

        private void end() {
            progressBar.progressProperty().unbind();
            dialogVisible = false;
        }

        @Override protected void layoutChildren() {
            if (progressBar != null) {
                Insets insets = getInsets();
                double w = getWidth() - insets.getLeft() - insets.getRight();
                double h = getHeight() - insets.getTop() - insets.getBottom();

                double prefH = progressBar.prefHeight(-1);
                double x = insets.getLeft() + (w - w) / 2.0;
                double y = insets.getTop() + (h - prefH) / 2.0;

                progressBar.resizeRelocate(x, y, w, prefH);
            }
        }
    }

    /**
     * Set the current state of the progress bar. This method accepts a double
     * from 0-1 and fills in the progress bar accordingly.
     *
     * @param progress The progress to apply to the progress bar. Must be between 0 and 1.
     */
    public void setProgress(double progress) {
        progressBar.setProgress(progress);
    }

    /**
     * Performs the function of <code>DialogPane.createContentLabel</code>
     *
     * @param text The text to be applied to the label.
     * @return A new label for the dialog.
     */
    private static Label createContentLabel(String text) {
        Label label = new Label(text);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.getStyleClass().add("content");
        label.setWrapText(true);
        label.setPrefWidth(360);
        return label;
    }

    /**
     * aggiorna la grid per visualizzare i componenti correnti
     */
    private void updateGrid() {
        vbox.getChildren().clear();

        vbox.getChildren().add(progressBar);
        vbox.getChildren().add(label);
        getDialogPane().setContent(vbox);

        Platform.runLater(() -> progressBar.requestFocus());
    }
}