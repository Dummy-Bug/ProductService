package product.exceptions;

public class ResponseBodyNullException extends Exception {
    public ResponseBodyNullException(String message) {
        super(message);
    }

    public ResponseBodyNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
