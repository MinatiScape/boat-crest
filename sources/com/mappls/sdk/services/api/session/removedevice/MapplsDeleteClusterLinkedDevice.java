package com.mappls.sdk.services.api.session.removedevice;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.session.removedevice.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsDeleteClusterLinkedDevice extends MapplsService<Void, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract MapplsDeleteClusterLinkedDevice autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsDeleteClusterLinkedDevice build() throws ServicesException {
            if (!MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId()) && !MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                throw new ServicesException("Using Mappls Services requires setting a valid client_id and client_secret.");
            }
            return autoBuild();
        }

        public abstract Builder clusterId(@NonNull String str);

        public abstract Builder linkedDevice(@NonNull String str);

        public abstract Builder sessionType(@NonNull String str);
    }

    public MapplsDeleteClusterLinkedDevice() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.OUTPOST_BASE_URL).sessionType("global");
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract String clusterId();

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
        return getService(true).a(sessionType(), clusterId(), linkedDevice());
    }

    @NonNull
    public abstract String linkedDevice();

    @NonNull
    public abstract String sessionType();
}
