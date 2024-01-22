package com.mappls.sdk.services.api.placedetail;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.placedetail.model.PlaceDetailResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes6.dex */
public class MapplsPlaceDetailManager {
    private final MapplsPlaceDetail mapplsPlaceDetail;

    /* loaded from: classes6.dex */
    public class a implements Callback<PlaceDetailResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13251a;

        public a(MapplsPlaceDetailManager mapplsPlaceDetailManager, OnResponseCallback onResponseCallback) {
            this.f13251a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PlaceDetailResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13251a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13251a.onError(1, th.getMessage());
            } else {
                this.f13251a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PlaceDetailResponse> call, Response<PlaceDetailResponse> response) {
            if (response.code() == 200) {
                this.f13251a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13251a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13251a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13251a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13251a.onError(response.code(), response.message());
                }
            } else {
                this.f13251a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsPlaceDetailManager(MapplsPlaceDetail mapplsPlaceDetail) {
        this.mapplsPlaceDetail = mapplsPlaceDetail;
    }

    public static MapplsPlaceDetailManager newInstance(MapplsPlaceDetail mapplsPlaceDetail) {
        return new MapplsPlaceDetailManager(mapplsPlaceDetail);
    }

    public void call(@NonNull OnResponseCallback<PlaceDetailResponse> onResponseCallback) {
        this.mapplsPlaceDetail.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsPlaceDetail.cancel();
    }

    public PlaceDetailResponse execute() throws IOException {
        return this.mapplsPlaceDetail.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsPlaceDetail.executed();
    }
}
