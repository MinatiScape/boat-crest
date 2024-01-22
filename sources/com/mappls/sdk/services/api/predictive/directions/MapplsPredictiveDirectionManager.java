package com.mappls.sdk.services.api.predictive.directions;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.predictive.directions.model.PredictiveDirectionsResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes7.dex */
public class MapplsPredictiveDirectionManager {
    private final MapplsPredictiveDirections mapplsPredictiveDirections;

    /* loaded from: classes7.dex */
    public class a implements Callback<PredictiveDirectionsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13255a;

        public a(MapplsPredictiveDirectionManager mapplsPredictiveDirectionManager, OnResponseCallback onResponseCallback) {
            this.f13255a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PredictiveDirectionsResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13255a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13255a.onError(1, th.getMessage());
            } else {
                this.f13255a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PredictiveDirectionsResponse> call, Response<PredictiveDirectionsResponse> response) {
            if (response.code() == 200) {
                this.f13255a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13255a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13255a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13255a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13255a.onError(response.code(), response.message());
                }
            } else {
                this.f13255a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsPredictiveDirectionManager(MapplsPredictiveDirections mapplsPredictiveDirections) {
        this.mapplsPredictiveDirections = mapplsPredictiveDirections;
    }

    public static MapplsPredictiveDirectionManager newInstance(MapplsPredictiveDirections mapplsPredictiveDirections) {
        return new MapplsPredictiveDirectionManager(mapplsPredictiveDirections);
    }

    public void call(@NonNull OnResponseCallback<PredictiveDirectionsResponse> onResponseCallback) {
        this.mapplsPredictiveDirections.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsPredictiveDirections.cancel();
    }

    public PredictiveDirectionsResponse execute() throws IOException {
        return this.mapplsPredictiveDirections.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsPredictiveDirections.executed();
    }
}
