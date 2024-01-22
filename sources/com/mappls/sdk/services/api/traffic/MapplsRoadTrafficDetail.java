package com.mappls.sdk.services.api.traffic;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.traffic.a;
import com.mappls.sdk.services.api.traffic.model.TrafficRoadDetailResponse;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsRoadTrafficDetail extends MapplsService<TrafficRoadDetailResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(@NonNull String str);

        public abstract MapplsRoadTrafficDetail build();

        public abstract Builder latitude(@NonNull Double d);

        public abstract Builder longitude(@NonNull Double d);

        public abstract Builder radius(@Nullable Long l);
    }

    public MapplsRoadTrafficDetail() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.TRAFFIC_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<TrafficRoadDetailResponse> callback) {
        enqueueCall(callback);
    }

    public Response<TrafficRoadDetailResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<TrafficRoadDetailResponse> initializeCall() {
        return getLoginService(true).a(latitude(), longitude(), radius());
    }

    @NonNull
    public abstract Double latitude();

    @NonNull
    public abstract Double longitude();

    @Nullable
    public abstract Long radius();
}
