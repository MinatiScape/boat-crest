package com.coveiot.coveaccess.ecgsession;

import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.ecgsession.model.PostECGSessionData;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public final class ECGSessionApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<FitnessConfigResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6481a;

        public a(CoveApiListener coveApiListener) {
            this.f6481a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessConfigResponse> call, Throwable th) {
            this.f6481a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessConfigResponse> call, Response<FitnessConfigResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6481a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6481a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(PostECGSessionData postECGSessionData, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().saveFitnessSessionData(map, postECGSessionData).enqueue(new a(coveApiListener));
    }

    public static void saveECGSessionData(PostECGSessionData postECGSessionData, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener) {
        if (postECGSessionData != null) {
            a(postECGSessionData, coveApiListener, CoveApi.getCustomHeaders());
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void saveECGSessionData(HashMap<String, String> hashMap, PostECGSessionData postECGSessionData, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener) {
        if (postECGSessionData != null) {
            a(postECGSessionData, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }
}
