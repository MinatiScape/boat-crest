package com.mappls.sdk.services.api.weather;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.weather.a;
import com.mappls.sdk.services.api.weather.model.WeatherResponse;
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
/* loaded from: classes8.dex */
public abstract class MapplsWeather extends MapplsService<WeatherResponse, WeatherService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract MapplsWeather autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsWeather build() {
            if (!MapplsUtils.isEmpty(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid Rest API Key.");
        }

        public abstract Builder latitude(@NonNull Double d);

        public Builder location(@NonNull Double d, @NonNull Double d2) {
            return latitude(d).longitude(d2);
        }

        public abstract Builder longitude(@NonNull Double d);

        public abstract Builder size(@NonNull String str);

        public abstract Builder tempUnit(@Nullable String str);

        public abstract Builder theme(@NonNull String str);

        public abstract Builder unit(@Nullable Integer num);

        public abstract Builder unitType(@Nullable String str);
    }

    public MapplsWeather() {
        super(WeatherService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.EXPLORE_BASE_URL).theme("light").size("36px");
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", latitude());
        hashMap.put("longitude", longitude());
        hashMap.put("density", "drawable-" + MapplsUtils.getDensityName());
        hashMap.put("platform", com.clevertap.android.sdk.Constants.KEY_ANDROID);
        hashMap.put("theme", theme());
        hashMap.put("size", size());
        if (tempUnit() != null) {
            hashMap.put("tempUnit", tempUnit());
        }
        if (unitType() != null) {
            hashMap.put("unitType", unitType());
        }
        if (unit() != null) {
            hashMap.put("unit", unit());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    public void enqueue(Callback<WeatherResponse> callback) {
        super.enqueueCall(callback);
    }

    public Response<WeatherResponse> execute() throws IOException {
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
    public Call<WeatherResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @NonNull
    public abstract Double latitude();

    @NonNull
    public abstract Double longitude();

    @NonNull
    public abstract String size();

    @Nullable
    public abstract String tempUnit();

    @NonNull
    public abstract String theme();

    @Nullable
    public abstract Integer unit();

    @Nullable
    public abstract String unitType();
}
