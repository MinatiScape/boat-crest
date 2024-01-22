package com.mappls.sdk.services.api.sdkconfig;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.sdkconfig.a;
import com.mappls.sdk.services.api.sdkconfig.model.SDKConfigResponse;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes7.dex */
public abstract class MapplsSDKConfig extends MapplsService<SDKConfigResponse, b> {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(@NonNull String str);

        public abstract MapplsSDKConfig build();

        public abstract Builder configUrl(@NonNull String str);
    }

    public MapplsSDKConfig() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl("https://sdkconfig.mappls.com/");
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @NonNull
    public abstract String configUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<SDKConfigResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<SDKConfigResponse> executeCall() throws IOException {
        return super.executeCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<SDKConfigResponse> initializeCall() {
        return getPlainService().getCall(configUrl());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public boolean isExecuted() {
        return super.isExecuted();
    }
}
