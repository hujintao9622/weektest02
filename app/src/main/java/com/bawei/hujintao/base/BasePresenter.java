package com.bawei.hujintao.base;

import com.bawei.hujintao.contract.IHomeContract;

import java.lang.ref.WeakReference;

/**
 * 功能:  presenter基类
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:10
 */
public abstract class BasePresenter<V extends IHomeContract.IView> implements IHomeContract.IPresenter {

    private WeakReference<V> weakReference;

    public void attach(V view){
        weakReference = new WeakReference<>(view);
    }
    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }
    public V getView(){
        return weakReference.get();
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
