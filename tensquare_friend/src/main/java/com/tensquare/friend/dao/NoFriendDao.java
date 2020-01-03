package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.friend.dao
 * @className:FriendDao
 * @author:larry
 * @date:2020/1/2 15:16
 * @description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

    public NoFriend findByUseridAndFriendid(String userId, String friendId);

}
