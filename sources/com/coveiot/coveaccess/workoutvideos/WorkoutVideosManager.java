package com.coveiot.coveaccess.workoutvideos;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWorkoutCategoryList;
import com.coveiot.coveaccess.model.server.SWorkoutVideosList;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class WorkoutVideosManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SWorkoutVideosList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6928a;

        public a(CoveApiListener coveApiListener) {
            this.f6928a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWorkoutVideosList> call, Throwable th) {
            this.f6928a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWorkoutVideosList> call, Response<SWorkoutVideosList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6928a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6928a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SWorkoutCategoryList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6929a;

        public b(CoveApiListener coveApiListener) {
            this.f6929a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWorkoutCategoryList> call, Throwable th) {
            this.f6929a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWorkoutCategoryList> call, Response<SWorkoutCategoryList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6929a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6929a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SWorkoutVideosList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6930a;

        public c(CoveApiListener coveApiListener) {
            this.f6930a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWorkoutVideosList> call, Throwable th) {
            this.f6930a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWorkoutVideosList> call, Response<SWorkoutVideosList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6930a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6930a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<SWorkoutCategoryList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6931a;

        public d(CoveApiListener coveApiListener) {
            this.f6931a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWorkoutCategoryList> call, Throwable th) {
            this.f6931a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWorkoutCategoryList> call, Response<SWorkoutCategoryList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6931a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6931a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SWorkoutVideosList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6932a;

        public e(CoveApiListener coveApiListener) {
            this.f6932a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWorkoutVideosList> call, Throwable th) {
            this.f6932a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWorkoutVideosList> call, Response<SWorkoutVideosList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6932a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6932a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getCategoriesList(hashMap).enqueue(new b(coveApiListener));
    }

    public static void b(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        CoveApi.getService().getVideoCategoriesList(hashMap, str).enqueue(new d(coveApiListener));
    }

    public static void c(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, int i, int i2) {
        CoveApi.getService().getWorkoutVideosList(hashMap, str, i, i2).enqueue(new a(coveApiListener));
    }

    public static void d(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, int i, int i2, String str2) {
        CoveApi.getService().getVideosList(hashMap, str, i, i2, str2).enqueue(new c(coveApiListener));
    }

    public static void e(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        CoveApi.getService().getVideoRecommendationsList(hashMap, str).enqueue(new e(coveApiListener));
    }

    public static void getCategoryList(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getVideoCategoryList(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener, String str) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getVideoRecommendationsList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, String str) {
        e(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getVideosList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, String str, int i, int i2, String str2) {
        d(coveApiListener, CoveApi.getCustomHeaders(), str, i, i2, str2);
    }

    public static void getWorkoutVideosList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, String str, int i, int i2) {
        c(coveApiListener, CoveApi.getCustomHeaders(), str, i, i2);
    }

    public static void getCategoryList(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getVideoCategoryList(CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getVideoRecommendationsList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        e(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getVideosList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, int i, int i2, String str2) {
        d(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, i, i2, str2);
    }

    public static void getWorkoutVideosList(CoveApiListener<SWorkoutVideosList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, int i, int i2) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, i, i2);
    }
}
