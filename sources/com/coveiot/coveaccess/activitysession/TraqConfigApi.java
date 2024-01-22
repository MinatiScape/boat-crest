package com.coveiot.coveaccess.activitysession;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.FitnessDataAggregate;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.GetDailyFitnessAggregateData;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.PostDailyDataAggregate;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.SGetDailyFitnessAggregateResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.FitnessDatum;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetActivityDailyFitnessResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsRes;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.PostDailyFitnessDataRequest;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SGetDailyFitnessDataResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SPostDailyFitnessDataResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SSaveFitnessDataRes;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class TraqConfigApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SActivitySessionDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6397a;

        public a(CoveApiListener coveApiListener) {
            this.f6397a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SActivitySessionDataResponse> call, Throwable th) {
            this.f6397a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SActivitySessionDataResponse> call, Response<SActivitySessionDataResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6397a.onSuccess(new PostActivitySessionHeaderResponse(response.code(), response.body().getRequestData()));
                return;
            }
            int code = response.code();
            this.f6397a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGetActivitySessionDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6398a;

        public b(CoveApiListener coveApiListener) {
            this.f6398a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetActivitySessionDataResponse> call, Throwable th) {
            this.f6398a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetActivitySessionDataResponse> call, Response<SGetActivitySessionDataResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            this.f6398a.onSuccess(new GetActivitySessionHeaderResponse(response.code(), response.body().data));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SActivitySessionDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6399a;

        public c(CoveApiListener coveApiListener) {
            this.f6399a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SActivitySessionDataResponse> call, Throwable th) {
            this.f6399a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SActivitySessionDataResponse> call, Response<SActivitySessionDataResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            this.f6399a.onSuccess(new PostActivitySessionHeaderResponse(response.code(), response.body().getRequestData()));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SPostDailyFitnessDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6400a;

        public d(CoveApiListener coveApiListener) {
            this.f6400a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPostDailyFitnessDataResponse> call, Throwable th) {
            this.f6400a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPostDailyFitnessDataResponse> call, Response<SPostDailyFitnessDataResponse> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6400a.onSuccess(new SSaveFitnessDataRes(response.code()));
                        return;
                    } else {
                        this.f6400a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6400a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6400a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SPostDailyFitnessDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6401a;

        public e(CoveApiListener coveApiListener) {
            this.f6401a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPostDailyFitnessDataResponse> call, Throwable th) {
            this.f6401a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPostDailyFitnessDataResponse> call, Response<SPostDailyFitnessDataResponse> response) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        this.f6401a.onSuccess(new SSaveFitnessDataRes(response.code()));
                        return;
                    } else {
                        this.f6401a.onError(new CoveApiErrorModel(response.body().getMessage(), response.code()));
                        return;
                    }
                }
                int code = response.code();
                this.f6401a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6401a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SGetDailyFitnessDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6402a;

        public f(CoveApiListener coveApiListener) {
            this.f6402a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetDailyFitnessDataResponse> call, Throwable th) {
            this.f6402a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetDailyFitnessDataResponse> call, Response<SGetDailyFitnessDataResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().getStatus().equalsIgnoreCase("ok")) {
                    List<FitnessDatum> arrayList = new ArrayList<>();
                    if (response.body().getFitnessResponse() != null) {
                        arrayList = response.body().getFitnessResponse().getRequestData();
                    }
                    this.f6402a.onSuccess(new GetActivityDailyFitnessResponse(response.code(), arrayList));
                    return;
                }
                int code = response.code();
                this.f6402a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6402a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<SGetDailyFitnessAggregateResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6403a;

        public g(CoveApiListener coveApiListener) {
            this.f6403a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetDailyFitnessAggregateResponse> call, Throwable th) {
            this.f6403a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetDailyFitnessAggregateResponse> call, Response<SGetDailyFitnessAggregateResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().getStatus().equalsIgnoreCase("ok")) {
                    List<FitnessDataAggregate> arrayList = new ArrayList<>();
                    if (response.body().getAggregateData() != null) {
                        arrayList = response.body().getAggregateData().getFitnessDataAggregates();
                    }
                    this.f6403a.onSuccess(new GetDailyFitnessAggregateData(response.code(), arrayList));
                    return;
                }
                int code = response.code();
                this.f6403a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6403a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<GetOverallStatsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6404a;

        public h(CoveApiListener coveApiListener) {
            this.f6404a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetOverallStatsResponse> call, Throwable th) {
            this.f6404a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetOverallStatsResponse> call, Response<GetOverallStatsResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().getStatus().equalsIgnoreCase("ok")) {
                    GetOverallStatsResponse getOverallStatsResponse = new GetOverallStatsResponse();
                    if (response.body().getOverallStatsData() != null) {
                        getOverallStatsResponse = response.body();
                    }
                    this.f6404a.onSuccess(new GetOverallStatsRes(response.code(), getOverallStatsResponse));
                    return;
                }
                int code = response.code();
                this.f6404a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                return;
            }
            int code2 = response.code();
            this.f6404a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code2), code2));
        }
    }

    public static void a(String str, String str2, CoveApiListener<GetDailyFitnessAggregateData, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessDataAggregate(map, str, str2).enqueue(new g(coveApiListener));
    }

    public static void b(String str, String str2, String str3, boolean z, CoveApiListener<GetActivityDailyFitnessResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getDailyFitnessDataFromServer(map, str, str2, str3, z).enqueue(new f(coveApiListener));
    }

    public static void c(String str, String str2, CoveApiListener<GetOverallStatsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFitnessOverallStats(map, str, str2).enqueue(new h(coveApiListener));
    }

    public static void d(int i, String str, String str2, String str3, CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSessionHeaderListFromServer(map, i, str, str2, str3).enqueue(new b(coveApiListener));
    }

    public static void e(int i, int i2, String str, ActivityType activityType, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSessionOverallDataFromServer(map, i, i2, str, activityType).enqueue(new c(coveApiListener));
    }

    public static void f(PostDailyDataAggregate postDailyDataAggregate, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postFitnessDataAggregate(map, postDailyDataAggregate).enqueue(new e(coveApiListener));
    }

    public static void g(PostDailyFitnessDataRequest postDailyFitnessDataRequest, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<SPostDailyFitnessDataResponse> postDailyFitnessDataToServer = CoveApi.getService().postDailyFitnessDataToServer(map, postDailyFitnessDataRequest);
        if (CoveApi.getInstance().isEgApp()) {
            postDailyFitnessDataToServer = CoveApi.getService().postEgDailyFitnessDataToServer(map, postDailyFitnessDataRequest);
        }
        postDailyFitnessDataToServer.enqueue(new d(coveApiListener));
    }

    public static void getDailyFitnessDataAggregate(String str, String str2, CoveApiListener<GetDailyFitnessAggregateData, CoveApiErrorModel> coveApiListener) {
        a(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getDailyFitnessDataFromServer(String str, String str2, String str3, boolean z, CoveApiListener<GetActivityDailyFitnessResponse, CoveApiErrorModel> coveApiListener) {
        b(str, str2, str3, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getOverallStats(String str, String str2, CoveApiListener<GetOverallStatsRes, CoveApiErrorModel> coveApiListener) {
        c(str, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSessionHeaderListFromServer(int i, String str, String str2, String str3, CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        d(i, str, str2, str3, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSessionOverallDataFromServer(int i, int i2, String str, ActivityType activityType, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        e(i, i2, str, activityType, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(PostActivitySessionDataRequest postActivitySessionDataRequest, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postSessionDataToServer(map, postActivitySessionDataRequest).enqueue(new a(coveApiListener));
    }

    public static void postDailyFitnessDataToServer(PostDailyFitnessDataRequest postDailyFitnessDataRequest, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener) {
        g(postDailyFitnessDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postFitnessDataAggregate(PostDailyDataAggregate postDailyDataAggregate, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener) {
        f(postDailyDataAggregate, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendSessionHeaderDataToServer(PostActivitySessionDataRequest postActivitySessionDataRequest, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        h(postActivitySessionDataRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getDailyFitnessDataAggregate(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetDailyFitnessAggregateData, CoveApiErrorModel> coveApiListener) {
        a(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getDailyFitnessDataFromServer(HashMap<String, String> hashMap, String str, String str2, String str3, boolean z, CoveApiListener<GetActivityDailyFitnessResponse, CoveApiErrorModel> coveApiListener) {
        b(str, str2, str3, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getOverallStats(HashMap<String, String> hashMap, String str, String str2, CoveApiListener<GetOverallStatsRes, CoveApiErrorModel> coveApiListener) {
        c(str, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getSessionHeaderListFromServer(int i, HashMap<String, String> hashMap, String str, String str2, String str3, CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        d(i, str, str2, str3, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getSessionOverallDataFromServer(int i, HashMap<String, String> hashMap, int i2, String str, ActivityType activityType, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        e(i, i2, str, activityType, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void postDailyFitnessDataToServer(HashMap<String, String> hashMap, PostDailyFitnessDataRequest postDailyFitnessDataRequest, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener) {
        g(postDailyFitnessDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void postFitnessDataAggregate(HashMap<String, String> hashMap, PostDailyDataAggregate postDailyDataAggregate, CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel> coveApiListener) {
        f(postDailyDataAggregate, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendSessionHeaderDataToServer(HashMap<String, String> hashMap, PostActivitySessionDataRequest postActivitySessionDataRequest, CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel> coveApiListener) {
        h(postActivitySessionDataRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
