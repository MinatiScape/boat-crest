package com.mappls.sdk.services.api.nearby;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.nearby.a;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsNearby extends MapplsService<NearbyAtlasResponse, NearbyService> {
    public MapplsNearby() {
        super(NearbyService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ATLAS_BASE_URL);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("keywords", keywordString());
        hashMap.put("refLocation", location());
        if (page() != null) {
            hashMap.put("page", page());
        }
        if (explain() != null && explain().booleanValue()) {
            hashMap.put("explain", explain());
        }
        if (richData() != null && richData().booleanValue()) {
            hashMap.put("richData", richData());
        }
        if (sortBy() != null) {
            hashMap.put("sortBy", sortBy());
        }
        if (filter() != null) {
            hashMap.put("filter", filter());
        }
        if (searchBy() != null) {
            hashMap.put("searchBy", searchBy());
        }
        if (radius() != null) {
            hashMap.put(com.clevertap.android.sdk.Constants.KEY_RADIUS, radius());
        }
        if (bounds() != null) {
            hashMap.put("bounds", bounds());
        }
        if (pod() != null) {
            hashMap.put("pod", pod());
        }
        if (userName() != null) {
            hashMap.put("username", userName());
        }
        if (ignoreAutoExpand() != null) {
            hashMap.put("ignoreAutoExpand", ignoreAutoExpand());
        }
        if (internalIncludes() != null) {
            hashMap.put("includes", internalIncludes());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract String bounds();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<NearbyAtlasResponse> callback) {
        enqueueCall(callback);
    }

    public Response<NearbyAtlasResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract Boolean explain();

    @Nullable
    public abstract String filter();

    @Nullable
    public abstract Boolean ignoreAutoExpand();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<NearbyAtlasResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract String internalIncludes();

    @NonNull
    public abstract String keywordString();

    @NonNull
    public abstract String location();

    @Nullable
    public abstract Integer page();

    @Nullable
    public abstract String pod();

    @Nullable
    public abstract Integer radius();

    @Nullable
    public abstract Boolean richData();

    @Nullable
    public abstract String searchBy();

    @Nullable
    public abstract String sortBy();

    @Nullable
    public abstract String userName();

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        private String keyword;
        private String location;

        public abstract MapplsNearby autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bounds(String str);

        public MapplsNearby build() throws ServicesException {
            if (this.keyword != null) {
                String str = this.location;
                if (str != null) {
                    location(str);
                }
                keywordString(this.keyword);
                return autoBuild();
            }
            throw new ServicesException("You should provide keyword");
        }

        public abstract Builder explain(Boolean bool);

        public abstract Builder filter(String str);

        public abstract Builder ignoreAutoExpand(Boolean bool);

        public Builder includes(String... strArr) {
            return internalIncludes(MapplsUtils.join(com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, strArr));
        }

        public abstract Builder internalIncludes(String str);

        public Builder keyword(@NonNull String str) {
            this.keyword = str;
            return this;
        }

        public abstract Builder keywordString(String str);

        public abstract Builder location(@NonNull String str);

        public abstract Builder page(Integer num);

        public abstract Builder pod(String str);

        public abstract Builder radius(Integer num);

        public abstract Builder richData(Boolean bool);

        public abstract Builder searchBy(String str);

        public Builder setLocation(@Nullable Double d, @Nullable Double d2) {
            if (d != null && d2 != null && d.doubleValue() > 0.0d && d2.doubleValue() > 0.0d) {
                this.location = d + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + d2;
            }
            return this;
        }

        public abstract Builder sortBy(String str);

        public abstract Builder userName(String str);

        public Builder setLocation(@Nullable String str) {
            this.location = str;
            return this;
        }
    }
}
