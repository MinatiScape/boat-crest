package com.mappls.sdk.services.api.tripoptimisation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.tripoptimisation.a;
import com.mappls.sdk.services.api.tripoptimisation.model.TripOptimisationResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes8.dex */
public abstract class MapplsTripOptimisation extends MapplsService<TripOptimisationResponse, b> {
    public MapplsTripOptimisation() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL).profile("driving").resource(TripOptimisationCriteria.RESOURCE_TRIP_OPTIMISATION).overview("full").geometries("polyline6");
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("overview", overview());
        hashMap.put("geometries", geometries());
        if (steps() != null) {
            hashMap.put("steps", steps());
        }
        if (sourceType() != null) {
            hashMap.put("source", sourceType());
        }
        if (destinationType() != null) {
            hashMap.put(FirebaseAnalytics.Param.DESTINATION, destinationType());
        }
        if (roundTrip() != null) {
            hashMap.put("roundtrip", roundTrip());
        }
        if (lessVerbose() != null) {
            hashMap.put("lessVerbose", lessVerbose());
        }
        if (continueStraight() != null) {
            hashMap.put("continue_straight", continueStraight());
        }
        return hashMap;
    }

    private String getCoordinates() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(origin());
        arrayList.addAll(waypoints());
        arrayList.add(destination());
        return MapplsUtils.join(";", arrayList.toArray());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract Boolean continueStraight();

    @NonNull
    public abstract String destination();

    @Nullable
    public abstract String destinationType();

    public void enqueue(Callback<TripOptimisationResponse> callback) {
        enqueueCall(callback);
    }

    public Response<TripOptimisationResponse> execute() throws IOException {
        return executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @NonNull
    public abstract String geometries();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return super.getGsonBuilder().registerTypeAdapterFactory(DirectionsAdapterFactory.create());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<TripOptimisationResponse> initializeCall() {
        return getLoginService(true).a(profile(), resource(), getCoordinates(), MapplsAccountManager.getInstance().getRestAPIKey(), createRequest());
    }

    @Nullable
    public abstract Boolean lessVerbose();

    @NonNull
    public abstract String origin();

    @NonNull
    public abstract String overview();

    @NonNull
    public abstract String profile();

    @NonNull
    public abstract String resource();

    @Nullable
    public abstract Boolean roundTrip();

    @Nullable
    public abstract String sourceType();

    @Nullable
    public abstract Boolean steps();

    @NonNull
    public abstract List<String> waypoints();

    @Keep
    @AutoValue.Builder
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        private List<String> coordinates = new ArrayList();

        public Builder addWayPoint(@NonNull Point point) {
            return addWayPoint(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
        }

        public abstract MapplsTripOptimisation autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsTripOptimisation build() {
            if (!MapplsUtils.isEmpty(MapplsAccountManager.getInstance().getAtlasClientId())) {
                waypoints(this.coordinates);
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid Rest API Key.");
        }

        public abstract Builder continueStraight(@Nullable Boolean bool);

        public Builder destination(@NonNull Point point) {
            return destination(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
        }

        public abstract Builder destination(@NonNull String str);

        public abstract Builder destinationType(@Nullable String str);

        public abstract Builder geometries(@NonNull String str);

        public abstract Builder lessVerbose(@Nullable Boolean bool);

        public Builder origin(@NonNull Point point) {
            return origin(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
        }

        public abstract Builder origin(@NonNull String str);

        public abstract Builder overview(@NonNull String str);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder resource(@NonNull String str);

        public abstract Builder roundTrip(@Nullable Boolean bool);

        public abstract Builder sourceType(@Nullable String str);

        public abstract Builder steps(@Nullable Boolean bool);

        public abstract Builder waypoints(@NonNull List<String> list);

        public Builder addWayPoint(@NonNull String str) {
            this.coordinates.add(str);
            return this;
        }
    }
}
