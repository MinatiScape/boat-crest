package com.mappls.sdk.services.api.directions;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsDirectionManager {
    private MapplsDirections mapplsDirections;

    /* loaded from: classes11.dex */
    public class a implements Callback<DirectionsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13163a;

        public a(MapplsDirectionManager mapplsDirectionManager, OnResponseCallback onResponseCallback) {
            this.f13163a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DirectionsResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13163a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13163a.onError(1, th.getMessage());
            } else {
                this.f13163a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
            if (response.code() == 200) {
                this.f13163a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13163a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13163a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13163a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13163a.onError(response.code(), response.message());
                }
            } else {
                this.f13163a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsDirectionManager(MapplsDirections mapplsDirections) {
        this.mapplsDirections = mapplsDirections;
    }

    public static MapplsDirectionManager newInstance(MapplsDirections mapplsDirections) {
        return new MapplsDirectionManager(mapplsDirections);
    }

    public void call(@NonNull OnResponseCallback<DirectionsResponse> onResponseCallback) {
        this.mapplsDirections.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsDirections.cancel();
    }

    public DirectionsResponse execute() throws IOException {
        return this.mapplsDirections.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsDirections.executed();
    }
}
