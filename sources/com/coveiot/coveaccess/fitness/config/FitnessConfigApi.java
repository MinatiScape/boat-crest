package com.coveiot.coveaccess.fitness.config;

import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.DefaultFitnessConfigDataResponse;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessSummaryResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.PersonalBestResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public final class FitnessConfigApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<FitnessSummaryResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6525a;

        public a(CoveApiListener coveApiListener) {
            this.f6525a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessSummaryResponse> call, Throwable th) {
            this.f6525a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessSummaryResponse> call, Response<FitnessSummaryResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6525a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6525a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<FitnessConfigResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6526a;

        public b(CoveApiListener coveApiListener) {
            this.f6526a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessConfigResponse> call, Throwable th) {
            this.f6526a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessConfigResponse> call, Response<FitnessConfigResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6526a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6526a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<DefaultFitnessConfigDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6527a;

        public c(CoveApiListener coveApiListener) {
            this.f6527a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DefaultFitnessConfigDataResponse> call, Throwable th) {
            this.f6527a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DefaultFitnessConfigDataResponse> call, Response<DefaultFitnessConfigDataResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6527a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6527a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<PersonalBestResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6528a;

        public d(CoveApiListener coveApiListener) {
            this.f6528a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PersonalBestResponse> call, Throwable th) {
            this.f6528a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PersonalBestResponse> call, Response<PersonalBestResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6528a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6528a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<DefaultFitnessConfigDataResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getDefaultFitnessConfigData(map).enqueue(new c(coveApiListener));
    }

    public static void b(FitnessConfigRequest fitnessConfigRequest, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (fitnessConfigRequest != null) {
            CoveApi.getService().saveFitnessConfigData(map, fitnessConfigRequest).enqueue(new b(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void c(CoveApiListener<FitnessSummaryResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessConfigData(map).enqueue(new a(coveApiListener));
    }

    public static void d(CoveApiListener<PersonalBestResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getPersonalBest(map).enqueue(new d(coveApiListener));
    }

    public static void getDefaultConfigData(CoveApiListener<DefaultFitnessConfigDataResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessConfigData(CoveApiListener<FitnessSummaryResponse, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getPersonalBestData(CoveApiListener<PersonalBestResponse, CoveApiErrorModel> coveApiListener) {
        d(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveFitnessConfigData(FitnessConfigRequest fitnessConfigRequest, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener) {
        b(fitnessConfigRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getDefaultConfigData(HashMap<String, String> hashMap, CoveApiListener<DefaultFitnessConfigDataResponse, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessConfigData(HashMap<String, String> hashMap, CoveApiListener<FitnessSummaryResponse, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getPersonalBestData(HashMap<String, String> hashMap, CoveApiListener<PersonalBestResponse, CoveApiErrorModel> coveApiListener) {
        d(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveFitnessConfigData(HashMap<String, String> hashMap, FitnessConfigRequest fitnessConfigRequest, CoveApiListener<FitnessConfigResponse, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        b(fitnessConfigRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
