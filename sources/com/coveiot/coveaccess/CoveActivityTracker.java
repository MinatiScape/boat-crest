package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.ActivityData;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.fitness.model.GetGoalHistoryResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public final class CoveActivityTracker {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6254a;

        public a(CoveApiListener coveApiListener) {
            this.f6254a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6254a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.isSuccessful()) {
                this.f6254a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6254a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<ActivityData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6255a;

        public b(CoveApiListener coveApiListener) {
            this.f6255a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityData> call, Throwable th) {
            this.f6255a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityData> call, Response<ActivityData> response) {
            if (response.isSuccessful()) {
                this.f6255a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6255a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<GetGoalHistoryResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6256a;

        public c(CoveApiListener coveApiListener) {
            this.f6256a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetGoalHistoryResponse> call, Throwable th) {
            this.f6256a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetGoalHistoryResponse> call, Response<GetGoalHistoryResponse> response) {
            if (response.isSuccessful() && response.code() == 200 && response.body() != null && response.body().getData() != null) {
                this.f6256a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6256a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(ActivityData activityData, CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (activityData != null) {
            Call<ActivityRes> postActivityDataToServer = CoveApi.getService().postActivityDataToServer(map, activityData);
            if (CoveApi.getInstance().isEgApp()) {
                postActivityDataToServer = CoveApi.getService().postEgActivityDataToServer(map, activityData);
            }
            postActivityDataToServer.enqueue(new a(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void b(String str, String str2, ActivityType activityType, boolean z, CoveApiListener<ActivityData, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (!CoveUtil.isEmpty(str) && !CoveUtil.isEmpty(str2) && !CoveUtil.isEmpty(activityType.getActivityType())) {
            CoveApi.getService().getActivityDataFromServer(map, str, str2, activityType, z).enqueue(new b(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void c(String str, String str2, String str3, @NonNull CoveApiListener<GetGoalHistoryResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getGoalHistory(map, str, str2, str3).enqueue(new c(coveApiListener));
    }

    public static void getActivityDataFromServer(String str, String str2, ActivityType activityType, boolean z, CoveApiListener<ActivityData, CoveApiErrorModel> coveApiListener) {
        b(str, str2, activityType, z, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getGoalHistory(String str, String str2, String str3, @NonNull CoveApiListener<GetGoalHistoryResponse, CoveApiErrorModel> coveApiListener) {
        c(str, str2, str3, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveActivityDataToServer(ActivityData activityData, CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener) {
        a(activityData, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getActivityDataFromServer(HashMap<String, String> hashMap, String str, String str2, ActivityType activityType, boolean z, CoveApiListener<ActivityData, CoveApiErrorModel> coveApiListener) {
        b(str, str2, activityType, z, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getGoalHistory(String str, String str2, String str3, HashMap<String, String> hashMap, @NonNull CoveApiListener<GetGoalHistoryResponse, CoveApiErrorModel> coveApiListener) {
        c(str, str2, str3, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveActivityDataToServer(HashMap<String, String> hashMap, ActivityData activityData, CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener) {
        a(activityData, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
