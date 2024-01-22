package com.coveiot.coveaccess.alexa;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.alexa.model.request.ActivateAlexaReq;
import com.coveiot.coveaccess.alexa.model.response.ActivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.DeactivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.GetStatusAlexaRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveAlexaApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivateAlexaRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6406a;

        public a(CoveApiListener coveApiListener) {
            this.f6406a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivateAlexaRes> call, Throwable th) {
            this.f6406a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivateAlexaRes> call, Response<ActivateAlexaRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6406a.onSuccess(response.body());
            } else {
                this.f6406a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<DeactivateAlexaRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6407a;

        public b(CoveApiListener coveApiListener) {
            this.f6407a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DeactivateAlexaRes> call, Throwable th) {
            this.f6407a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DeactivateAlexaRes> call, Response<DeactivateAlexaRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6407a.onSuccess(response.body());
            } else {
                this.f6407a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<GetStatusAlexaRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6408a;

        public c(CoveApiListener coveApiListener) {
            this.f6408a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetStatusAlexaRes> call, Throwable th) {
            this.f6408a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetStatusAlexaRes> call, Response<GetStatusAlexaRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6408a.onSuccess(response.body());
            } else {
                this.f6408a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    public static void a(CoveApiListener<DeactivateAlexaRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().deactivateAlexaAccountLinking(hashMap).enqueue(new b(coveApiListener));
    }

    public static void activateAlexaAccountLinking(ActivateAlexaReq activateAlexaReq, CoveApiListener<ActivateAlexaRes, CoveApiErrorModel> coveApiListener) {
        b(activateAlexaReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void b(ActivateAlexaReq activateAlexaReq, CoveApiListener<ActivateAlexaRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().activateAlexaAccountLinking(hashMap, activateAlexaReq).enqueue(new a(coveApiListener));
    }

    public static void c(CoveApiListener<GetStatusAlexaRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getStatusAlexaAccountLinking(hashMap).enqueue(new c(coveApiListener));
    }

    public static void deactivateAlexaAccountLinking(CoveApiListener<DeactivateAlexaRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getStatusAlexaAccountLinking(CoveApiListener<GetStatusAlexaRes, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void activateAlexaAccountLinking(HashMap<String, String> hashMap, ActivateAlexaReq activateAlexaReq, CoveApiListener<ActivateAlexaRes, CoveApiErrorModel> coveApiListener) {
        b(activateAlexaReq, coveApiListener, hashMap);
    }

    public static void deactivateAlexaAccountLinking(HashMap<String, String> hashMap, CoveApiListener<DeactivateAlexaRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, hashMap);
    }

    public static void getStatusAlexaAccountLinking(HashMap<String, String> hashMap, CoveApiListener<GetStatusAlexaRes, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, hashMap);
    }
}
