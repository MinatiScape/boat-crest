package com.mappls.sdk.services.api.hateaosnearby;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.hateaosnearby.a;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsHateosNearby extends MapplsService<NearbyAtlasResponse, NearbyHateosService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract MapplsHateosNearby autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsHateosNearby build() throws ServicesException {
            return autoBuild();
        }

        public abstract Builder hyperlink(@NonNull String str);
    }

    public MapplsHateosNearby() {
        super(NearbyHateosService.class);
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

    public void enqueue(Callback<NearbyAtlasResponse> callback) {
        enqueueCall(callback);
    }

    public Response<NearbyAtlasResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    public abstract String hyperlink();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<NearbyAtlasResponse> initializeCall() {
        return getLoginService(true).getCall(hyperlink());
    }
}
