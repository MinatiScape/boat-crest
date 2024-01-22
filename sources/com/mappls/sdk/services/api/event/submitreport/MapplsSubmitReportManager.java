package com.mappls.sdk.services.api.event.submitreport;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes6.dex */
public class MapplsSubmitReportManager {

    /* renamed from: a  reason: collision with root package name */
    public final MapplsSubmitReport f13226a;

    /* loaded from: classes6.dex */
    public class a implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13227a;

        public a(MapplsSubmitReportManager mapplsSubmitReportManager, OnResponseCallback onResponseCallback) {
            this.f13227a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable th) {
            if (call.isCanceled()) {
                this.f13227a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13227a.onError(1, th.getMessage());
            } else {
                this.f13227a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
            if (response.code() == 201) {
                this.f13227a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13227a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13227a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13227a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13227a.onError(response.code(), response.message());
                }
            } else {
                this.f13227a.onError(response.code(), response.message());
            }
        }
    }

    public MapplsSubmitReportManager(MapplsSubmitReport mapplsSubmitReport) {
        this.f13226a = mapplsSubmitReport;
    }

    public static MapplsSubmitReportManager newInstance(MapplsSubmitReport mapplsSubmitReport) {
        return new MapplsSubmitReportManager(mapplsSubmitReport);
    }

    public void call(@NonNull OnResponseCallback<Void> onResponseCallback) {
        this.f13226a.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.f13226a.cancel();
    }

    public Void execute() throws IOException {
        return this.f13226a.execute().body();
    }

    public boolean isExecuted() {
        return this.f13226a.executed();
    }
}
