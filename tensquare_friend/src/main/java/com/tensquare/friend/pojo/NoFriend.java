package com.tensquare.friend.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.friend.pojo
 * @className:Friend
 * @author:larry
 * @date:2020/1/2 15:16
 * @description:
 */
@Data
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {

    @Id
    private String userid;
    @Id
    private String friendid;

}
