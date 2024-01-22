package com.mappls.sdk.services.api.predictive.distance;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.predictive.distance.models.PredictiveDistanceResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes7.dex */
public class MapplsPredictiveDistanceManager {
    private final MapplsPredictiveDistance mapplsPredictiveDistance;

    /* loaded from: classes7.dex */
    public class a implements Callback<PredictiveDistanceResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13258a;

        public a(MapplsPredictiveDistanceManager mapplsPredictiveDistanceManager, OnResponseCallback onResponseCallback) {
            this.f13258a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PredictiveDistanceResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13258a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13258a.onError(1, th.getMessage());
            } else {
                this.f13258a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PredictiveDistanceResponse> call, Response<PredictiveDistanceResponse> response) {
            if (response.code() == 200) {
                this.f13258a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13258a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13258a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13258a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13258a.onError(response.code(), response.message());
                }
            } else {
                this.f13258a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsPredictiveDistanceManager(MapplsPredictiveDistance mapplsPredictiveDistance) {
        this.mapplsPredictiveDistance = mapplsPredictiveDistance;
    }

    public static MapplsPredictiveDistanceManager newInstance(MapplsPredictiveDistance mapplsPredictiveDistance) {
        return new MapplsPredictiveDistanceManager(mapplsPredictiveDistance);
    }

    public void call(@NonNull OnResponseCallback<PredictiveDistanceResponse> onResponseCallback) {
        this.mapplsPredictiveDistance.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsPredictiveDistance.cancel();
    }

    public PredictiveDistanceResponse execute() throws IOException {
        return this.mapplsPredictiveDistance.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsPredictiveDistance.executed();
    }
}
