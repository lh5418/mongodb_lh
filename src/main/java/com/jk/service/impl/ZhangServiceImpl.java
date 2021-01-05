package com.jk.service.impl;

import com.jk.pojo.TreeBean;
import com.jk.pojo.ZhangBean;
import com.jk.service.ZhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @program: mongodb_lh
 * @description:
 * @author: 刘海
 * @create: 2020-12-31 20:22
 */
@Service
public class ZhangServiceImpl implements ZhangService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<TreeBean> findTree() {
        String pid = "0";
        List<TreeBean> list= findNode(pid);
        return list;
    }

    private List<TreeBean> findNode(String pid) {
        Query query =new Query();
        query.addCriteria(Criteria.where("pid").is(pid));
        List<TreeBean> list = mongoTemplate.find(query , TreeBean.class);
        for (TreeBean tree : list) {
            String id = tree.getId();
            List<TreeBean> list2 = findNode(id);
            tree.setChildren(list2);
        }
        return list;
    }

    @Override
    public HashMap<String, Object> findZhang(ZhangBean zhangBean, Integer page, Integer rows) {
        Query query = new Query();
        if(!StringUtils.isEmpty(zhangBean.getName())){
            query.addCriteria(Criteria.where("name").regex(zhangBean.getName()));
        }
        Criteria cr = Criteria.where("time");
        if(zhangBean.getMindate()!=null){
            cr.gte(zhangBean.getMindate());
        }
        if(zhangBean.getMaxdate()!=null){
            cr.lte(zhangBean.getMaxdate());
        }
        if(zhangBean.getMindate()!=null || zhangBean.getMaxdate()!=null){
            query.addCriteria(cr);
        }
        long count = mongoTemplate.count(query , ZhangBean.class);
        Sort orders = Sort.by(new Sort.Order(Sort.Direction.DESC,"money"));
        query.with(orders);
        int start =(page-1)*rows;
        query.skip(start).limit(rows);
        List<ZhangBean> list= mongoTemplate.find(query, ZhangBean.class);
        HashMap<String, Object> map=new HashMap<>();
        map.put("total", count);
        map.put("rows", list);
        return map;
    }

    @Override
    public void addZhang(ZhangBean zhangBean) {
        if(zhangBean.getId().equals("")){
            zhangBean.setId(null);
        }
        mongoTemplate.save(zhangBean);
    }

    @Override
    public ZhangBean findZhangById(String id) {
        return mongoTemplate.findById(id, ZhangBean.class);
    }

    @Override
    public void deleteZhang(String[] ids) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        mongoTemplate.remove(query,ZhangBean.class);
    }
}
