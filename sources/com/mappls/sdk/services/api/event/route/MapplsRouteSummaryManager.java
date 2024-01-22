package com.mappls.sdk.services.api.event.route;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes4.dex */
public class MapplsRouteSummaryManager {
    private MapplsRouteSummary mapplsRouteSummary;

    /* loaded from: classes4.dex */
    public class a implements Callback<RouteReportSummaryResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13222a;

        public a(MapplsRouteSummaryManager mapplsRouteSummaryManager, OnResponseCallback onResponseCallback) {
            this.f13222a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<RouteReportSummaryResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13222a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13222a.onError(1, th.getMessage());
            } else {
                this.f13222a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<RouteReportSummaryResponse> call, Response<RouteReportSummaryResponse> response) {
            if (response.code() == 200) {
                this.f13222a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13222a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13222a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13222a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13222a.onError(response.code(), response.message());
                }
            } else {
                this.f13222a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsRouteSummaryManager(MapplsRouteSummary mapplsRouteSummary) {
        this.mapplsRouteSummary = mapplsRouteSummary;
    }

    public static MapplsRouteSummaryManager newInstance(MapplsRouteSummary mapplsRouteSummary) {
        return new MapplsRouteSummaryManager(mapplsRouteSummary);
    }

    public void call(@NonNull OnResponseCallback<RouteReportSummaryResponse> onResponseCallback) {
        this.mapplsRouteSummary.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsRouteSummary.cancel();
    }

    public RouteReportSummaryResponse execute() throws IOException {
        return this.mapplsRouteSummary.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsRouteSummary.executed();
    }
}
