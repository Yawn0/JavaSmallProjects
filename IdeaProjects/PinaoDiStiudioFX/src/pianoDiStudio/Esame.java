package pianoDiStudio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model Esame contenente tutte le informazioni dell'esame ed i metodi necessari a gestirle
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Esame {

    private final int id;
    private final StringProperty materia;
    private final IntegerProperty crediti;
    private final StringProperty docente;

    /**
     * Costruttore della classe Esame
     *
     * @param id      identificativo esame
     * @param materia materia dell'esame
     * @param crediti crediti dell'esame
     * @param docente docente dell'esame
     */
    public Esame(int id, String materia, int crediti, String docente) {
        this.id = id;
        this.materia = new SimpleStringProperty(materia);
        this.crediti = new SimpleIntegerProperty(crediti);
        this.docente = new SimpleStringProperty(docente);
    }

    /**
     * Costruttore di copia della classe Esame
     *
     * @param esame esame da copiare
     */
    public Esame(Esame esame) {
        this.id = esame.getId();
        this.materia = esame.materiaProperty();
        this.crediti = esame.creditiProperty();
        this.docente = esame.docenteProperty();
    }

    /**
     *
     */
    public int getId() {
        return id;
    }

    /**
     *
     */
    public String getMateria() {
        return materia.get();
    }

    /**
     *
     */
    public StringProperty materiaProperty() {
        return materia;
    }

    /**
     *
     */
    public void setMateria(String materia) {
        this.materia.set(materia);
    }

    /**
     *
     */
    public int getCrediti() {
        return crediti.get();
    }

    /**
     *
     */
    public IntegerProperty creditiProperty() {
        return crediti;
    }

    /**
     *
     */
    public void setCrediti(int crediti) {
        this.crediti.set(crediti);
    }

    /**
     *
     */
    public String getDocente() {
        return docente.get();
    }

    /**
     *
     */
    public StringProperty docenteProperty() {
        return docente;
    }

    /**
     *
     */
    public void setDocente(String docente) {
        this.docente.set(docente);
    }

}