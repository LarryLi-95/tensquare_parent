package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 更新好友粉丝数和用户关注数
     */
    @RequestMapping(value = "/user/{userId}/{friendId}/{x}", method = RequestMethod.PUT)
    public void updateFanscountAndFollowcount(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId, @PathVariable("x") int x);
}
