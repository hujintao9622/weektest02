package com.bawei.hujintao.contract;

import com.bawei.hujintao.model.bean.LeftBean;
import com.bawei.hujintao.model.bean.RightBean;

/**
 * 功能:  契约
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:04
 */
public interface IHomeContract {
    interface IView{
        void onLeftSuccess(LeftBean leftBean);
        void onLeftFailure(Throwable throwable);
        void onRightSuccess(RightBean rightBean);
        void onRightFailure(Throwable throwable);
    }
    interface IPresenter{
        void getLeftData();
        void getRightData(String param);
    }
    interface IModel{
        void getLeftData(ILeftModelCallback iLeftModelCallback);
        void getRightData(String param,IRightModelCallback iRightModelCallback);
        interface ILeftModelCallback{
            void onLeftSuccess(LeftBean leftBean);
            void onLeftFailure(Throwable throwable);
        }
        interface IRightModelCallback{
            void onRightSuccess(RightBean rightBean);
            void onRightFailure(Throwable throwable);
        }
    }
}
