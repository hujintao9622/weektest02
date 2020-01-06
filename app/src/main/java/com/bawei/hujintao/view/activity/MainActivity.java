package com.bawei.hujintao.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseActivity;
import com.bawei.hujintao.database.DaoMaster;
import com.bawei.hujintao.database.DaoSession;
import com.bawei.hujintao.database.SqLeftBeanDao;
import com.bawei.hujintao.database.SqRightBeanDao;
import com.bawei.hujintao.model.bean.LeftBean;
import com.bawei.hujintao.model.bean.RightBean;
import com.bawei.hujintao.model.bean.SqLeftBean;
import com.bawei.hujintao.model.bean.SqRightBean;
import com.bawei.hujintao.presenter.HomePresenter;
import com.bawei.hujintao.util.NetUtil;
import com.bawei.hujintao.view.adapter.LeftAdapter;
import com.bawei.hujintao.view.adapter.RightAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  展示页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:14
 */
public class MainActivity extends BaseActivity<HomePresenter> {
    @BindView(R.id.ma_rc_left)
    RecyclerView maRcLeft;
    @BindView(R.id.ma_rc_right)
    RecyclerView maRcRight;
    private SqLeftBeanDao sqLeftBeanDao;
    private SqRightBeanDao sqRightBeanDao;

    @Override
    protected void initData() {
        //判断有无网
        if (NetUtil.getInstance().hasNet(this)){
            mPresenter.getLeftData();
        }else {
            SqLeftBean unique = sqLeftBeanDao.queryBuilder().unique();
            List<String> category = new Gson().fromJson(unique.getJson(), LeftBean.class).getCategory();
            //创建适配器
            LeftAdapter leftAdapter = new LeftAdapter(category);
            //条目点击事件
            leftAdapter.setOnItemClickLisener(new LeftAdapter.OnItemClickLisener() {
                @Override
                public void OnItemClick(int position) {
                    RightList(position);
                }
            });
            //设置适配器
            maRcLeft.setAdapter(leftAdapter);
            RightList(0);
        }
    }

    @Override
    protected void initView() {
        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        sqLeftBeanDao = daoSession.getSqLeftBeanDao();
        sqRightBeanDao = daoSession.getSqRightBeanDao();
        //设置布局管理器
        //左
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        maRcLeft.setLayoutManager(linearLayoutManager);
        //右
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        maRcRight.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onLeftSuccess(LeftBean leftBean) {
        sqLeftBeanDao.deleteAll();
        //对象转字符串
        String s = new Gson().toJson(leftBean);
        //存储到数据库
        sqLeftBeanDao.insert(new SqLeftBean(s));
        //左侧数据
        List<String> category = leftBean.getCategory();
        //创建适配器
        LeftAdapter leftAdapter = new LeftAdapter(category);
        //条目点击事件
        leftAdapter.setOnItemClickLisener(new LeftAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClick(int position) {
                //传值
                EventBus.getDefault().post(category.get(position));
            }
        });
        //设置适配器
        maRcLeft.setAdapter(leftAdapter);
        mPresenter.getRightData(category.get(0));
    }

    @Override
    public void onLeftFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }

    @Override
    public void onRightSuccess(RightBean rightBean) {
        //对象转字符串
        String s = new Gson().toJson(rightBean);
        //添加到数据库
        sqRightBeanDao.insert(new SqRightBean(s));
        //右侧数据
        List<RightBean.DataBean> data = rightBean.getData();
        //创建适配器
        RightAdapter rightAdapter = new RightAdapter(data);
        //设置适配器
        maRcRight.setAdapter(rightAdapter);
    }

    @Override
    public void onRightFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //注销
        EventBus.getDefault().unregister(this);
    }
    //接收值
    @Subscribe
    public void getRightJson(String s){
        mPresenter.getRightData(s);
    };
    public void RightList(int position){
        //数据库取右侧数据
        List<SqRightBean> list = sqRightBeanDao.queryBuilder().list();
        String json = list.get(position).getJson();
        RightBean rightBean = new Gson().fromJson(json, RightBean.class);
        List<RightBean.DataBean> data = rightBean.getData();
        //创建适配器
        RightAdapter rightAdapter = new RightAdapter(data);
        //设置适配器
        maRcRight.setAdapter(rightAdapter);
    }
}
