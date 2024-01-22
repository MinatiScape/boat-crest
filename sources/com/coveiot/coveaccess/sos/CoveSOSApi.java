package com.coveiot.coveaccess.sos;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.sos.model.SosEventReq;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveSOSApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6733a;

        public a(CoveApiListener coveApiListener) {
            this.f6733a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6733a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6733a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6733a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6733a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6733a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    public static void a(Map<String, String> map, SosEventReq sosEventReq, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().postSosEvent(map, sosEventReq).enqueue(new a(coveApiListener));
    }

    public static void postSosEvent(SosEventReq sosEventReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), sosEventReq, coveApiListener);
    }

    public static void postSosEvent(HashMap<String, String> hashMap, SosEventReq sosEventReq, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), sosEventReq, coveApiListener);
    }
}
