package com.mappls.sdk.maps.auth;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.auth.a;
import com.mappls.sdk.maps.auth.model.PublicKeyToken;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsVectorKey extends MapplsService<PublicKeyToken, b> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsVectorKey a();

        public MapplsVectorKey build() throws ServicesException {
            return a();
        }
    }

    public MapplsVectorKey() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().b(Constants.OUTPOST_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<PublicKeyToken> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<PublicKeyToken> executeCall() throws IOException {
        return super.executeCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PublicKeyToken> initializeCall() {
        return getLoginService(true).getCall();
    }
}
