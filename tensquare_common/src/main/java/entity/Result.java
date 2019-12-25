package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @projectName:tensquare_parent
 * @packageName:entity
 * @className:Result
 * @author:li930
 * @date:2019/12/18 22:30
 * @description:
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
