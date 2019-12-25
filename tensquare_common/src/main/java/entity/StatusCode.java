package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @projectName:tensquare_parent
 * @packageName:entity
 * @className:StatusCode
 * @author:larry
 * @date:2019/12/18 22:44
 * @description:
 */
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int repERROR = 20005;//重复操作

}
