package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    //最新问题列表
    @Query(value ="SELECT * FROM tb_problem tp,tb_pl tpl WHERE tp.id = tpl.problemid AND tpl.labelid = ?1 ORDER BY replytime DESC " ,nativeQuery = true)
    public Page<Problem> newestList(String labelId, Pageable pageable);

    //最热门问题列表
    @Query(value ="SELECT * FROM tb_problem tp,tb_pl tpl WHERE tp.id = tpl.problemid AND tpl.labelid = ?1 ORDER BY reply DESC" ,nativeQuery = true)
    public Page<Problem> hotList(String labelId, Pageable pageable);

    //待回答问题列表
    @Query(value ="SELECT * FROM tb_problem tp,tb_pl tpl WHERE tp.id = tpl.problemid AND tpl.labelid = ?1 AND tp.reply=0 ORDER BY replytime DESC " ,nativeQuery = true)
    public Page<Problem> waitList(String labelId, Pageable pageable);
}
