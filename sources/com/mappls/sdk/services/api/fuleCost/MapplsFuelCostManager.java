package com.mappls.sdk.services.api.fuleCost;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.fuleCost.models.FuelCostResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes6.dex */
public class MapplsFuelCostManager {
    private final MapplsFuelCost mapplsFuelCost;

    /* loaded from: classes6.dex */
    public class a implements Callback<FuelCostResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13232a;

        public a(MapplsFuelCostManager mapplsFuelCostManager, OnResponseCallback onResponseCallback) {
            this.f13232a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<FuelCostResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13232a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13232a.onError(1, th.getMessage());
            } else {
                this.f13232a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<FuelCostResponse> call, Response<FuelCostResponse> response) {
            if (response.code() == 200) {
                this.f13232a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13232a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13232a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13232a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13232a.onError(response.code(), response.message());
                }
            } else {
                this.f13232a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsFuelCostManager(MapplsFuelCost mapplsFuelCost) {
        this.mapplsFuelCost = mapplsFuelCost;
    }

    public static MapplsFuelCostManager newInstance(MapplsFuelCost mapplsFuelCost) {
        return new MapplsFuelCostManager(mapplsFuelCost);
    }

    public void call(@NonNull OnResponseCallback<FuelCostResponse> onResponseCallback) {
        this.mapplsFuelCost.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsFuelCost.cancel();
    }

    public FuelCostResponse execute() throws IOException {
        return this.mapplsFuelCost.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsFuelCost.executed();
    }
}
