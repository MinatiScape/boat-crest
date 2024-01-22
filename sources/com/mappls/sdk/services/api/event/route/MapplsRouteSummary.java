package com.mappls.sdk.services.api.event.route;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.event.route.a;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes4.dex */
public abstract class MapplsRouteSummary extends MapplsService<RouteReportSummaryResponse, RouteSummaryService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes4.dex */
    public static abstract class Builder {
        public abstract MapplsRouteSummary autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsRouteSummary build() throws ServicesException {
            if (!MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId()) && !MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                throw new ServicesException("Using Mappls Services requires setting a valid client_id and client_secret.");
            }
            return autoBuild();
        }

        public Builder categories(@Nullable String... strArr) {
            return internalCategories(MapplsUtils.join(Constants.SEPARATOR_COMMA, strArr));
        }

        public abstract Builder currentNode(@Nullable String str);

        public abstract Builder internalCategories(@Nullable String str);

        public abstract Builder isGroup(@Nullable Integer num);

        public abstract Builder routeId(@NonNull String str);

        public abstract Builder routeIdx(@Nullable Integer num);

        public abstract Builder screenName(@Nullable String str);
    }

    public MapplsRouteSummary() {
        super(RouteSummaryService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(com.mappls.sdk.services.utils.Constants.EXPLORE_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract String currentNode();

    public void enqueue(Callback<RouteReportSummaryResponse> callback) {
        enqueueCall(callback);
    }

    public Response<RouteReportSummaryResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<RouteReportSummaryResponse> initializeCall() {
        return getLoginService(true).getCall(routeId(), routeIdx(), currentNode(), isGroup(), internalCategories(), screenName());
    }

    @Nullable
    public abstract String internalCategories();

    @Nullable
    public abstract Integer isGroup();

    @NonNull
    public abstract String routeId();

    @Nullable
    public abstract Integer routeIdx();

    @Nullable
    public abstract String screenName();
}
