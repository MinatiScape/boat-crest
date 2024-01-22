package com.coveiot.coveaccess.weeklyreport;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.coveaccess.weeklyreport.request.GenerateOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.VerifyOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportSubscriptionReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportUnsubscribeReq;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.coveaccess.weeklyreport.response.GenerateVerifyOtpRes;
import com.coveiot.coveaccess.weeklyreport.response.WeekReportSubscriptionResponse;
import com.coveiot.coveaccess.weeklyreport.response.WeeklyReportSubscriptionStatusResponse;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class WeeklyReportApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<GenerateVerifyOtpRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6907a;

        public a(CoveApiListener coveApiListener) {
            this.f6907a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenerateVerifyOtpRes> call, Throwable th) {
            this.f6907a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenerateVerifyOtpRes> call, Response<GenerateVerifyOtpRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6907a.onSuccess(response.body());
            } else {
                this.f6907a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<GenerateVerifyOtpRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6908a;

        public b(CoveApiListener coveApiListener) {
            this.f6908a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GenerateVerifyOtpRes> call, Throwable th) {
            this.f6908a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GenerateVerifyOtpRes> call, Response<GenerateVerifyOtpRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6908a.onSuccess(response.body());
            } else {
                this.f6908a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<WeekReportSubscriptionResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6909a;

        public c(CoveApiListener coveApiListener) {
            this.f6909a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<WeekReportSubscriptionResponse> call, Throwable th) {
            this.f6909a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<WeekReportSubscriptionResponse> call, Response<WeekReportSubscriptionResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6909a.onSuccess(response.body());
            } else {
                this.f6909a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6910a;

        public d(CoveApiListener coveApiListener) {
            this.f6910a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6910a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6910a.onSuccess(response.body());
            } else {
                this.f6910a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<WeeklyReportSubscriptionStatusResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6911a;

        public e(CoveApiListener coveApiListener) {
            this.f6911a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<WeeklyReportSubscriptionStatusResponse> call, Throwable th) {
            this.f6911a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<WeeklyReportSubscriptionStatusResponse> call, Response<WeeklyReportSubscriptionStatusResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6911a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6911a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<CommonResponseGeneric<FitnessReportRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6912a;

        public f(CoveApiListener coveApiListener) {
            this.f6912a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<FitnessReportRes>> call, Throwable th) {
            this.f6912a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<FitnessReportRes>> call, Response<CommonResponseGeneric<FitnessReportRes>> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6912a.onSuccess(response.body().getData());
                return;
            }
            int code = response.code();
            this.f6912a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<WeeklyReportSubscriptionStatusResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getSubscriptionStatus(hashMap).enqueue(new e(coveApiListener));
    }

    public static void b(GenerateOtpReq generateOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().generateOtpWithEmail(map, generateOtpReq).enqueue(new a(coveApiListener));
    }

    public static void c(VerifyOtpReq verifyOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().verifyEmailOtp(map, verifyOtpReq).enqueue(new b(coveApiListener));
    }

    public static void d(WeeklyReportSubscriptionReq weeklyReportSubscriptionReq, CoveApiListener<WeekReportSubscriptionResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().subscribeForFitnessWeeklyReport(map, weeklyReportSubscriptionReq).enqueue(new c(coveApiListener));
    }

    public static void e(WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().unsubscribeFromFitnessWeeklyReport(map, weeklyReportUnsubscribeReq).enqueue(new d(coveApiListener));
    }

    public static void f(CoveApiListener<FitnessReportRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getWeeklyFitnessReport(hashMap).enqueue(new f(coveApiListener));
    }

    public static void getSubscriptionStatus(CoveApiListener<WeeklyReportSubscriptionStatusResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getWeeklyFitnessReports(CoveApiListener<FitnessReportRes, CoveApiErrorModel> coveApiListener) {
        f(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendEmailToGenerateOtp(GenerateOtpReq generateOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener) {
        b(generateOtpReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void subscribeForWeeklyReport(WeeklyReportSubscriptionReq weeklyReportSubscriptionReq, CoveApiListener<WeekReportSubscriptionResponse, CoveApiErrorModel> coveApiListener) {
        d(weeklyReportSubscriptionReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void unsubscribeFromWeeklyReport(WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener) {
        e(weeklyReportUnsubscribeReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void verifyEmailOtp(VerifyOtpReq verifyOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener) {
        c(verifyOtpReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getWeeklyFitnessReports(CoveApiListener<FitnessReportRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        f(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getSubscriptionStatus(HashMap<String, String> hashMap, CoveApiListener<WeeklyReportSubscriptionStatusResponse, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendEmailToGenerateOtp(HashMap<String, String> hashMap, GenerateOtpReq generateOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener) {
        b(generateOtpReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void subscribeForWeeklyReport(HashMap<String, String> hashMap, WeeklyReportSubscriptionReq weeklyReportSubscriptionReq, CoveApiListener<WeekReportSubscriptionResponse, CoveApiErrorModel> coveApiListener) {
        d(weeklyReportSubscriptionReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void unsubscribeFromWeeklyReport(HashMap<String, String> hashMap, WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener) {
        e(weeklyReportUnsubscribeReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void verifyEmailOtp(HashMap<String, String> hashMap, VerifyOtpReq verifyOtpReq, CoveApiListener<GenerateVerifyOtpRes, CoveApiErrorModel> coveApiListener) {
        c(verifyOtpReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
