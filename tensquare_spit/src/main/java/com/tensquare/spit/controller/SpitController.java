package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.spit.controller
 * @className:SpitController
 * @author:larry
 * @date:2019/12/26 15:44
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Spit> spitList = spitService.findAll();
        Result result = new Result(true, StatusCode.OK, "查询成功!", spitList);
        return result;
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId) {
        Spit spit = spitService.findById(spitId);
        Result result = new Result(true, StatusCode.OK, "查询成功!", spit);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        Result result = new Result(true, StatusCode.OK, "保存成功!");
        return result;
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        Result result = new Result(true, StatusCode.OK, "更新成功!");
        return result;
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        Result result = new Result(true, StatusCode.OK, "删除成功!");
        return result;
    }

    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result deleteById(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentId(parentId, page, size);
        PageResult<Spit> pageResult = new PageResult<>(pageData.getTotalElements(), pageData.getContent());
        Result result = new Result(true, StatusCode.OK, "查询成功!", pageResult);
        return result;
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result addThumbup(@PathVariable String spitId) {
        //判断当前用户是否已经点赞,但是现在没有做登录认证,暂时将userId写死
        String userid = "111";
        //判断当前用户是否已经点赞
        Object o = redisTemplate.opsForValue().get("thumbup" + userid);
        if (o != null) {
            return new Result(false, StatusCode.REMOTEERROR, "您已经点赞过了!");
        }
        spitService.addThumbup(spitId);
        redisTemplate.opsForValue().set("thumbup" + userid, 1);
        Result result = new Result(true, StatusCode.OK, "点赞成功!");
        return result;
    }
}
