package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.friend.controller
 * @className:FriendController
 * @author:larry
 * @date:2020/1/2 14:54
 * @description:
 */
@RestController
@RequestMapping("/friend")
@CrossOrigin
@RefreshScope
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友或者添加非好友
     *
     * @return
     */
    @RequestMapping(value = "/like/{friendId}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        //验证是否登录,并拿到当前用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new Result(false, StatusCode.ERROR, "权限不足!");
        }
        //得到当前用户登陆的id
        String userId = claims.getId();
        //判断是添加好友还是添加非好友
        if (type != null) {
            if (type.equals("1")) { //添加好友
                int flag = friendService.addFriend(userId, friendId);
                if (flag == 0) { //重复添加
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友!");
                }
                if (flag == 1) {
                    userClient.updateFanscountAndFollowcount(userId, friendId, 1);
                    return new Result(true, StatusCode.ERROR, "添加成功!");
                }
            } else {  //添加非好友
                int flag = friendService.addNoFriend(userId, friendId);
                if (flag == 0) { //重复添加
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友!");
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.ERROR, "添加成功!");
                }
            }
            return new Result(false, StatusCode.ERROR, "参数异常!");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常!");
        }
    }


    /**
     * 删除好友
     *
     * @return
     */
    @RequestMapping(value = "/{friendId}", method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendId) {

        //验证是否登录,并拿到当前用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new Result(false, StatusCode.ERROR, "权限不足!");
        }
        //得到当前用户登陆的id
        String userId = claims.getId();

        friendService.deleteFriend(userId, friendId);
        userClient.updateFanscountAndFollowcount(userId, friendId, -1);
        return new Result(true, StatusCode.OK, "删除成功!");
    }
}
