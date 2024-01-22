package com.coveiot.coveaccess.watchface;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.coveaccess.watchface.model.WatchFaceCategoriesResponse;
import com.coveiot.coveaccess.watchface.model.WatchfaceByIdRequest;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class WatchFaceApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SWatchFaceList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6900a;

        public a(CoveApiListener coveApiListener) {
            this.f6900a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWatchFaceList> call, Throwable th) {
            this.f6900a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWatchFaceList> call, Response<SWatchFaceList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6900a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6900a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SWatchFaceList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6901a;

        public b(CoveApiListener coveApiListener) {
            this.f6901a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWatchFaceList> call, Throwable th) {
            this.f6901a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWatchFaceList> call, Response<SWatchFaceList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6901a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6901a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SWatchFaceList> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6902a;

        public c(CoveApiListener coveApiListener) {
            this.f6902a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SWatchFaceList> call, Throwable th) {
            this.f6902a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SWatchFaceList> call, Response<SWatchFaceList> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6902a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6902a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<WatchFaceCategoriesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6903a;

        public d(CoveApiListener coveApiListener) {
            this.f6903a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<WatchFaceCategoriesResponse> call, Throwable th) {
            this.f6903a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<WatchFaceCategoriesResponse> call, Response<WatchFaceCategoriesResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6903a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6903a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, WatchfaceByIdRequest watchfaceByIdRequest) {
        CoveApi.getService().getWatchFaceListById(hashMap, watchfaceByIdRequest).enqueue(new b(coveApiListener));
    }

    public static void b(CoveApiListener<WatchFaceCategoriesResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        CoveApi.getService().getWatchFacesCategory(hashMap, str).enqueue(new d(coveApiListener));
    }

    public static void c(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2, String str3, int i, int i2) {
        CoveApi.getService().getWatchFaceList(hashMap, str, str2, str3, i, i2).enqueue(new a(coveApiListener));
    }

    public static void d(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2, String str3, String str4, int i, int i2) {
        CoveApi.getService().getWatchFaceListByCategory(hashMap, str, str2, str3, str4, i, i2).enqueue(new c(coveApiListener));
    }

    public static void getWatchFaceLisByCategoryId(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, String str, String str2, String str3, String str4, int i, int i2) {
        d(coveApiListener, CoveApi.getCustomHeaders(), str, str2, str3, str4, i, i2);
    }

    public static void getWatchFaceList(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, String str, String str2, String str3, int i, int i2) {
        c(coveApiListener, CoveApi.getCustomHeaders(), str, str2, str3, i, i2);
    }

    public static void getWatchFaceListById(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, WatchfaceByIdRequest watchfaceByIdRequest) {
        a(coveApiListener, CoveApi.getCustomHeaders(), watchfaceByIdRequest);
    }

    public static void getWatchFacesCategoryList(CoveApiListener<WatchFaceCategoriesResponse, CoveApiErrorModel> coveApiListener, String str) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getWatchFaceLisByCategoryId(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2, String str3, String str4, int i, int i2) {
        d(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, str2, str3, str4, i, i2);
    }

    public static void getWatchFaceList(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2, String str3, int i, int i2) {
        c(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, str2, str3, i, i2);
    }

    public static void getWatchFaceListById(CoveApiListener<SWatchFaceList, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, WatchfaceByIdRequest watchfaceByIdRequest) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), watchfaceByIdRequest);
    }

    public static void getWatchFacesCategoryList(CoveApiListener<WatchFaceCategoriesResponse, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }
}
