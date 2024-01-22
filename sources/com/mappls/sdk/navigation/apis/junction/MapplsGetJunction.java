package com.mappls.sdk.navigation.apis.junction;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.navigation.apis.junction.a;
import com.mappls.sdk.navigation.model.JunctionApiResponse;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsGetJunction extends MapplsService<JunctionApiResponse, com.mappls.sdk.navigation.apis.web.a> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsGetJunction autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsGetJunction build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder junctionViewMode(@NonNull String str);

        public abstract Builder routeId(@NonNull String str);

        public abstract Builder routeIdx(@NonNull Integer num);

        public abstract Builder size(@NonNull String str);
    }

    public MapplsGetJunction() {
        super(com.mappls.sdk.navigation.apis.web.a.class);
    }

    public static Builder builder() {
        return new a.C0638a().baseUrl(Constants.EXPLORE_BASE_URL).junctionViewMode(WeatherCriteria.UNIT_TYPE_DAY).size("280X200");
    }

    @NonNull
    public abstract String a();

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<JunctionApiResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<JunctionApiResponse> executeCall() {
        return super.executeCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<JunctionApiResponse> initializeCall() {
        return getLoginService(true).a(routeId(), a(), routeIdx(), size());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public boolean isExecuted() {
        return super.isExecuted();
    }

    @NonNull
    public abstract String routeId();

    @NonNull
    public abstract Integer routeIdx();

    @NonNull
    public abstract String size();
}
