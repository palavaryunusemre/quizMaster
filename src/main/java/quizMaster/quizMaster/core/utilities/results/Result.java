package quizMaster.quizMaster.core.utilities.results;

public class Result {
    private boolean success;
    private int code;
    public Result(boolean success) {
        this.success = success;
    }
    public Result(boolean success,int code) {
        this(success);
        this.code = code;
    }
    public boolean isSuccess() {
        return success;
    }
    public int getCode() {
        return code;
    }
}
