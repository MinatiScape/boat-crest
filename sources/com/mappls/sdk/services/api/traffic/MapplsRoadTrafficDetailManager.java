package com.mappls.sdk.services.api.traffic;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.traffic.model.TrafficRoadDetailResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes8.dex */
public class MapplsRoadTrafficDetailManager {
    private MapplsRoadTrafficDetail mapplsRoadTrafficDetail;

    /* loaded from: classes8.dex */
    public class a implements Callback<TrafficRoadDetailResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13286a;

        public a(MapplsRoadTrafficDetailManager mapplsRoadTrafficDetailManager, OnResponseCallback onResponseCallback) {
            this.f13286a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<TrafficRoadDetailResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13286a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13286a.onError(1, th.getMessage());
            } else {
                this.f13286a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<TrafficRoadDetailResponse> call, Response<TrafficRoadDetailResponse> response) {
            if (response.code() == 200) {
                this.f13286a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13286a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13286a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13286a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13286a.onError(response.code(), response.message());
                }
            } else {
                this.f13286a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsRoadTrafficDetailManager(MapplsRoadTrafficDetail mapplsRoadTrafficDetail) {
        this.mapplsRoadTrafficDetail = mapplsRoadTrafficDetail;
    }

    public static MapplsRoadTrafficDetailManager newInstance(MapplsRoadTrafficDetail mapplsRoadTrafficDetail) {
        return new MapplsRoadTrafficDetailManager(mapplsRoadTrafficDetail);
    }

    public void call(@NonNull OnResponseCallback<TrafficRoadDetailResponse> onResponseCallback) {
        this.mapplsRoadTrafficDetail.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsRoadTrafficDetail.cancel();
    }

    public TrafficRoadDetailResponse execute() throws IOException {
        return this.mapplsRoadTrafficDetail.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsRoadTrafficDetail.executed();
    }
}
