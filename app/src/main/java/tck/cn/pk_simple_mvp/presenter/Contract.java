package tck.cn.pk_simple_mvp.presenter;

/**
 * Description :
 * <p>
 * Created by tck on 2016/11/19.
 */

import android.content.Context;

import tck.cn.pk_simple_mvp.model.PhoneInfo;

public interface Contract {

    interface BaseView {
        void showData(PhoneInfo phoneInfo);

    }

    interface BasePresenter {
        void loadData(String number);
    }
}
