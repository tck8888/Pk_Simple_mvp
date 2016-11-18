package tck.cn.pk_simple_mvp.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tck.cn.pk_simple_mvp.ApiService;
import tck.cn.pk_simple_mvp.constant.Constant;

/**
 * Description :
 * <p>
 * Created by tck on 2016/11/19.
 */

public class NetManager {

    private static NetManager manager = new NetManager();

    private NetManager() {

    }

    public static NetManager getInstance() {
        return manager;
    }


    public ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public void getNetData(String url, Map<String, String> msps, final ListenData<ResponseBody> listen) {
        getApiService().getData(url, msps)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        listen.success(responseBody);
                    }
                });
    }

    public interface ListenData<ResponseBody> {
        void success(ResponseBody body);
    }
}
