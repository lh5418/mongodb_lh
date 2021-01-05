package com.jk.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author lh
 * @Description
 * @Date 20:18 2020/12/31
 * @Param
 * @return
 **/
@Data
@Document(collection = "t_tree")
public class TreeBean {
    private String id;
    private String text;
    private Integer pid;
    private String url;
    private List<TreeBean> children;
}
