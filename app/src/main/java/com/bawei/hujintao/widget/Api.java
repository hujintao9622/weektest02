package com.bawei.hujintao.widget;

import com.bawei.hujintao.model.bean.LeftBean;
import com.bawei.hujintao.model.bean.RightBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 8:45
 */
public interface Api {
    @GET("baweiapi/category")
    Observable<LeftBean> leftrcy();
    @GET("baweiapi/shopByCategory")
    Observable<RightBean> rightrcy(@Query("category") String param);

}
