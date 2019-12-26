package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.spit.service
 * @className:SpitService
 * @author:larry
 * @date:2019/12/26 15:12
 * @description:
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll() {
        List<Spit> spitList = spitDao.findAll();
        return spitList;
    }

    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date()); //发布日期
        spit.setThumbup(0); // 点赞数
        spit.setVisits(0); //浏览量
        spit.setShare(0); //分享数
        spit.setComment(0);  //回复数
        spit.setState("1"); //状态
        //如果当前添加的吐槽,有父节点,那么父节点的吐槽回复数要加一 先判断有无父节点
        if (spit.getParentid() != null && "".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        Spit savedSpit = spitDao.save(spit);
    }

    public void update(Spit spit) {
        Spit savedSpit = spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);

    }

    public Page<Spit> findByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Spit> spits = spitDao.findByParentid(parentId, pageable);
        return spits;
    }

    public void addThumbup(String spitId) {
//        //方式一 效率低
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup()==null?0:spit.getThumbup())+1);
//        spitDao.save(spit);
        //方式二 使用原生mongo命令实现自增 db.uodate({"_id":"1"},{$inc:{thumbup:NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("1"));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
