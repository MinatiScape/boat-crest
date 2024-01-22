package com.mappls.sdk.services.api.hateaosnearby;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes6.dex */
public class MapplsHateosNearbyManager {
    private MapplsHateosNearby mapplsHateosNearby;

    /* loaded from: classes6.dex */
    public class a implements Callback<NearbyAtlasResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13245a;

        public a(MapplsHateosNearbyManager mapplsHateosNearbyManager, OnResponseCallback onResponseCallback) {
            this.f13245a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<NearbyAtlasResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13245a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13245a.onError(1, th.getMessage());
            } else {
                this.f13245a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<NearbyAtlasResponse> call, Response<NearbyAtlasResponse> response) {
            if (response.code() == 200) {
                this.f13245a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13245a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13245a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13245a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13245a.onError(response.code(), response.message());
                }
            } else {
                this.f13245a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsHateosNearbyManager(MapplsHateosNearby mapplsHateosNearby) {
        this.mapplsHateosNearby = mapplsHateosNearby;
    }

    public static MapplsHateosNearbyManager newInstance(MapplsHateosNearby mapplsHateosNearby) {
        return new MapplsHateosNearbyManager(mapplsHateosNearby);
    }

    public void call(@NonNull OnResponseCallback<NearbyAtlasResponse> onResponseCallback) {
        this.mapplsHateosNearby.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsHateosNearby.cancel();
    }

    public NearbyAtlasResponse execute() throws IOException {
        return this.mapplsHateosNearby.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsHateosNearby.executed();
    }
}
