package com.mappls.sdk.services.api.costestimation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.costestimation.a;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsCostEstimation extends MapplsService<CostEstimationResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsCostEstimation autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsCostEstimation build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder distance(@Nullable Double d);

        public abstract Builder fuelEfficiency(@Nullable Integer num);

        public abstract Builder fuelEfficiencyUnit(@Nullable String str);

        public abstract Builder fuelPrice(@Nullable Double d);

        public abstract Builder isTollEnabled(@Nullable Boolean bool);

        public abstract Builder latitude(@Nullable Double d);

        public abstract Builder longitude(@Nullable Double d);

        public abstract Builder routeId(@Nullable String str);

        public abstract Builder routeIndex(@Nullable Integer num);

        public abstract Builder vehicleFuelType(@Nullable String str);

        public abstract Builder vehicleType(@Nullable String str);
    }

    public MapplsCostEstimation() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.EXPLORE_BASE_URL);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        if (routeId() != null) {
            hashMap.put("routeId", routeId());
        }
        if (routeIndex() != null) {
            hashMap.put("routeIdx", routeIndex());
        }
        if (vehicleType() != null) {
            hashMap.put("vehicleType", vehicleType());
        }
        if (isTollEnabled() != null) {
            hashMap.put("isTollEnabled", isTollEnabled());
        }
        if (vehicleFuelType() != null) {
            hashMap.put("vehicleFuelType", vehicleFuelType());
        }
        if (fuelEfficiency() != null) {
            hashMap.put("fuelEfficiency", fuelEfficiency());
        }
        if (fuelEfficiencyUnit() != null) {
            hashMap.put("fuelEfficiencyUnit", fuelEfficiencyUnit());
        }
        if (fuelPrice() != null) {
            hashMap.put("fuelPrice", fuelPrice());
        }
        if (distance() != null) {
            hashMap.put("distance", distance());
        }
        if (latitude() != null) {
            hashMap.put("latitude", latitude());
        }
        if (longitude() != null) {
            hashMap.put("longitude", longitude());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract Double distance();

    public void enqueue(Callback<CostEstimationResponse> callback) {
        enqueueCall(callback);
    }

    public Response<CostEstimationResponse> execute() throws IOException {
        return executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract Integer fuelEfficiency();

    @Nullable
    public abstract String fuelEfficiencyUnit();

    @Nullable
    public abstract Double fuelPrice();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<CostEstimationResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract Boolean isTollEnabled();

    @Nullable
    public abstract Double latitude();

    @Nullable
    public abstract Double longitude();

    @Nullable
    public abstract String routeId();

    @Nullable
    public abstract Integer routeIndex();

    @Nullable
    public abstract String vehicleFuelType();

    @Nullable
    public abstract String vehicleType();
}
