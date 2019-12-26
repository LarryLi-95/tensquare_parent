package com.tensquare.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.spit.pojo
 * @className:Spit
 * @author:larry
 * @date:2019/12/26 15:06
 * @description:
 */
@Data
public class Spit implements Serializable {
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nikename;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;


}
