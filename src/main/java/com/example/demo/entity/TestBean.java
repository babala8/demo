package com.example.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "TEST_TABLE_A")
public class TestBean {
    //id
    @Id
    private  int  id;
    //菜单名称
    @ApiModelProperty(value = "菜单名称")
    private  String  name_ss;
    //菜单url
    @Transient
    private  String  url;
    //顺序
    @ApiModelProperty(value = "顺序")
    private  int  sort;

    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_ss() {
        return name_ss;
    }

    public void setName_ss(String name_ss) {
        this.name_ss = name_ss;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
