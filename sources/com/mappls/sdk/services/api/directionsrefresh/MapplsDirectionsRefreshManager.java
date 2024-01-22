package com.mappls.sdk.services.api.directionsrefresh;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsDirectionsRefreshManager {
    private MapplsDirectionsRefresh mapplsDirectionsRefresh;

    /* loaded from: classes11.dex */
    public class a implements Callback<DirectionsRoute> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13204a;

        public a(MapplsDirectionsRefreshManager mapplsDirectionsRefreshManager, OnResponseCallback onResponseCallback) {
            this.f13204a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DirectionsRoute> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13204a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13204a.onError(1, th.getMessage());
            } else {
                this.f13204a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DirectionsRoute> call, Response<DirectionsRoute> response) {
            if (response.code() == 200) {
                this.f13204a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13204a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13204a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13204a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13204a.onError(response.code(), response.message());
                }
            } else {
                this.f13204a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsDirectionsRefreshManager(MapplsDirectionsRefresh mapplsDirectionsRefresh) {
        this.mapplsDirectionsRefresh = mapplsDirectionsRefresh;
    }

    public static MapplsDirectionsRefreshManager newInstance(MapplsDirectionsRefresh mapplsDirectionsRefresh) {
        return new MapplsDirectionsRefreshManager(mapplsDirectionsRefresh);
    }

    public void call(@NonNull OnResponseCallback<DirectionsRoute> onResponseCallback) {
        this.mapplsDirectionsRefresh.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsDirectionsRefresh.cancel();
    }

    public DirectionsRoute execute() throws IOException {
        return this.mapplsDirectionsRefresh.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsDirectionsRefresh.executed();
    }
}
