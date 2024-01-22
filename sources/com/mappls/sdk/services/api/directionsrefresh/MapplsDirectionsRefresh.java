package com.mappls.sdk.services.api.directionsrefresh;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directionsrefresh.a;
import com.mappls.sdk.services.api.directionsrefresh.models.DirectionsRefreshAdapterFactory;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsDirectionsRefresh extends MapplsService<DirectionsRoute, DirectionsRefreshService> {
    private static final int ZERO = 0;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder baseUrl(String str);

        public abstract MapplsDirectionsRefresh build();

        public abstract Builder categories(@Nullable String str);

        public abstract Builder isNotify(@Nullable Boolean bool);

        public abstract Builder isRefresh(@NonNull Boolean bool);

        public abstract Builder isSort(@Nullable Boolean bool);

        public abstract Builder nodeIndex(@Nullable Long l);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder requestId(String str);

        public abstract Builder routeIndex(@NonNull Integer num);

        public abstract Builder sessionId(@NonNull String str);

        public Builder source(@NonNull Point point) {
            return sourceInternal(point.longitude() + Constants.SEPARATOR_COMMA + point.latitude());
        }

        public abstract Builder sourceInternal(@Nullable String str);

        public abstract Builder tripType(@NonNull Integer num);
    }

    /* loaded from: classes11.dex */
    public class a implements Callback<DirectionsRoute> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callback f13203a;

        public a(Callback callback) {
            this.f13203a = callback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DirectionsRoute> call, Throwable th) {
            this.f13203a.onFailure(call, th);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DirectionsRoute> call, Response<DirectionsRoute> response) {
            this.f13203a.onResponse(call, new DirectionsRefreshResponseFactory(MapplsDirectionsRefresh.this).generate(response));
        }
    }

    public MapplsDirectionsRefresh() {
        super(DirectionsRefreshService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(com.mappls.sdk.services.utils.Constants.ADVANCE_MAP_BASE_URL).profile("driving").routeIndex(0);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract String categories();

    public void enqueue(Callback<DirectionsRoute> callback) {
        enqueueCall(new a(callback));
    }

    public Response<DirectionsRoute> execute() throws IOException {
        return new DirectionsRefreshResponseFactory(this).generate(super.executeCall());
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return super.getGsonBuilder().registerTypeAdapterFactory(DirectionsRefreshAdapterFactory.create()).registerTypeAdapterFactory(DirectionsAdapterFactory.create());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<DirectionsRoute> initializeCall() {
        return getLoginService(true).getCall(MapplsAccountManager.getInstance().getRestAPIKey(), profile(), requestId(), routeIndex(), isRefresh(), isNotify(), nodeIndex(), categories(), tripType(), sessionId(), isSort(), sourceInternal());
    }

    @Nullable
    public abstract Boolean isNotify();

    @Nullable
    public abstract Boolean isRefresh();

    @Nullable
    public abstract Boolean isSort();

    @Nullable
    public abstract Long nodeIndex();

    @NonNull
    public abstract String profile();

    public abstract String requestId();

    public abstract Integer routeIndex();

    @Nullable
    public abstract String sessionId();

    @Nullable
    public abstract String sourceInternal();

    public abstract Builder toBuilder();

    public abstract Integer tripType();
}
