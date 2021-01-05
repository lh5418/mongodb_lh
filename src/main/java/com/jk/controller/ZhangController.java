package com.jk.controller;

import com.jk.pojo.TreeBean;
import com.jk.pojo.ZhangBean;
import com.jk.service.ZhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @program: mongodb_lh
 * @description:
 * @author: 刘老师66
 * @create: 2020-12-31 20:21
 */
@Controller
@RequestMapping("test")
public class ZhangController {

    @Autowired
    private ZhangService zhangService;

    /**
     * @Author lh 
     * @Description  show页面
     * @Date 20:36 2020/12/31
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("show")
    public String show(){
        return "show";
    }

    /**
     * @Author lh 
     * @Description  树
     * @Date 20:37 2020/12/31
     * @Param []
     * @return java.util.List<com.jk.pojo.TreeBean>
     **/
    @RequestMapping("findTree")
    @ResponseBody
    public List<TreeBean> findTree(){
        return zhangService.findTree();
    }

    /**
     * @Author lh 
     * @Description  账单
     * @Date 20:37 2020/12/31
     * @Param [zhangBean, page, rows]
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("findZhang")
    @ResponseBody
    public HashMap<String,Object> findZhang(ZhangBean zhangBean, Integer page, Integer rows){
        return zhangService.findZhang(zhangBean,page,rows);
    }

    /**
     * @Author lh 
     * @Description  添加 修改账单
     * @Date 20:37 2020/12/31
     * @Param [zhangBean]
     * @return void
     **/
    @RequestMapping("addZhang")
    @ResponseBody
    public void addZhang(ZhangBean zhangBean){
        zhangService.addZhang(zhangBean);
    }
    @RequestMapping("deleteZhang")
    @ResponseBody
    public void deleteZhang(String[] ids){
        zhangService.deleteZhang(ids);
    }

    /**
     * @Author lh 
     * @Description  修改回显
     * @Date 20:39 2020/12/31
     * @Param [id]
     * @return com.jk.pojo.ZhangBean
     **/
    @RequestMapping("findZhangById")
    @ResponseBody
    public ZhangBean findZhangById(String id){
        return zhangService.findZhangById(id);
    }



}
