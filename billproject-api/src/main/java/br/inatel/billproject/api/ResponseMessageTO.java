package br.inatel.billproject.api;

public class ResponseMessageTO {
    private String message;

    public ResponseMessageTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
