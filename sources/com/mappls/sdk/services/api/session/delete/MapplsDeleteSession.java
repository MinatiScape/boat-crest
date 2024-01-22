package com.mappls.sdk.services.api.session.delete;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import com.mappls.sdk.services.api.session.delete.a;
import com.mappls.sdk.services.utils.Constants;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsDeleteSession extends MapplsService<SessionResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(String str);

        public abstract MapplsDeleteSession build();

        public abstract Builder hyperlink(@NonNull String str);
    }

    public MapplsDeleteSession() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void cancelCall() {
        super.cancelCall();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<SessionResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Response<SessionResponse> executeCall() throws IOException {
        return super.executeCall();
    }

    @NonNull
    public abstract String hyperlink();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<SessionResponse> initializeCall() {
        return getLoginService(true).getCall(hyperlink());
    }
}
