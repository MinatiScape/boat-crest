package com.mappls.sdk.services.api.alongroute;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsPOIAlongRouteManager {
    private MapplsPOIAlongRoute mapplsPOIAlongRoute;

    /* loaded from: classes11.dex */
    public class a implements Callback<POIAlongRouteResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13150a;

        public a(MapplsPOIAlongRouteManager mapplsPOIAlongRouteManager, OnResponseCallback onResponseCallback) {
            this.f13150a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<POIAlongRouteResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13150a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13150a.onError(1, th.getMessage());
            } else {
                this.f13150a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<POIAlongRouteResponse> call, Response<POIAlongRouteResponse> response) {
            if (response.code() == 200) {
                this.f13150a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13150a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13150a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13150a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13150a.onError(response.code(), response.message());
                }
            } else {
                this.f13150a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsPOIAlongRouteManager(MapplsPOIAlongRoute mapplsPOIAlongRoute) {
        this.mapplsPOIAlongRoute = mapplsPOIAlongRoute;
    }

    public static MapplsPOIAlongRouteManager newInstance(MapplsPOIAlongRoute mapplsPOIAlongRoute) {
        return new MapplsPOIAlongRouteManager(mapplsPOIAlongRoute);
    }

    public void call(@NonNull OnResponseCallback<POIAlongRouteResponse> onResponseCallback) {
        this.mapplsPOIAlongRoute.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsPOIAlongRoute.cancel();
    }

    public POIAlongRouteResponse execute() throws IOException {
        return this.mapplsPOIAlongRoute.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsPOIAlongRoute.executed();
    }
}
