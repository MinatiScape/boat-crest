package com.mappls.sdk.services.api.transit;

import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.transit.model.TransitPlannerResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class MapplsTransitPlannerManager {

    /* renamed from: a  reason: collision with root package name */
    public final MapplsTransitPlanner f13289a;

    /* loaded from: classes8.dex */
    public class a implements Callback<TransitPlannerResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13290a;

        public a(MapplsTransitPlannerManager mapplsTransitPlannerManager, OnResponseCallback onResponseCallback) {
            this.f13290a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<TransitPlannerResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13290a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13290a.onError(1, th.getMessage());
            } else {
                this.f13290a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<TransitPlannerResponse> call, Response<TransitPlannerResponse> response) {
            if (response.code() == 200) {
                this.f13290a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13290a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13290a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13290a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13290a.onError(response.code(), response.message());
                }
            } else {
                this.f13290a.onError(response.code(), response.message());
            }
        }
    }

    public MapplsTransitPlannerManager(MapplsTransitPlanner mapplsTransitPlanner) {
        this.f13289a = mapplsTransitPlanner;
    }

    public static MapplsTransitPlannerManager newInstance(MapplsTransitPlanner mapplsTransitPlanner) {
        return new MapplsTransitPlannerManager(mapplsTransitPlanner);
    }

    public void call(OnResponseCallback<TransitPlannerResponse> onResponseCallback) {
        this.f13289a.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.f13289a.cancel();
    }

    public TransitPlannerResponse execute() throws IOException {
        return this.f13289a.execute().body();
    }

    public boolean isExecuted() {
        return this.f13289a.executed();
    }
}
