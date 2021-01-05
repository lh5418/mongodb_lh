package com.jk.service;

import com.jk.pojo.TreeBean;
import com.jk.pojo.ZhangBean;

import java.util.HashMap;
import java.util.List;

/**
 * @program: mongodb_lh
 * @description:
 * @author: 刘海
 * @create: 2020-12-31 20:22
 */
public interface ZhangService {
    List<TreeBean> findTree();

    HashMap<String, Object> findZhang(ZhangBean zhangBean, Integer page, Integer rows);

    void addZhang(ZhangBean zhangBean);

    ZhangBean findZhangById(String id);

    void deleteZhang(String[] ids);
}
