package com.coveiot.coveaccess.ambientsound;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AmbientSoundDataBean;
import com.coveiot.coveaccess.model.server.SGetAmbientSoundDataRes;
import com.coveiot.coveaccess.model.server.SSaveAmbientSoundDataReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class AmbientSoundApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6415a;

        public a(CoveApiListener coveApiListener) {
            this.f6415a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6415a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6415a.onSuccess(new SaveAmbientSoundRes(response.code()));
                        return;
                    } else {
                        this.f6415a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6415a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6415a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetAmbientSoundDataRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6416a;

        public b(CoveApiListener coveApiListener) {
            this.f6416a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetAmbientSoundDataRes> call, Throwable th) {
            this.f6416a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetAmbientSoundDataRes> call, Response<SGetAmbientSoundDataRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                GetAmbientSoundDataRes getAmbientSoundDataRes = new GetAmbientSoundDataRes(response.code());
                ArrayList arrayList = new ArrayList();
                if (response.body().getData() != null && !CoveUtil.isEmpty(response.body().getData().getFitnessData())) {
                    for (AmbientSoundDataBean ambientSoundDataBean : response.body().getData().getFitnessData()) {
                        arrayList.add(AmbientSoundUtils.getServerAmbientSoundBeanfrom(ambientSoundDataBean));
                    }
                    getAmbientSoundDataRes.setAmbientSoundDataList(arrayList);
                    this.f6416a.onSuccess(getAmbientSoundDataRes);
                    return;
                } else if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6416a.onSuccess(null);
                    return;
                } else {
                    this.f6416a.onError(new CoveApiErrorModel(response.body().getMessage()));
                    return;
                }
            }
            this.f6416a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(SaveAmbientSoundReq saveAmbientSoundReq, CoveApiListener<SaveAmbientSoundRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SSaveAmbientSoundDataReq sSaveAmbientSoundDataReq = new SSaveAmbientSoundDataReq();
        ArrayList arrayList = new ArrayList();
        if (!CoveUtil.isEmpty(saveAmbientSoundReq.getAmbientSoundDataList())) {
            for (AmbientSoundData ambientSoundData : saveAmbientSoundReq.getAmbientSoundDataList()) {
                arrayList.add(AmbientSoundUtils.getServerAmbientSoundBeanfrom(ambientSoundData));
            }
        }
        sSaveAmbientSoundDataReq.setFitnessData(arrayList);
        Call<ActivityRes> postAmbientSoundDataToServer = CoveApi.getService().postAmbientSoundDataToServer(map, sSaveAmbientSoundDataReq);
        if (CoveApi.getInstance().isEgApp()) {
            postAmbientSoundDataToServer = CoveApi.getService().postEgAmbientSoundDataToServer(map, sSaveAmbientSoundDataReq);
        }
        postAmbientSoundDataToServer.enqueue(new a(coveApiListener));
    }

    public static void b(String str, String str2, boolean z, CoveApiListener<GetAmbientSoundDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAmbientSoundDataFromServer(map, str, str2, "AMBIENT_SOUND", z).enqueue(new b(coveApiListener));
    }

    public static void getAmbientSoundDataFromServer(String str, String str2, boolean z, CoveApiListener<GetAmbientSoundDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAmbientSoundData(SaveAmbientSoundReq saveAmbientSoundReq, CoveApiListener<SaveAmbientSoundRes, CoveApiErrorModel> coveApiListener) {
        a(saveAmbientSoundReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAmbientSoundDataFromServer(HashMap<String, String> hashMap, String str, String str2, boolean z, CoveApiListener<GetAmbientSoundDataRes, CoveApiErrorModel> coveApiListener) {
        b(str, str2, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAmbientSoundData(HashMap<String, String> hashMap, SaveAmbientSoundReq saveAmbientSoundReq, CoveApiListener<SaveAmbientSoundRes, CoveApiErrorModel> coveApiListener) {
        a(saveAmbientSoundReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
