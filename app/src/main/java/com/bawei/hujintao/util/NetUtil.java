package com.bawei.hujintao.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.widget.Api;
import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 8:54
 */
public class NetUtil {
    //公共URL
    private static final String baseUrl="http://blog.zhaoliang5156.cn/";
    private final Api api;

    //单例封装无参构造
    private NetUtil (){
        //创建拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置拦截器级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(httpLoggingInterceptor)
                //三个超时
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //okhttp平台
                .client(okHttpClient)
                //公共URL
                .baseUrl(baseUrl)
                //gson
                .addConverterFactory(GsonConverterFactory.create())
                //rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建Api
        api = retrofit.create(Api.class);
    }
    //单例
    public static NetUtil getInstance() {
        return SingleHolder.NET_UTIL;
    }
    //单例静态内部类
    private static final class SingleHolder{
        private static final NetUtil NET_UTIL=new NetUtil();
    }

    public Api getApi() {
        return api;
    }
    //有无网
    public boolean hasNet(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null&&networkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }
    //请求图片
    public void getPhoto(String bit, ImageView img){
        Glide.with(img).load(bit)
                //占位图
                .placeholder(R.mipmap.ic_launcher)
                //错位图
                .error(R.mipmap.ic_launcher_round)
                .into(img);
    }
}
