package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    public User findByMobile(String mobile);

    @Modifying
    @Query(value = "UPDATE tb_user set fanscount=fanscount+?1 where id=?2", nativeQuery = true)
    public void updateFansCount(int x, String friendId);

    @Modifying
    @Query(value = "UPDATE tb_user set followcount=followcount+?1 where id=?2", nativeQuery = true)
    public void updateFollowCount(int x, String userId);
}
