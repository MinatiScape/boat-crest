package com.coveiot.coveaccess.hrvdata;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.hrv.SaveHrvDataRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SNewGetHrvDataRes;
import com.coveiot.coveaccess.model.server.SNewSaveHrvDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class NewHRVAPIManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6620a;

        public a(CoveApiListener coveApiListener) {
            this.f6620a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6620a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6620a.onSuccess(new SaveHrvDataRes(response.code()));
                        return;
                    } else {
                        this.f6620a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6620a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6620a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SNewGetHrvDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6621a;

        public b(CoveApiListener coveApiListener) {
            this.f6621a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SNewGetHrvDataRes> call, Throwable th) {
            this.f6621a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SNewGetHrvDataRes> call, Response<SNewGetHrvDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200) {
                this.f6621a.onSuccess(response.body());
            } else {
                this.f6621a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    public static void a(CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener, SNewSaveHrvDataReq sNewSaveHrvDataReq, Map<String, String> map) {
        CoveApi.getService().saveNewHRVDataToServer(map, sNewSaveHrvDataReq).enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<SNewGetHrvDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getNewHrvDataFromServer(map, str, str2, "HRV", z).enqueue(new b(coveApiListener));
    }

    public static void getHrvDataFromServer(String str, String str2, boolean z, CoveApiListener<SNewGetHrvDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveHrvHistoryData(SNewSaveHrvDataReq sNewSaveHrvDataReq, CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, sNewSaveHrvDataReq, CoveApi.getCustomHeaders());
    }

    public static void getHrvDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<SNewGetHrvDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveHrvHistoryData(HashMap<String, String> hashMap, SNewSaveHrvDataReq sNewSaveHrvDataReq, CoveApiListener<SaveHrvDataRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, sNewSaveHrvDataReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
