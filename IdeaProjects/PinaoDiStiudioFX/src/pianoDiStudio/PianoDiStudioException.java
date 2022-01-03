package pianoDiStudio;

/**
 * The type Viaggio exception.
 */
public class PianoDiStudioException extends Exception {
    private String error;

    /**
     * Instantiates a new Viaggio exception.
     *
     * @param error the error
     */
    PianoDiStudioException(String error) {
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
