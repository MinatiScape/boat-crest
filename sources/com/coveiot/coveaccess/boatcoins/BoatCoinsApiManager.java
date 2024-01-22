package com.coveiot.coveaccess.boatcoins;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class BoatCoinsApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<BoatCoinsDetailsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6425a;

        public a(CoveApiListener coveApiListener) {
            this.f6425a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<BoatCoinsDetailsResponse> call, Throwable th) {
            this.f6425a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<BoatCoinsDetailsResponse> call, Response<BoatCoinsDetailsResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6425a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6425a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<BoatCoinsDetailsResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getBoatCoinsDetails(hashMap).enqueue(new a(coveApiListener));
    }

    public static void getBoatCoinsDetails(CoveApiListener<BoatCoinsDetailsResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBoatCoinsDetails(CoveApiListener<BoatCoinsDetailsResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
