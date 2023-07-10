package utils;

public class UnexpectedJsErrorException extends RuntimeException{
    public UnexpectedJsErrorException() {
    }

    public UnexpectedJsErrorException(String message) {
        super(message);
    }

    public UnexpectedJsErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedJsErrorException(Throwable cause) {
        super(cause);
    }

    public UnexpectedJsErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
