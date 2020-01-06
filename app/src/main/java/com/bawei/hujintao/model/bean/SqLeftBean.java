package com.bawei.hujintao.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:  左侧数据库
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:40
 */
@Entity
public class SqLeftBean {
    private String json;

    @Generated(hash = 1849398210)
    public SqLeftBean(String json) {
        this.json = json;
    }

    @Generated(hash = 1973372244)
    public SqLeftBean() {
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
