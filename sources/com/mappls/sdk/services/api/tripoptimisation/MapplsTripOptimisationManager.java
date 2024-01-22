package com.mappls.sdk.services.api.tripoptimisation;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes8.dex */
public class MapplsTripOptimisationManager {
    private final MapplsTripOptimisation mapplsTripOptimisation;

    /* loaded from: classes8.dex */
    public class a implements Callback<TripOptimisationResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13293a;

        public a(MapplsTripOptimisationManager mapplsTripOptimisationManager, OnResponseCallback onResponseCallback) {
            this.f13293a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<TripOptimisationResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13293a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13293a.onError(1, th.getMessage());
            } else {
                this.f13293a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<TripOptimisationResponse> call, Response<TripOptimisationResponse> response) {
            if (response.code() == 200) {
                this.f13293a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13293a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13293a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13293a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13293a.onError(response.code(), response.message());
                }
            } else {
                this.f13293a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsTripOptimisationManager(MapplsTripOptimisation mapplsTripOptimisation) {
        this.mapplsTripOptimisation = mapplsTripOptimisation;
    }

    public static MapplsTripOptimisationManager newInstance(MapplsTripOptimisation mapplsTripOptimisation) {
        return new MapplsTripOptimisationManager(mapplsTripOptimisation);
    }

    public void call(OnResponseCallback<TripOptimisationResponse> onResponseCallback) {
        this.mapplsTripOptimisation.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsTripOptimisation.cancel();
    }

    public TripOptimisationResponse execute() throws IOException {
        return this.mapplsTripOptimisation.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsTripOptimisation.executed();
    }
}
