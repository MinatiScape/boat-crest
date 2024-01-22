package com.coveiot.coveaccess;

import android.util.Log;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.STrackDetailsModel;
import com.coveiot.coveaccess.model.server.STrackListModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public final class CoveVirtualRunApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<STrackListModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6373a;

        public a(CoveApiListener coveApiListener) {
            this.f6373a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STrackListModel> call, Throwable th) {
            this.f6373a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STrackListModel> call, Response<STrackListModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6373a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6373a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<STrackDetailsModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6374a;

        public b(CoveApiListener coveApiListener) {
            this.f6374a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STrackDetailsModel> call, Throwable th) {
            this.f6374a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STrackDetailsModel> call, Response<STrackDetailsModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6374a.onSuccess(response.body());
                Log.d("data", "track details :" + new Gson().toJson(response.body()));
                return;
            }
            int code = response.code();
            this.f6374a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<STrackListModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getALlTrack(map).enqueue(new a(coveApiListener));
    }

    public static void b(String str, CoveApiListener<STrackDetailsModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getTrackDetailsById(map, str).enqueue(new b(coveApiListener));
    }

    public static void getTrackDetailsById(String str, CoveApiListener<STrackDetailsModel, CoveApiErrorModel> coveApiListener) {
        b(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getVirtualRunTrackList(CoveApiListener<STrackListModel, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getTrackDetailsById(HashMap<String, String> hashMap, String str, CoveApiListener<STrackDetailsModel, CoveApiErrorModel> coveApiListener) {
        b(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getVirtualRunTrackList(HashMap<String, String> hashMap, CoveApiListener<STrackListModel, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
