package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.FitnessPlanHistoryRes;
import com.coveiot.coveaccess.model.server.FitnessPlanProgressReq;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.coveaccess.model.server.SActivatePlanResponse;
import com.coveiot.coveaccess.model.server.SCurrentFitnessPlanResponse;
import com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes;
import com.coveiot.coveaccess.model.server.SDeactivatePlanResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessPlanResponse;
import com.coveiot.coveaccess.model.server.SPlanProgressUpdateReq;
import com.coveiot.coveaccess.model.server.UserPlanInfoRes;
import com.coveiot.coveaccess.preparationplan.requestmodel.ActivateFitnessPlanReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.DeactivateFitnessPlanReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CovePreparationPlanApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<FitnessPlanTemplateRes> {
        public static final /* synthetic */ boolean b = true;

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6290a;

        public a(CoveApiListener coveApiListener) {
            this.f6290a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessPlanTemplateRes> call, Throwable th) {
            this.f6290a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessPlanTemplateRes> call, Response<FitnessPlanTemplateRes> response) {
            if (response.isSuccessful()) {
                if (!b && response.body() == null) {
                    throw new AssertionError();
                }
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6290a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6290a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetFitnessPlanResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6291a;

        public b(CoveApiListener coveApiListener) {
            this.f6291a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetFitnessPlanResponse> call, Throwable th) {
            this.f6291a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetFitnessPlanResponse> call, Response<SGetFitnessPlanResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6291a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6291a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SCurrentFitnessPlanResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6292a;

        public c(CoveApiListener coveApiListener) {
            this.f6292a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SCurrentFitnessPlanResponse> call, Throwable th) {
            this.f6292a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SCurrentFitnessPlanResponse> call, Response<SCurrentFitnessPlanResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6292a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6292a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SActivatePlanResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6293a;

        public d(CoveApiListener coveApiListener) {
            this.f6293a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SActivatePlanResponse> call, Throwable th) {
            this.f6293a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SActivatePlanResponse> call, Response<SActivatePlanResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6293a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6293a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SDeactivatePlanResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6294a;

        public e(CoveApiListener coveApiListener) {
            this.f6294a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDeactivatePlanResponse> call, Throwable th) {
            this.f6294a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDeactivatePlanResponse> call, Response<SDeactivatePlanResponse> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6294a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6294a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SCurrentPreparationPlanRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6295a;

        public f(CoveApiListener coveApiListener) {
            this.f6295a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SCurrentPreparationPlanRes> call, Throwable th) {
            this.f6295a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SCurrentPreparationPlanRes> call, Response<SCurrentPreparationPlanRes> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6295a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6295a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6296a;

        public g(CoveApiListener coveApiListener) {
            this.f6296a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6296a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                try {
                    if (response.body().getString(NotificationCompat.CATEGORY_STATUS).equalsIgnoreCase("ok")) {
                        PlanProgressUpdateRes planProgressUpdateRes = new PlanProgressUpdateRes(response.code());
                        planProgressUpdateRes.setMessage(response.body().getString(Constants.KEY_MESSAGE));
                        this.f6296a.onSuccess(planProgressUpdateRes);
                        return;
                    }
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.f6296a.onError(new CoveApiErrorModel(e.getMessage(), response.code()));
                    return;
                }
            }
            int code = response.code();
            this.f6296a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<CommonResponseClass> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6297a;

        public h(CoveApiListener coveApiListener) {
            this.f6297a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseClass> call, Throwable th) {
            this.f6297a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseClass> call, Response<CommonResponseClass> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6297a.onSuccess(response.body());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6297a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<FitnessPlanHistoryRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6298a;

        public i(CoveApiListener coveApiListener) {
            this.f6298a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FitnessPlanHistoryRes> call, Throwable th) {
            this.f6298a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FitnessPlanHistoryRes> call, Response<FitnessPlanHistoryRes> response) {
            if (response.isSuccessful()) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6298a.onSuccess(response.body().getFitnessPlanHistory());
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f6298a.onError(new CoveApiErrorModel(e.getMessage(), response.code()));
                    return;
                }
            }
            int code = response.code();
            this.f6298a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<UserPlanInfoRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6299a;

        public j(CoveApiListener coveApiListener) {
            this.f6299a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<UserPlanInfoRes> call, Throwable th) {
            this.f6299a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<UserPlanInfoRes> call, Response<UserPlanInfoRes> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    this.f6299a.onSuccess(response.body().getData());
                    return;
                }
                return;
            }
            int code = response.code();
            this.f6299a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(@NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, FitnessPlanProgressReq fitnessPlanProgressReq, Map<String, String> map) {
        CoveApi.getService().updatePlanProgress(map, fitnessPlanProgressReq).enqueue(new h(coveApiListener));
    }

    public static void activateFitnessPlan(ActivateFitnessPlanReq activateFitnessPlanReq, CoveApiListener<SActivatePlanResponse, CoveApiErrorModel> coveApiListener) {
        d(activateFitnessPlanReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void b(@NonNull CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> coveApiListener, SPlanProgressUpdateReq sPlanProgressUpdateReq, Map<String, String> map) {
        CoveApi.getService().updatePlanProgress(map, sPlanProgressUpdateReq).enqueue(new g(coveApiListener));
    }

    public static void c(CoveApiListener<SCurrentFitnessPlanResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getCurrentPlan(map).enqueue(new c(coveApiListener));
    }

    public static void d(ActivateFitnessPlanReq activateFitnessPlanReq, CoveApiListener<SActivatePlanResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().activateFitnessPlan(map, activateFitnessPlanReq).enqueue(new d(coveApiListener));
    }

    public static void deActivateFitnessPlan(DeactivateFitnessPlanReq deactivateFitnessPlanReq, CoveApiListener<SDeactivatePlanResponse, CoveApiErrorModel> coveApiListener) {
        e(deactivateFitnessPlanReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void e(DeactivateFitnessPlanReq deactivateFitnessPlanReq, CoveApiListener<SDeactivatePlanResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().deActivateFitnessPlan(map, deactivateFitnessPlanReq).enqueue(new e(coveApiListener));
    }

    public static void f(Map<String, String> map, @NonNull CoveApiListener<FitnessPlanHistoryRes.FitnessPlanHistoryData, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getFitnessHistory(map).enqueue(new i(coveApiListener));
    }

    public static void g(Map<String, String> map, String str, @NonNull CoveApiListener<UserPlanInfoRes.UserFitnessPlanData, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getUserFitnessPlanInfo(map, str).enqueue(new j(coveApiListener));
    }

    public static void getCurrentFitnessPlan(CoveApiListener<SCurrentFitnessPlanResponse, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getCurrentPreparationPlan(boolean z, CoveApiListener<SCurrentPreparationPlanRes, CoveApiErrorModel> coveApiListener) {
        h(z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessPlan(CoveApiListener<SGetFitnessPlanResponse, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFitnessPlanHistory(CoveApiListener<FitnessPlanHistoryRes.FitnessPlanHistoryData, CoveApiErrorModel> coveApiListener) {
        f(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getUserFitnessPlanInfo(String str, CoveApiListener<UserPlanInfoRes.UserFitnessPlanData, CoveApiErrorModel> coveApiListener) {
        g(CoveApi.getCustomHeaders(), str, coveApiListener);
    }

    public static void getUserFitnessPlanTemplate(String str, CoveApiListener<FitnessPlanTemplateRes.FitnessPlanTemplateData, CoveApiErrorModel> coveApiListener) {
        j(CoveApi.getCustomHeaders(), str, coveApiListener);
    }

    public static void h(boolean z, CoveApiListener<SCurrentPreparationPlanRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getCurrentPreparationPlan(map, z).enqueue(new f(coveApiListener));
    }

    public static void i(CoveApiListener<SGetFitnessPlanResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessPlan(map).enqueue(new b(coveApiListener));
    }

    public static void j(Map<String, String> map, String str, @NonNull CoveApiListener<FitnessPlanTemplateRes.FitnessPlanTemplateData, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getUserFitnessPlanTemplate(map, str).enqueue(new a(coveApiListener));
    }

    public static void updateFitnessProgress(CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, FitnessPlanProgressReq fitnessPlanProgressReq) {
        a(coveApiListener, fitnessPlanProgressReq, CoveApi.getCustomHeaders());
    }

    public static void updatePlanProgress(@NonNull PlanProgressUpdateReq planProgressUpdateReq, @NonNull CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> coveApiListener) {
        SPlanProgressUpdateReq sPlanProgressUpdateReq = new SPlanProgressUpdateReq();
        sPlanProgressUpdateReq.setUserPlanId(planProgressUpdateReq.getUserPlanId());
        sPlanProgressUpdateReq.setPercentage(planProgressUpdateReq.getPercentage());
        sPlanProgressUpdateReq.setProgressStatus(planProgressUpdateReq.getProgressStatus());
        b(coveApiListener, sPlanProgressUpdateReq, CoveApi.getCustomHeaders());
    }

    public static void activateFitnessPlan(HashMap<String, String> hashMap, ActivateFitnessPlanReq activateFitnessPlanReq, CoveApiListener<SActivatePlanResponse, CoveApiErrorModel> coveApiListener) {
        d(activateFitnessPlanReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void deActivateFitnessPlan(HashMap<String, String> hashMap, DeactivateFitnessPlanReq deactivateFitnessPlanReq, CoveApiListener<SDeactivatePlanResponse, CoveApiErrorModel> coveApiListener) {
        e(deactivateFitnessPlanReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getCurrentFitnessPlan(HashMap<String, String> hashMap, CoveApiListener<SCurrentFitnessPlanResponse, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getCurrentPreparationPlan(HashMap<String, String> hashMap, boolean z, CoveApiListener<SCurrentPreparationPlanRes, CoveApiErrorModel> coveApiListener) {
        h(z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessPlan(HashMap<String, String> hashMap, CoveApiListener<SGetFitnessPlanResponse, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFitnessPlanHistory(HashMap<String, String> hashMap, CoveApiListener<FitnessPlanHistoryRes.FitnessPlanHistoryData, CoveApiErrorModel> coveApiListener) {
        f(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getUserFitnessPlanInfo(HashMap<String, String> hashMap, String str, CoveApiListener<UserPlanInfoRes.UserFitnessPlanData, CoveApiErrorModel> coveApiListener) {
        g(CoveUtil.getRevisedHeaders(hashMap), str, coveApiListener);
    }

    public static void getUserFitnessPlanTemplate(HashMap<String, String> hashMap, String str, CoveApiListener<FitnessPlanTemplateRes.FitnessPlanTemplateData, CoveApiErrorModel> coveApiListener) {
        j(CoveUtil.getRevisedHeaders(hashMap), str, coveApiListener);
    }

    public static void updateFitnessProgress(HashMap<String, String> hashMap, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, FitnessPlanProgressReq fitnessPlanProgressReq) {
        a(coveApiListener, fitnessPlanProgressReq, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updatePlanProgress(HashMap<String, String> hashMap, @NonNull PlanProgressUpdateReq planProgressUpdateReq, @NonNull CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> coveApiListener) {
        SPlanProgressUpdateReq sPlanProgressUpdateReq = new SPlanProgressUpdateReq();
        sPlanProgressUpdateReq.setUserPlanId(planProgressUpdateReq.getUserPlanId());
        sPlanProgressUpdateReq.setPercentage(planProgressUpdateReq.getPercentage());
        sPlanProgressUpdateReq.setProgressStatus(planProgressUpdateReq.getProgressStatus());
        b(coveApiListener, sPlanProgressUpdateReq, CoveUtil.getRevisedHeaders(hashMap));
    }
}
