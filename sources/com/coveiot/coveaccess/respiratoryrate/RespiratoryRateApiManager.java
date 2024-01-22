package com.coveiot.coveaccess.respiratoryrate;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveApiService;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class RespiratoryRateApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<RespiratoryRateApiRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6690a;

        public a(CoveApiListener coveApiListener) {
            this.f6690a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<RespiratoryRateApiRes> call, Throwable th) {
            this.f6690a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<RespiratoryRateApiRes> call, Response<RespiratoryRateApiRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6690a.onSuccess(response.body());
            } else {
                this.f6690a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(@NonNull RespiratoryRateApiReq respiratoryRateApiReq, CoveApiListener<RespiratoryRateApiRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApiService service = CoveApi.getService();
        service.getRespiratoryRateByUsingPPGData(map, CoveApi.getInstance().getGateWayUrl() + "v1/algosrv/respiratory/rate", respiratoryRateApiReq).enqueue(new a(coveApiListener));
    }

    public static void sendDataForRespiratoryRateCal(HashMap<String, String> hashMap, @NonNull RespiratoryRateApiReq respiratoryRateApiReq, CoveApiListener<RespiratoryRateApiRes, CoveApiErrorModel> coveApiListener) {
        a(respiratoryRateApiReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDataForRespiratoryRateCal(@NonNull RespiratoryRateApiReq respiratoryRateApiReq, CoveApiListener<RespiratoryRateApiRes, CoveApiErrorModel> coveApiListener) {
        a(respiratoryRateApiReq, coveApiListener, CoveApi.getCustomHeaders());
    }
}
