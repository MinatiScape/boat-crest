package com.mappls.sdk.services.api.transit;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.transit.a;
import com.mappls.sdk.services.api.transit.model.TransitPlannerResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsTransitPlanner extends MapplsService<TransitPlannerResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder arriveBy(@Nullable Boolean bool);

        public abstract MapplsTransitPlanner autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsTransitPlanner build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder date(@Nullable String str);

        public Builder destination(@NonNull Point point) {
            return destination(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.latitude()), MapplsUtils.formatCoordinate(point.longitude())));
        }

        public abstract Builder destination(@NonNull String str);

        public abstract Builder maxTransfers(@Nullable Integer num);

        public abstract Builder mode(@NonNull String str);

        public abstract Builder optimalRoute(@Nullable Boolean bool);

        public Builder origin(@NonNull Point point) {
            return origin(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.latitude()), MapplsUtils.formatCoordinate(point.longitude())));
        }

        public abstract Builder origin(@NonNull String str);

        public abstract Builder searchWindow(@Nullable Integer num);

        public abstract Builder showIntermediateStops(@Nullable Boolean bool);

        public abstract Builder time(@Nullable String str);

        public abstract Builder walkSpeed(@Nullable Double d);
    }

    public MapplsTransitPlanner() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL).mode(TransitPlannerCriteria.MODE_TRANSIT);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("mode", mode());
        hashMap.put("locations", origin() + ";" + destination());
        if (date() != null) {
            hashMap.put("date", date());
        }
        if (time() != null) {
            hashMap.put(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, time());
        }
        if (arriveBy() != null) {
            hashMap.put("arriveBy", arriveBy());
        }
        if (optimalRoute() != null) {
            hashMap.put("optimalRoute", optimalRoute());
        }
        if (searchWindow() != null) {
            hashMap.put("searchWindow", searchWindow());
        }
        if (maxTransfers() != null) {
            hashMap.put("maxTransfers", maxTransfers());
        }
        if (showIntermediateStops() != null) {
            hashMap.put("showIntermediateStops", showIntermediateStops());
        }
        return hashMap;
    }

    @Nullable
    public abstract Boolean arriveBy();

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract String date();

    @NonNull
    public abstract String destination();

    public void enqueue(Callback<TransitPlannerResponse> callback) {
        enqueueCall(callback);
    }

    public Response<TransitPlannerResponse> execute() throws IOException {
        return executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<TransitPlannerResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract Integer maxTransfers();

    @NonNull
    public abstract String mode();

    @Nullable
    public abstract Boolean optimalRoute();

    @NonNull
    public abstract String origin();

    @Nullable
    public abstract Integer searchWindow();

    @Nullable
    public abstract Boolean showIntermediateStops();

    @Nullable
    public abstract String time();

    @Nullable
    public abstract Double walkSpeed();
}
