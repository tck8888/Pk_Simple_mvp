package tck.cn.pk_simple_mvp;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description :
 * <p>
 * Created by tck on 2016/11/19.
 */

public interface ApiService {

    @GET("{method}")
    Observable<ResponseBody> getData(@Path("method") String meythod, @QueryMap Map<String, String> map);
}
