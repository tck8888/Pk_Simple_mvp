package tck.cn.pk_simple_mvp.presenter;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import tck.cn.pk_simple_mvp.constant.Constant;
import tck.cn.pk_simple_mvp.http.NetManager;
import tck.cn.pk_simple_mvp.model.PhoneInfo;

/**
 * Description :
 * <p>
 * Created by tck on 2016/11/19.
 */

public class BasePresenterImpl implements Contract.BasePresenter {

    private Contract.BaseView mBaseView;
    private String url = "mobile/get";

    private Map<String, String> maps = new HashMap<>();

    public BasePresenterImpl(Contract.BaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void loadData(String number) {
        maps.put("key", Constant.KEY);
        maps.put("phone",number);
        NetManager.getInstance().getNetData(url, maps, new NetManager.ListenData<ResponseBody>() {
            @Override
            public void success(ResponseBody responseBody) {

                try {
                    String json = responseBody.string();
                    PhoneInfo phoneInfo = new Gson().fromJson(json, PhoneInfo.class);
                    mBaseView.showData(phoneInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
