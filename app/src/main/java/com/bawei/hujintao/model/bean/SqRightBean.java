package com.bawei.hujintao.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:  右侧数据库
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:40
 */
@Entity
public class SqRightBean {
    private String json;

    @Generated(hash = 1441222726)
    public SqRightBean(String json) {
        this.json = json;
    }

    @Generated(hash = 1050596496)
    public SqRightBean() {
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
