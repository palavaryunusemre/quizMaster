package quizMaster.quizMaster.core.utilities.results;

public class DataResult<T> extends Result{
    private T data;
    private String message;
    public DataResult(T data, boolean success, int code) {
        super(success, code);
        this.data = data;
    }
    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }
    public  DataResult(boolean success, String message){
        super(success);
        this.message = message;

    }
    public T getData() {
        return data;
    }
}
