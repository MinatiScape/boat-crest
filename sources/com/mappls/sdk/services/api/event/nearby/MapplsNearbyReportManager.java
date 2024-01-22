package com.mappls.sdk.services.api.event.nearby;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.event.nearby.model.NearbyReportResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes3.dex */
public class MapplsNearbyReportManager {
    private final MapplsNearbyReport mapplsNearbyReport;

    /* loaded from: classes3.dex */
    public class a implements Callback<NearbyReportResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13219a;

        public a(MapplsNearbyReportManager mapplsNearbyReportManager, OnResponseCallback onResponseCallback) {
            this.f13219a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<NearbyReportResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13219a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13219a.onError(1, th.getMessage());
            } else {
                this.f13219a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<NearbyReportResponse> call, Response<NearbyReportResponse> response) {
            if (response.code() == 200) {
                this.f13219a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13219a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13219a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13219a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13219a.onError(response.code(), response.message());
                }
            } else {
                this.f13219a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsNearbyReportManager(MapplsNearbyReport mapplsNearbyReport) {
        this.mapplsNearbyReport = mapplsNearbyReport;
    }

    public static MapplsNearbyReportManager newInstance(MapplsNearbyReport mapplsNearbyReport) {
        return new MapplsNearbyReportManager(mapplsNearbyReport);
    }

    public void call(@NonNull OnResponseCallback<NearbyReportResponse> onResponseCallback) {
        this.mapplsNearbyReport.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsNearbyReport.cancel();
    }

    public NearbyReportResponse execute() throws IOException {
        return this.mapplsNearbyReport.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsNearbyReport.executed();
    }
}
