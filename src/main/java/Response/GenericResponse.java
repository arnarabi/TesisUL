package Response;

public class GenericResponse {
    String message;
    boolean error;

    public GenericResponse(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
