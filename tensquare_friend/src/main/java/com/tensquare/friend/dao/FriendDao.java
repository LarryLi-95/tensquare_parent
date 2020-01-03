package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
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
public interface FriendDao extends JpaRepository<Friend, String> {

    public Friend findByUseridAndFriendid(String userId, String friendId);

    @Modifying
    @Query(value = "UPDATE tb_friend set islike=?1 WHERE userid=?2 AND friendid=?3", nativeQuery = true)
    public int UpdateIsLike(String islike, String userId, String friendId);

    @Modifying
    @Query(value = "delete from  tb_friend  WHERE userid=?1 AND friendid=?2", nativeQuery = true)
    public void deleteFriend(String userId, String friendId);
}
