package com.coveiot.coveaccess.homescreen;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.homescreen.model.HomeScreenBannerDataReq;
import com.coveiot.coveaccess.homescreen.model.HomeScreenBannerDataRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class HomeScreen {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6610a;

        public a(CoveApiListener coveApiListener) {
            this.f6610a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            this.f6610a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.code() == 200) {
                HomeScreenBannerDataRes homeScreenBannerDataRes = new HomeScreenBannerDataRes(response.code());
                try {
                    homeScreenBannerDataRes.setHtmlSring(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.f6610a.onSuccess(homeScreenBannerDataRes);
                return;
            }
            this.f6610a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    public static void a(CoveApiListener<HomeScreenBannerDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHomeScreenData(map).enqueue(new a(coveApiListener));
    }

    public static void getHomeScreenBannerData(HomeScreenBannerDataReq homeScreenBannerDataReq, CoveApiListener<HomeScreenBannerDataRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getHomeScreenBannerData(HashMap<String, String> hashMap, HomeScreenBannerDataReq homeScreenBannerDataReq, CoveApiListener<HomeScreenBannerDataRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
