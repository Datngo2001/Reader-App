package hcmute.edu.vn.reader.model;

public class BaseResponse<T> {
    private T datal;
    private String message;

    public T getDatal() {
        return datal;
    }

    public void setDatal(T datal) {
        this.datal = datal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
