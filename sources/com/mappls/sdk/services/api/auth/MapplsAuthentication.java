package com.mappls.sdk.services.api.auth;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.auth.b;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsAuthentication extends MapplsService<AtlasAuthToken, a> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsAuthentication autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsAuthentication build() throws ServicesException {
            return autoBuild();
        }

        public abstract Builder grantType(@NonNull String str);

        public abstract Builder refreshToken(@Nullable String str);
    }

    public MapplsAuthentication() {
        super(a.class);
    }

    public static Builder builder() {
        return new b.C0665b().grantType("client_credentials").baseUrl(Constants.OUTPOST_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    public void enqueue(Callback<AtlasAuthToken> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<AtlasAuthToken> executeCall() throws IOException {
        return super.executeCall();
    }

    @NonNull
    public abstract String grantType();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<AtlasAuthToken> initializeCall() {
        return getService(false).a(MapplsAccountManager.getInstance().getAtlasClientId(), MapplsAccountManager.getInstance().getAtlasClientSecret(), refreshToken(), grantType());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public boolean isExecuted() {
        return super.isExecuted();
    }

    @Nullable
    public abstract String refreshToken();
}
