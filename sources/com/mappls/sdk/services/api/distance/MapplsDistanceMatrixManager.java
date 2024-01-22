package com.mappls.sdk.services.api.distance;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.distance.models.DistanceResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsDistanceMatrixManager {
    private final MapplsDistanceMatrix mapplsDistanceMatrix;

    /* loaded from: classes11.dex */
    public class a implements Callback<DistanceResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13209a;

        public a(MapplsDistanceMatrixManager mapplsDistanceMatrixManager, OnResponseCallback onResponseCallback) {
            this.f13209a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DistanceResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13209a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13209a.onError(1, th.getMessage());
            } else {
                this.f13209a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DistanceResponse> call, Response<DistanceResponse> response) {
            if (response.code() == 200) {
                this.f13209a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13209a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13209a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13209a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13209a.onError(response.code(), response.message());
                }
            } else {
                this.f13209a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsDistanceMatrixManager(MapplsDistanceMatrix mapplsDistanceMatrix) {
        this.mapplsDistanceMatrix = mapplsDistanceMatrix;
    }

    public static MapplsDistanceMatrixManager newInstance(MapplsDistanceMatrix mapplsDistanceMatrix) {
        return new MapplsDistanceMatrixManager(mapplsDistanceMatrix);
    }

    public void call(@NonNull OnResponseCallback<DistanceResponse> onResponseCallback) {
        this.mapplsDistanceMatrix.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsDistanceMatrix.cancel();
    }

    public DistanceResponse execute() throws IOException {
        return this.mapplsDistanceMatrix.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsDistanceMatrix.executed();
    }
}
