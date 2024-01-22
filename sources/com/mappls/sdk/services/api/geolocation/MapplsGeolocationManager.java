package com.mappls.sdk.services.api.geolocation;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.geolocation.model.GeolocationResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes6.dex */
public class MapplsGeolocationManager {

    /* renamed from: a  reason: collision with root package name */
    public final MapplsGeolocation f13241a;

    /* loaded from: classes6.dex */
    public class a implements Callback<GeolocationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13242a;

        public a(MapplsGeolocationManager mapplsGeolocationManager, OnResponseCallback onResponseCallback) {
            this.f13242a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GeolocationResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13242a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13242a.onError(1, th.getMessage());
            } else {
                this.f13242a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GeolocationResponse> call, Response<GeolocationResponse> response) {
            if (response.code() == 200) {
                this.f13242a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13242a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13242a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13242a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13242a.onError(response.code(), response.message());
                }
            } else {
                this.f13242a.onError(response.code(), response.message());
            }
        }
    }

    public MapplsGeolocationManager(MapplsGeolocation mapplsGeolocation) {
        this.f13241a = mapplsGeolocation;
    }

    public static MapplsGeolocationManager newInstance(MapplsGeolocation mapplsGeolocation) {
        return new MapplsGeolocationManager(mapplsGeolocation);
    }

    public void call(@NonNull OnResponseCallback<GeolocationResponse> onResponseCallback) {
        this.f13241a.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.f13241a.cancel();
    }

    public GeolocationResponse execute() throws IOException {
        return this.f13241a.execute().body();
    }

    public boolean isExecuted() {
        return this.f13241a.executed();
    }
}