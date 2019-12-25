package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.base.service
 * @className:LabelService
 * @author:larry
 * @date:2019/12/19 0:37
 * @description:
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        List<Label> labelList = labelDao.findAll();
        return labelList;
    }

    public Label findById(String labelId) {
        Label label = labelDao.findById(labelId).get();
        return label;
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /*
             *@param root 根对象,也就是说要把条件封装到那个对象中.where 类名=label.getId
             *@param query 封装的是查询的关键字,如group by order by等
             *@param cb 用来封装条件对象的,如果直接返回null,表示不需要条件
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合来存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if (label.getLabelname() != null && "".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//相当于 where labelname like "%小明%"
                    predicateList.add(predicate);
                }
                if(label.getState()!=null&&"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),  label.getState() );// where state ="1"
                }
                //new 一个数组作为最终返回值
                Predicate[] predicates = new Predicate[predicateList.size()];
                //将list转为数组
                predicateList.toArray(predicates);
                return cb.and(predicates);//相当于 where labelname like "%小明%" and where state ="1"
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /*
             *@param root 根对象,也就是说要把条件封装到那个对象中.where 类名=label.getId
             *@param query 封装的是查询的关键字,如group by order by等
             *@param cb 用来封装条件对象的,如果直接返回null,表示不需要条件
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合来存放所有条件
                List<Predicate> predicateList = new ArrayList<>();
                if (label.getLabelname() != null && "".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//相当于 where labelname like "%小明%"
                    predicateList.add(predicate);
                }
                if(label.getState()!=null&&"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),  label.getState() );// where state ="1"
                }
                //new 一个数组作为最终返回值
                Predicate[] predicates = new Predicate[predicateList.size()];
                //将list转为数组
                predicateList.toArray(predicates);
                return cb.and(predicates);//相当于 where labelname like "%小明%" and where state ="1"
            }
        }, pageable);
    }
}
