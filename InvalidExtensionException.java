import java.lang.Exception;

class InvalidExtensionException extends Exception {
    public InvalidExtensionException(String errorMessage) {
        super(errorMessage);
    }
}