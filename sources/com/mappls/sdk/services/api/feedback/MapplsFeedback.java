package com.mappls.sdk.services.api.feedback;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.feedback.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsFeedback extends MapplsService<Void, FeedbackService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract Builder appVersion(@NonNull String str);

        public abstract MapplsFeedback autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsFeedback build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public abstract Builder index(@NonNull Integer num);

        public abstract Builder latitude(@Nullable Double d);

        public abstract Builder locationName(@NonNull String str);

        public abstract Builder longitude(@Nullable Double d);

        public abstract Builder mapplsPin(@Nullable String str);

        public abstract Builder typedKeyword(@NonNull String str);

        public abstract Builder userName(@NonNull String str);
    }

    public MapplsFeedback() {
        super(FeedbackService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ATLAS_BASE_URL);
    }

    private HashMap<String, String> createRequest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("typedKeyword", typedKeyword());
        hashMap.put("selectedEloc", mapplsPin());
        hashMap.put("selectedLocationName", locationName());
        hashMap.put("apiVersion", "versionless");
        hashMap.put("selectedIndex", String.valueOf(index()));
        hashMap.put("username", userName());
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, appVersion());
        if (latitude() != null) {
            hashMap.put("latitude", String.valueOf(latitude()));
        }
        if (longitude() != null) {
            hashMap.put("longitude", String.valueOf(longitude()));
        }
        return hashMap;
    }

    @NonNull
    public abstract String appVersion();

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

    @NonNull
    public abstract Integer index();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<Void> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract Double latitude();

    @NonNull
    public abstract String locationName();

    @Nullable
    public abstract Double longitude();

    @Nullable
    public abstract String mapplsPin();

    @NonNull
    public abstract String typedKeyword();

    @NonNull
    public abstract String userName();
}
