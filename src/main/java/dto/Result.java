package dto;

import java.util.List;

/**
 * created by Xu on 2023/11/13 11:16.
 * 响应对象
 */
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public Result() {
    }

    public Result(Boolean success, String errorMsg, Object data, Long total) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.data = data;
        this.total = total;
    }

    public static Result ok(){
        return new Result(true, null, null, null);
    }

    /**
     * 数据量较小
     * @param data
     * @return
     */
    public static Result ok(Object data){
        return new Result(true, null, data, null);
    }

    /**
     *
     * @param data
     * @param total
     * @return list数据及其长度
     */
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, total);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null, null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
