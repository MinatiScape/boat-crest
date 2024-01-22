package com.coveiot.coveaccess.smartgrid;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveSmartGridApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<CommonResponseGeneric<GetSmartGridRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6731a;

        public a(CoveApiListener coveApiListener) {
            this.f6731a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<GetSmartGridRes>> call, Throwable th) {
            this.f6731a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<GetSmartGridRes>> call, Response<CommonResponseGeneric<GetSmartGridRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6731a.onSuccess(response.body().getData());
                        return;
                    } else {
                        this.f6731a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6731a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(Map<String, String> map, String str, @NonNull CoveApiListener<GetSmartGridRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getSmartGridItems(map, str).enqueue(new a(coveApiListener));
    }

    public static void getSmartGridItems(String str, CoveApiListener<GetSmartGridRes, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), str, coveApiListener);
    }

    public static void getSmartGridItems(HashMap<String, String> hashMap, String str, CoveApiListener<GetSmartGridRes, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), str, coveApiListener);
    }
}
