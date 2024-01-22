package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.geocoding.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsGeoCoding extends MapplsService<GeoCodeResponse, GeoCodingService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        private String address;

        public abstract Builder address(@NonNull String str);

        public abstract MapplsGeoCoding autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bias(@Nullable Integer num);

        public abstract Builder bound(@Nullable String str);

        public MapplsGeoCoding build() throws ServicesException {
            if (!MapplsUtils.isEmpty(this.address)) {
                address(this.address);
                return autoBuild();
            }
            throw new ServicesException("Please pass valid address");
        }

        public abstract Builder clientAppName(@Nullable String str);

        public abstract Builder itemCount(@Nullable Integer num);

        public abstract Builder podFilter(@Nullable String str);

        public abstract Builder scores(@Nullable Boolean bool);

        public Builder setAddress(String str) {
            this.address = str;
            return this;
        }
    }

    public MapplsGeoCoding() {
        super(GeoCodingService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ATLAS_BASE_URL);
    }

    @NonNull
    public abstract String address();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract Integer bias();

    @Nullable
    public abstract String bound();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract String clientAppName();

    public void enqueue(Callback<GeoCodeResponse> callback) {
        enqueueCall(callback);
    }

    public Response<GeoCodeResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GeoCodeResponse.class, new GeoCodeJsonDeserializer()).create();
        return gsonBuilder;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<GeoCodeResponse> initializeCall() {
        HashMap hashMap = new HashMap();
        hashMap.put("address", address());
        if (itemCount() != null) {
            hashMap.put("itemCount", itemCount());
        }
        if (bias() != null) {
            hashMap.put("bias", bias());
        }
        if (podFilter() != null) {
            hashMap.put("podFilter", podFilter());
        }
        if (bound() != null) {
            hashMap.put("bound", bound());
        }
        if (scores() != null && scores().booleanValue()) {
            hashMap.put("scores", scores());
        }
        return getLoginService(true).getCall(hashMap);
    }

    @Nullable
    public abstract Integer itemCount();

    @Nullable
    public abstract String podFilter();

    @Nullable
    public abstract Boolean scores();
}
