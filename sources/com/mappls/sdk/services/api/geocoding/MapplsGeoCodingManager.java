package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes6.dex */
public class MapplsGeoCodingManager {
    private MapplsGeoCoding mapplsGeoCoding;

    /* loaded from: classes6.dex */
    public class a implements Callback<GeoCodeResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13238a;

        public a(MapplsGeoCodingManager mapplsGeoCodingManager, OnResponseCallback onResponseCallback) {
            this.f13238a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GeoCodeResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13238a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13238a.onError(1, th.getMessage());
            } else {
                this.f13238a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GeoCodeResponse> call, Response<GeoCodeResponse> response) {
            if (response.code() == 200) {
                this.f13238a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13238a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13238a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13238a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13238a.onError(response.code(), response.message());
                }
            } else {
                this.f13238a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsGeoCodingManager(MapplsGeoCoding mapplsGeoCoding) {
        this.mapplsGeoCoding = mapplsGeoCoding;
    }

    public static MapplsGeoCodingManager newInstance(MapplsGeoCoding mapplsGeoCoding) {
        return new MapplsGeoCodingManager(mapplsGeoCoding);
    }

    public void call(@NonNull OnResponseCallback<GeoCodeResponse> onResponseCallback) {
        this.mapplsGeoCoding.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsGeoCoding.cancel();
    }

    public GeoCodeResponse execute() throws IOException {
        return this.mapplsGeoCoding.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsGeoCoding.executed();
    }
}
