package com.mappls.sdk.services.api.reversegeocode;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.PlaceResponse;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.reversegeocode.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes7.dex */
public abstract class MapplsReverseGeoCode extends MapplsService<PlaceResponse, ReverseGeoCodeService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        private Double latitude;
        private Double longitude;

        public abstract MapplsReverseGeoCode autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsReverseGeoCode build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                latitude(this.latitude.doubleValue());
                longitude(this.longitude.doubleValue());
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid Client ID API key.");
        }

        public abstract Builder lang(@Nullable String str);

        public abstract Builder latitude(@NonNull double d);

        public abstract Builder longitude(@NonNull double d);

        public Builder setLocation(double d, double d2) {
            this.latitude = Double.valueOf(d);
            this.longitude = Double.valueOf(d2);
            return this;
        }
    }

    public MapplsReverseGeoCode() {
        super(ReverseGeoCodeService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<PlaceResponse> callback) {
        enqueueCall(callback);
    }

    public Response<PlaceResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PlaceResponse> initializeCall() {
        return getLoginService(true).getCall(latitude(), longitude(), lang());
    }

    @Nullable
    public abstract String lang();

    @NonNull
    public abstract double latitude();

    @NonNull
    public abstract double longitude();
}
