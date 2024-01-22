package com.mappls.sdk.services.api.generateotp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.generateotp.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsGenerateOTP extends MapplsService<Void, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract MapplsGenerateOTP autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsGenerateOTP build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public abstract Builder userHandle(@NonNull String str);
    }

    public MapplsGenerateOTP() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ANCHOR_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<Void> callback) {
        enqueueCall(callback);
    }

    public Response<Void> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<Void> initializeCall() {
        return getService(true).getCall(userHandle());
    }

    @NonNull
    public abstract String userHandle();
}
