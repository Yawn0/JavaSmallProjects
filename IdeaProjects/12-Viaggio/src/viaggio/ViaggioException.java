package viaggio;

/**
 * The type Viaggio exception.
 */
public class ViaggioException extends Exception {
    private String error;

    /**
     * Instantiates a new Viaggio exception.
     *
     * @param error the error
     */
    ViaggioException(String error) {
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