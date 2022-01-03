package pianoDiStudio;

/**
 * The type Viaggio exception.
 */
public class PianoStudioException extends Exception {
    private String error;

    /**
     * Instantiates a new Viaggio exception.
     *
     * @param error the error
     */
    PianoStudioException(String error) {
        this.error = error;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    String getError() {
        return error;
    }
}
