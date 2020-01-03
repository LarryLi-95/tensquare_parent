package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.friend.service
 * @className:FriendService
 * @author:larry
 * @date:2020/1/2 15:05
 * @description:
 */
@Service
@CrossOrigin
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendId) {
        //先判断userid到friendid是否有数据
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendId);
        if (friend != null) {
            return 0;  //表示重复添加
        }
        //直接添加好友,让好友表中userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据,如果有,把双方状态都改为1
        if (friendDao.findByUseridAndFriendid(friendId, userId) != null) {
            //把双方的islike都改为1
            friendDao.UpdateIsLike("1", userId, friendId);
            friendDao.UpdateIsLike("1", friendId, userId);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        //判断是否已经是好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendId);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendId) {
        //先删除好友表中 userid到friendid的数据
        friendDao.deleteFriend(userId, friendId);
        //更新friendid到userid的islike为0
        friendDao.UpdateIsLike("0", friendId, userId);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setFriendid(friendId);
        noFriend.setUserid(userId);
        noFriendDao.save(noFriend);
    }
}
