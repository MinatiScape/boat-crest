package com.mappls.sdk.services.api.textsearch;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.actions.SearchIntents;
import com.google.auto.value.AutoValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.api.textsearch.a;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsTextSearch extends MapplsService<AutoSuggestAtlasResponse, TextSearchService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        private Double latitude;
        private Double longitude;
        private String query;

        public abstract MapplsTextSearch autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bridge(@Nullable Boolean bool);

        public MapplsTextSearch build() throws ServicesException {
            if (this.query != null) {
                Double d = this.latitude;
                if (d != null && this.longitude != null && d.doubleValue() > 0.0d && this.longitude.doubleValue() > 0.0d) {
                    location(this.latitude + Constants.SEPARATOR_COMMA + this.longitude);
                }
                queryString(this.query);
                return autoBuild();
            }
            throw new ServicesException("You should provide query");
        }

        public abstract Builder explain(@Nullable Boolean bool);

        public abstract Builder filter(String str);

        public abstract Builder location(@Nullable String str);

        public Builder query(@NonNull String str) {
            this.query = str;
            return this;
        }

        public abstract Builder queryString(@NonNull String str);

        public Builder setLocation(@Nullable Double d, @Nullable Double d2) {
            this.latitude = d;
            this.longitude = d2;
            return this;
        }

        public abstract Builder username(String str);
    }

    public MapplsTextSearch() {
        super(TextSearchService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(com.mappls.sdk.services.utils.Constants.ATLAS_BASE_URL);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put(SearchIntents.EXTRA_QUERY, queryString());
        if (location() != null) {
            hashMap.put(FirebaseAnalytics.Param.LOCATION, location());
        }
        if (explain() != null && explain().booleanValue()) {
            hashMap.put("explain", explain());
        }
        if (bridge() != null && bridge().booleanValue()) {
            hashMap.put("bridge", bridge());
        }
        if (username() != null) {
            hashMap.put("username", username());
        }
        if (filter() != null) {
            hashMap.put("filter", filter());
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
    public Call<AutoSuggestAtlasResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract String location();

    @NonNull
    public abstract String queryString();

    @Nullable
    public abstract String username();
}
