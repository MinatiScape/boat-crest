package com.coveiot.coveaccess;

import android.util.Log;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.runsession.GetAllSessionResponse;
import com.coveiot.coveaccess.runsession.GetSessionDataRequest;
import com.coveiot.coveaccess.runsession.GetSessionDataResponse;
import com.coveiot.coveaccess.runsession.PostSessionDataRequest;
import com.coveiot.coveaccess.runsession.PostSessionDataResponse;
import com.coveiot.coveaccess.runsession.SGetAllSessionResponse;
import com.coveiot.coveaccess.runsession.SGetSessionDataResponse;
import com.coveiot.coveaccess.runsession.SPostSessionDataResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveRunTracking {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SPostSessionDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6300a;

        public a(CoveApiListener coveApiListener) {
            this.f6300a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPostSessionDataResponse> call, Throwable th) {
            this.f6300a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPostSessionDataResponse> call, Response<SPostSessionDataResponse> response) {
            Log.d("API Response", response.body().toString());
            if (response.isSuccessful()) {
                this.f6300a.onSuccess(new PostSessionDataResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6300a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetSessionDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6301a;

        public b(CoveApiListener coveApiListener) {
            this.f6301a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSessionDataResponse> call, Throwable th) {
            this.f6301a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSessionDataResponse> call, Response<SGetSessionDataResponse> response) {
            if (response.isSuccessful()) {
                this.f6301a.onSuccess(new GetSessionDataResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6301a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SGetAllSessionResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6302a;

        public c(CoveApiListener coveApiListener) {
            this.f6302a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetAllSessionResponse> call, Throwable th) {
            this.f6302a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetAllSessionResponse> call, Response<SGetAllSessionResponse> response) {
            if (response.isSuccessful()) {
                this.f6302a.onSuccess(new GetAllSessionResponse(response.code(), response.body().data));
                return;
            }
            int code = response.code();
            this.f6302a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<GetAllSessionResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAllSessions(map).enqueue(new c(coveApiListener));
    }

    public static void b(GetSessionDataRequest getSessionDataRequest, CoveApiListener<GetSessionDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSessionDataForId(map, getSessionDataRequest.getFitnessSessionId()).enqueue(new b(coveApiListener));
    }

    public static void c(PostSessionDataRequest postSessionDataRequest, CoveApiListener<PostSessionDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postSessionDataToServer(map, postSessionDataRequest).enqueue(new a(coveApiListener));
    }

    public static void getAllSessions(CoveApiListener<GetAllSessionResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSessionDataForSessionId(GetSessionDataRequest getSessionDataRequest, CoveApiListener<GetSessionDataResponse, CoveApiErrorModel> coveApiListener) {
        b(getSessionDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendSessionDataToServer(PostSessionDataRequest postSessionDataRequest, CoveApiListener<PostSessionDataResponse, CoveApiErrorModel> coveApiListener) {
        c(postSessionDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAllSessions(HashMap<String, String> hashMap, CoveApiListener<GetAllSessionResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getSessionDataForSessionId(HashMap<String, String> hashMap, GetSessionDataRequest getSessionDataRequest, CoveApiListener<GetSessionDataResponse, CoveApiErrorModel> coveApiListener) {
        b(getSessionDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendSessionDataToServer(HashMap<String, String> hashMap, PostSessionDataRequest postSessionDataRequest, CoveApiListener<PostSessionDataResponse, CoveApiErrorModel> coveApiListener) {
        c(postSessionDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
