package com.mappls.sdk.services.api.distance;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.distance.a;
import com.mappls.sdk.services.api.distance.models.DistanceResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsDistanceMatrix extends MapplsService<DistanceResponse, DistanceMatrixService> {
    public MapplsDistanceMatrix() {
        super(DistanceMatrixService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL).resource(DirectionsCriteria.RESOURCE_DISTANCE).profile("driving");
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract String coordinates();

    @Nullable
    public abstract String destinations();

    public void enqueue(Callback<DistanceResponse> callback) {
        enqueueCall(callback);
    }

    public Response<DistanceResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract String fallbackCoordinate();

    @Nullable
    public abstract Double fallbackSpeed();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder().registerTypeAdapterFactory(DistanceMatrixAdapterFactory.create());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<DistanceResponse> initializeCall() {
        return getLoginService(true).getCall(resource(), profile(), coordinates(), MapplsAccountManager.getInstance().getRestAPIKey(), routeType(), sources(), destinations(), fallbackSpeed(), fallbackCoordinate());
    }

    @NonNull
    public abstract String profile();

    @NonNull
    public abstract String resource();

    @Nullable
    public abstract Integer routeType();

    @Nullable
    public abstract String sources();

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        private List<String> coordinates = new ArrayList();
        private List<Integer> destinations;
        private List<Integer> sources;

        private static String formatCoordinates(List<String> list) {
            return MapplsUtils.join(";", list.toArray());
        }

        public abstract MapplsDistanceMatrix autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsDistanceMatrix build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                List<String> list = this.coordinates;
                if (list != null && list.size() >= 2) {
                    if (this.coordinates.size() <= 25) {
                        coordinates(formatCoordinates(this.coordinates));
                        List<Integer> list2 = this.sources;
                        if (list2 != null) {
                            sources(MapplsUtils.join(";", list2.toArray()));
                        }
                        List<Integer> list3 = this.destinations;
                        if (list3 != null) {
                            destinations(MapplsUtils.join(";", list3.toArray()));
                        }
                        return autoBuild();
                    }
                    throw new ServicesException("Maximum of 25 coordinates are allowed for this API.");
                }
                throw new ServicesException("At least two coordinates must be provided with your API request.");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public Builder coordinate(@NonNull Point point) {
            this.coordinates.add(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
            return this;
        }

        public Builder coordinateList(List<String> list) {
            this.coordinates.addAll(list);
            return this;
        }

        public abstract Builder coordinates(@NonNull String str);

        public Builder coordinates(List<Point> list) {
            ArrayList arrayList = new ArrayList();
            for (Point point : list) {
                arrayList.add(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
            }
            this.coordinates.addAll(arrayList);
            return this;
        }

        public abstract Builder destinations(@Nullable String str);

        public Builder destinations(List<Integer> list) {
            this.destinations = list;
            return this;
        }

        public abstract Builder fallbackCoordinate(@Nullable String str);

        public abstract Builder fallbackSpeed(@Nullable Double d);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder resource(@NonNull String str);

        public abstract Builder routeType(@Nullable Integer num);

        public abstract Builder sources(@Nullable String str);

        public Builder sources(List<Integer> list) {
            this.sources = list;
            return this;
        }

        public Builder coordinate(String str) {
            this.coordinates.add(str);
            return this;
        }
    }
}
