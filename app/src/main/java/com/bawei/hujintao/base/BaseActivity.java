package com.bawei.hujintao.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.hujintao.contract.IHomeContract;

import butterknife.ButterKnife;

/**
 * 功能:  activity基类
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:14
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IHomeContract.IView {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter=providePresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
