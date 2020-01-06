package com.bawei.hujintao.presenter;

import com.bawei.hujintao.base.BasePresenter;
import com.bawei.hujintao.contract.IHomeContract;
import com.bawei.hujintao.model.HomeModel;
import com.bawei.hujintao.model.bean.LeftBean;
import com.bawei.hujintao.model.bean.RightBean;

/**
 * 功能:  presenter页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:12
 */
public class HomePresenter extends BasePresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getLeftData() {
        homeModel.getLeftData(new IHomeContract.IModel.ILeftModelCallback() {
            @Override
            public void onLeftSuccess(LeftBean leftBean) {
                getView().onLeftSuccess(leftBean);
            }

            @Override
            public void onLeftFailure(Throwable throwable) {
                getView().onLeftFailure(throwable);
            }
        });
    }

    @Override
    public void getRightData(String param) {
        homeModel.getRightData(param, new IHomeContract.IModel.IRightModelCallback() {
            @Override
            public void onRightSuccess(RightBean rightBean) {
                getView().onRightSuccess(rightBean);
            }

            @Override
            public void onRightFailure(Throwable throwable) {
                getView().onRightFailure(throwable);
            }
        });
    }
}
