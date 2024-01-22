package com.mappls.sdk.services.api.autosuggest;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.actions.SearchIntents;
import com.google.auto.value.AutoValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.autosuggest.a;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsAutoSuggest extends MapplsService<AutoSuggestAtlasResponse, AutoSuggestService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        private Double latitude;
        private Double longitude;
        private String query;
        private Double zoom;

        public abstract MapplsAutoSuggest autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bridge(@Nullable Boolean bool);

        public MapplsAutoSuggest build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                if (this.query != null) {
                    Double d = this.latitude;
                    if (d != null && this.longitude != null && d.doubleValue() > 0.0d && this.longitude.doubleValue() > 0.0d) {
                        location(this.latitude + Constants.SEPARATOR_COMMA + this.longitude);
                    }
                    internalQuery(this.query);
                    Double d2 = this.zoom;
                    if (d2 != null && d2.doubleValue() >= 4.0d) {
                        internalZoom(this.zoom);
                    }
                    return autoBuild();
                }
                throw new ServicesException("You should provide query");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder explain(@Nullable Boolean bool);

        public abstract Builder filter(@Nullable String str);

        public abstract Builder hyperLocal(@Nullable Boolean bool);

        public abstract Builder internalQuery(@NonNull String str);

        public abstract Builder internalZoom(@Nullable Double d);

        public abstract Builder isPrimary(@Nullable String str);

        public abstract Builder location(@Nullable String str);

        public abstract Builder mapCentre(@Nullable String str);

        public abstract Builder pod(@Nullable String str);

        public Builder query(@NonNull String str) {
            this.query = str;
            return this;
        }

        public abstract Builder responseLang(@Nullable String str);

        public Builder setLocation(@Nullable Double d, @Nullable Double d2) {
            this.latitude = d;
            this.longitude = d2;
            return this;
        }

        public Builder setMapCentre(@NonNull Double d, @Nullable Double d2) {
            return mapCentre(d + Constants.SEPARATOR_COMMA + d2);
        }

        public abstract Builder tokenizeAddress(@Nullable Boolean bool);

        public Builder zoom(@Nullable Double d) {
            this.zoom = d;
            return this;
        }
    }

    public MapplsAutoSuggest() {
        super(AutoSuggestService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(com.mappls.sdk.services.utils.Constants.ATLAS_BASE_URL);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put(SearchIntents.EXTRA_QUERY, internalQuery());
        if (location() != null) {
            hashMap.put(FirebaseAnalytics.Param.LOCATION, location());
        }
        if (internalZoom() != null) {
            hashMap.put("zoom", internalZoom());
        }
        if (tokenizeAddress() != null && tokenizeAddress().booleanValue()) {
            hashMap.put("tokenizeAddress", tokenizeAddress());
        }
        if (bridge() != null && bridge().booleanValue()) {
            hashMap.put("bridge", bridge());
        }
        if (pod() != null) {
            hashMap.put("pod", pod());
        }
        if (filter() != null) {
            hashMap.put("filter", filter());
        }
        if (hyperLocal() != null && hyperLocal().booleanValue()) {
            hashMap.put("hyperLocal", hyperLocal());
        }
        if (mapCentre() != null) {
            hashMap.put("mapCentre", mapCentre());
        }
        if (isPrimary() != null) {
            hashMap.put("isPrimary", isPrimary());
        }
        if (responseLang() != null) {
            hashMap.put("responseLang", responseLang());
        }
        if (explain() != null && explain().booleanValue()) {
            hashMap.put("explain", explain());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract Boolean bridge();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<AutoSuggestAtlasResponse> callback) {
        enqueueCall(callback);
    }

    public Response<AutoSuggestAtlasResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract Boolean explain();

    @Nullable
    public abstract String filter();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    @Nullable
    public abstract Boolean hyperLocal();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<AutoSuggestAtlasResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @NonNull
    public abstract String internalQuery();

    @Nullable
    public abstract Double internalZoom();

    @Nullable
    public abstract String isPrimary();

    @Nullable
    public abstract String location();

    @Nullable
    public abstract String mapCentre();

    @Nullable
    public abstract String pod();

    @Nullable
    public abstract String responseLang();

    @Nullable
    public abstract Boolean tokenizeAddress();
}
