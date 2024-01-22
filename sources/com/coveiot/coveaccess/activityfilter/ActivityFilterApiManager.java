package com.coveiot.coveaccess.activityfilter;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class ActivityFilterApiManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<ActivityFilter> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6378a;

        public a(CoveApiListener coveApiListener) {
            this.f6378a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityFilter> call, Throwable th) {
            this.f6378a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityFilter> call, Response<ActivityFilter> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6378a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6378a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<ActivityFilter, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str) {
        CoveApi.getService().getActivityFilterOption(hashMap, str).enqueue(new a(coveApiListener));
    }

    public static void getActivityFilterOption(CoveApiListener<ActivityFilter, CoveApiErrorModel> coveApiListener, String str) {
        a(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getActivityFilterOption(CoveApiListener<ActivityFilter, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2, String str3, int i, int i2) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str3);
    }
}
