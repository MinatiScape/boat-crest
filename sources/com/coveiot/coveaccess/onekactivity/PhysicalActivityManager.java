package com.coveiot.coveaccess.onekactivity;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class PhysicalActivityManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SPhysicalActivityListRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6675a;

        public a(CoveApiListener coveApiListener) {
            this.f6675a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhysicalActivityListRes> call, Throwable th) {
            this.f6675a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhysicalActivityListRes> call, Response<SPhysicalActivityListRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6675a.onSuccess(response.body());
            } else {
                this.f6675a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SPhysicalActivityListRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6676a;

        public b(CoveApiListener coveApiListener) {
            this.f6676a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhysicalActivityListRes> call, Throwable th) {
            this.f6676a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhysicalActivityListRes> call, Response<SPhysicalActivityListRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6676a.onSuccess(response.body());
            } else {
                this.f6676a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<SPhysicalActivityCategoriesRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6677a;

        public c(CoveApiListener coveApiListener) {
            this.f6677a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SPhysicalActivityCategoriesRes> call, Throwable th) {
            this.f6677a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SPhysicalActivityCategoriesRes> call, Response<SPhysicalActivityCategoriesRes> response) {
            int code = response.code();
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6677a.onSuccess(response.body());
            } else {
                this.f6677a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
            }
        }
    }

    public static void a(CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getInBuiltPhysicalActivityList(map).enqueue(new b(coveApiListener));
    }

    public static void b(CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel> coveApiListener, Map<String, String> map, int i) {
        CoveApi.getService().getPhysicalActivityList(map, i).enqueue(new a(coveApiListener));
    }

    public static void c(CoveApiListener<SPhysicalActivityCategoriesRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getPhysicalActivityCategories(map).enqueue(new c(coveApiListener));
    }

    public static void getInBuiltPhysicalActivityList(CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getPhysicalActivityCategories(CoveApiListener<SPhysicalActivityCategoriesRes, CoveApiErrorModel> coveApiListener) {
        c(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getPhysicalActivityList(CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel> coveApiListener, int i) {
        b(coveApiListener, CoveApi.getCustomHeaders(), i);
    }
}
