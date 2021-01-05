package com.jk.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author lh 
 * @Description  
 * @Date 20:37 2021/1/3
 * @Param 
 * @return 
 **/
@Data
@Document(collection = "t_zhang")
public class ZhangBean {
    private String id;
    private String name;
    private Integer money;
    private String info;
    @DateTimeFormat(pattern="yyyy-MM-dd")//时间类型注解
    private Date time;

    @DateTimeFormat(pattern="yyyy-MM-dd")//时间类型注解
    private Date mindate;
    @DateTimeFormat(pattern="yyyy-MM-dd")//时间类型注解
    private Date maxdate;
}
