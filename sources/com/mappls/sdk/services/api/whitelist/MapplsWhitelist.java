package com.mappls.sdk.services.api.whitelist;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import com.mappls.sdk.services.api.whitelist.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsWhitelist extends MapplsService<AtlasAuthToken, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(@NonNull String str);

        public abstract MapplsWhitelist build();

        public abstract Builder otp(@NonNull String str);

        public abstract Builder refLocation(@NonNull String str);

        public abstract Builder userHandle(@NonNull String str);
    }

    public MapplsWhitelist() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.OUTPOST_BASE_URL);
    }

    private Map<String, String> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("clientId", MapplsAccountManager.getInstance().getAtlasClientId());
        hashMap.put("refLocation", refLocation());
        hashMap.put("userHandle", userHandle());
        hashMap.put("passPhrase", Utils.sha256(Utils.encode(otp())));
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<AtlasAuthToken> callback) {
        enqueueCall(callback);
    }

    public Response<AtlasAuthToken> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<AtlasAuthToken> initializeCall() {
        return getService(false).getCall(createRequest());
    }

    @NonNull
    public abstract String otp();

    @NonNull
    public abstract String refLocation();

    @NonNull
    public abstract String userHandle();
}
