package com.mappls.sdk.services.api.costestimation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsCostEstimationManager {
    private final MapplsCostEstimation mapplsCostEstimation;

    /* loaded from: classes11.dex */
    public class a implements Callback<CostEstimationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13159a;

        public a(MapplsCostEstimationManager mapplsCostEstimationManager, OnResponseCallback onResponseCallback) {
            this.f13159a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CostEstimationResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13159a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13159a.onError(1, th.getMessage());
            } else {
                this.f13159a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CostEstimationResponse> call, Response<CostEstimationResponse> response) {
            if (response.code() == 200) {
                this.f13159a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13159a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13159a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13159a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13159a.onError(response.code(), response.message());
                }
            } else {
                this.f13159a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsCostEstimationManager(MapplsCostEstimation mapplsCostEstimation) {
        this.mapplsCostEstimation = mapplsCostEstimation;
    }

    public static MapplsCostEstimationManager newInstance(MapplsCostEstimation mapplsCostEstimation) {
        return new MapplsCostEstimationManager(mapplsCostEstimation);
    }

    public void call(@NonNull OnResponseCallback<CostEstimationResponse> onResponseCallback) {
        this.mapplsCostEstimation.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsCostEstimation.cancel();
    }

    public CostEstimationResponse execute() throws IOException {
        return this.mapplsCostEstimation.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsCostEstimation.executed();
    }
}
