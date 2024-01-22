package com.mappls.sdk.services.api.reversegeocode;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.PlaceResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes7.dex */
public class MapplsReverseGeoCodeManager {
    private final MapplsReverseGeoCode mapplsReverseGeoCode;

    /* loaded from: classes7.dex */
    public class a implements Callback<PlaceResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13263a;

        public a(MapplsReverseGeoCodeManager mapplsReverseGeoCodeManager, OnResponseCallback onResponseCallback) {
            this.f13263a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PlaceResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13263a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13263a.onError(1, th.getMessage());
            } else {
                this.f13263a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
            if (response.code() == 200) {
                this.f13263a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13263a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13263a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13263a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13263a.onError(response.code(), response.message());
                }
            } else {
                this.f13263a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsReverseGeoCodeManager(MapplsReverseGeoCode mapplsReverseGeoCode) {
        this.mapplsReverseGeoCode = mapplsReverseGeoCode;
    }

    public static MapplsReverseGeoCodeManager newInstance(MapplsReverseGeoCode mapplsReverseGeoCode) {
        return new MapplsReverseGeoCodeManager(mapplsReverseGeoCode);
    }

    public void call(@NonNull OnResponseCallback<PlaceResponse> onResponseCallback) {
        this.mapplsReverseGeoCode.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsReverseGeoCode.cancel();
    }

    public PlaceResponse execute() throws IOException {
        return this.mapplsReverseGeoCode.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsReverseGeoCode.executed();
    }
}
