package com.wlos.common_generator.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Result setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.message = resultCode.message();
        return this;
    }
    public static Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(){
        Result result = new Result();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
