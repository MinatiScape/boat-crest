package com.mappls.sdk.services.api.fuleCost;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.fuleCost.a;
import com.mappls.sdk.services.api.fuleCost.models.FuelCostResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsFuelCost extends MapplsService<FuelCostResponse, FuelCostService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract MapplsFuelCost autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsFuelCost build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder latitude(@NonNull Double d);

        public Builder location(Double d, Double d2) {
            return latitude(d).longitude(d2);
        }

        public abstract Builder longitude(@NonNull Double d);
    }

    public MapplsFuelCost() {
        super(FuelCostService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.EXPLORE_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<FuelCostResponse> callback) {
        enqueueCall(callback);
    }

    public Response<FuelCostResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<FuelCostResponse> initializeCall() {
        return getLoginService(true).getCall(latitude(), longitude());
    }

    @NonNull
    public abstract Double latitude();

    @NonNull
    public abstract Double longitude();
}
