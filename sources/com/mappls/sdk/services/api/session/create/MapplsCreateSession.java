package com.mappls.sdk.services.api.session.create;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.session.create.a;
import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsCreateSession extends MapplsService<SessionResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(@NonNull String str);

        public abstract MapplsCreateSession build();

        public abstract Builder clusterId(@Nullable String str);

        public abstract Builder sessionRequest(SessionRequestModel sessionRequestModel);

        public abstract Builder sessionType(@NonNull String str);
    }

    public MapplsCreateSession() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.OUTPOST_BASE_URL).sessionType("global");
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    @Nullable
    public abstract String clusterId();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<SessionResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<SessionResponse> executeCall() throws IOException {
        return super.executeCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<SessionResponse> initializeCall() {
        return getLoginService(true).a(sessionType(), clusterId(), sessionRequest());
    }

    @NonNull
    public abstract SessionRequestModel sessionRequest();

    @NonNull
    public abstract String sessionType();
}
