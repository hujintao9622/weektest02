package com.bawei.hujintao.model;

import com.bawei.hujintao.contract.IHomeContract;
import com.bawei.hujintao.model.bean.LeftBean;
import com.bawei.hujintao.model.bean.RightBean;
import com.bawei.hujintao.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能:  Model页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 8:45
 */
public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getLeftData(ILeftModelCallback iLeftModelCallback) {
        //左侧列表数据
        NetUtil.getInstance().getApi().leftrcy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeftBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        iLeftModelCallback.onLeftSuccess(leftBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iLeftModelCallback.onLeftFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRightData(String param, IRightModelCallback iRightModelCallback) {
        //右侧列表数据
        NetUtil.getInstance().getApi().rightrcy(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        iRightModelCallback.onRightSuccess(rightBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iRightModelCallback.onRightFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
