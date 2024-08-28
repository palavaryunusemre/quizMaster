package quizMaster.quizMaster.core.utilities.results;

public class Result {
    private boolean success;
    private int code;
    private String message;
    public Result(boolean success) {
        this.success = success;
    }
    public Result(boolean success,int code) {
        this(success);
        this.code = code;
    }
    public Result(boolean success,int code, String message) {
        this(success);
        this.code = code;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
