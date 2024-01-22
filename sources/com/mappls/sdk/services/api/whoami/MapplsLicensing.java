package com.mappls.sdk.services.api.whoami;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.whoami.a;
import com.mappls.sdk.services.api.whoami.model.LicensingResponse;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsLicensing extends MapplsService<LicensingResponse, b> {

    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract MapplsLicensing a();

        public MapplsLicensing build() {
            return a();
        }

        public abstract Builder deviceId(@Nullable String str);
    }

    public MapplsLicensing() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().b(Constants.ANCHOR_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    @Nullable
    public abstract String deviceId();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<LicensingResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<LicensingResponse> executeCall() throws IOException {
        return super.executeCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<LicensingResponse> initializeCall() {
        return getService(true).getCall(deviceId());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public boolean isExecuted() {
        return super.isExecuted();
    }
}
