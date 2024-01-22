package com.mappls.sdk.services.api.event.catmaster;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.event.catmaster.model.ReportMasterResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes2.dex */
public class MapplsCategoryMasterManager {
    private final MapplsCategoryMaster mapplsCategoryMaster;

    /* loaded from: classes2.dex */
    public class a implements Callback<ReportMasterResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13216a;

        public a(MapplsCategoryMasterManager mapplsCategoryMasterManager, OnResponseCallback onResponseCallback) {
            this.f13216a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<ReportMasterResponse> call, @NonNull Throwable th) {
            if (call.isCanceled()) {
                this.f13216a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13216a.onError(1, th.getMessage());
            } else {
                this.f13216a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<ReportMasterResponse> call, @NonNull Response<ReportMasterResponse> response) {
            if (response.code() == 200) {
                this.f13216a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13216a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13216a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13216a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13216a.onError(response.code(), response.message());
                }
            } else {
                this.f13216a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsCategoryMasterManager(MapplsCategoryMaster mapplsCategoryMaster) {
        this.mapplsCategoryMaster = mapplsCategoryMaster;
    }

    public static MapplsCategoryMasterManager newInstance(MapplsCategoryMaster mapplsCategoryMaster) {
        return new MapplsCategoryMasterManager(mapplsCategoryMaster);
    }

    public void call(@NonNull OnResponseCallback<ReportMasterResponse> onResponseCallback) {
        this.mapplsCategoryMaster.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsCategoryMaster.cancel();
    }

    public ReportMasterResponse execute() throws IOException {
        return this.mapplsCategoryMaster.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsCategoryMaster.executed();
    }
}
