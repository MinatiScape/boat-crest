package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetHealthStatusRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveHealthStatusApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SGetHealthStatusRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6265a;

        public a(CoveApiListener coveApiListener) {
            this.f6265a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetHealthStatusRes> call, Throwable th) {
            this.f6265a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetHealthStatusRes> call, Response<SGetHealthStatusRes> response) {
            if (response.isSuccessful()) {
                this.f6265a.onSuccess(response.body());
            } else {
                this.f6265a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(@NonNull CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getHealthStatus(map).enqueue(new a(coveApiListener));
    }

    public static void getHealthStatus(@NonNull CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getHealthStatus(HashMap<String, String> hashMap, @NonNull CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
