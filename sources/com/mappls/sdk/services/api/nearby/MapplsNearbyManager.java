package com.mappls.sdk.services.api.nearby;

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
public class MapplsNearbyManager {
    private MapplsNearby mapplsNearby;

    /* loaded from: classes6.dex */
    public class a implements Callback<NearbyAtlasResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13248a;

        public a(MapplsNearbyManager mapplsNearbyManager, OnResponseCallback onResponseCallback) {
            this.f13248a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<NearbyAtlasResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13248a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13248a.onError(1, th.getMessage());
            } else {
                this.f13248a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<NearbyAtlasResponse> call, Response<NearbyAtlasResponse> response) {
            if (response.code() == 200) {
                this.f13248a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13248a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13248a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13248a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13248a.onError(response.code(), response.message());
                }
            } else {
                this.f13248a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsNearbyManager(MapplsNearby mapplsNearby) {
        this.mapplsNearby = mapplsNearby;
    }

    public static MapplsNearbyManager newInstance(MapplsNearby mapplsNearby) {
        return new MapplsNearbyManager(mapplsNearby);
    }

    public void call(@NonNull OnResponseCallback<NearbyAtlasResponse> onResponseCallback) {
        this.mapplsNearby.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsNearby.cancel();
    }

    public NearbyAtlasResponse execute() throws IOException {
        return this.mapplsNearby.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsNearby.executed();
    }
}
