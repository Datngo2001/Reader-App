package hcmute.edu.vn.reader.model;

public class BaseResponse<T> {
    private T data;
    private String message;

    public T getData() {
        return data;
    } // get the user data

    public void setData(T data) {
        this.data = data;
    } // set user data

    public String getMessage() {
        return message;
    } // get user message

    public void setMessage(String message) {
        this.message = message;
    } // set user message
}
