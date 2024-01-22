package com.mappls.sdk.services.api.predictive.directions;

import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Scopes;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType;
import com.mappls.sdk.services.api.predictive.directions.a;
import com.mappls.sdk.services.api.predictive.directions.model.PredictiveDirectionsResponse;
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
/* loaded from: classes7.dex */
public abstract class MapplsPredictiveDirections extends MapplsService<PredictiveDirectionsResponse, b> {
    public MapplsPredictiveDirections() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ADVANCE_MAP_BASE_URL).profile("driving");
    }

    private static String formatCoordinates(List<String> list) {
        return MapplsUtils.join(";", list.toArray());
    }

    private Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("locations", formatCoordinates(coordinates()));
        hashMap.put(Scopes.PROFILE, profile());
        if (internalSpeedType() != null) {
            hashMap.put("speedTypes", internalSpeedType());
        }
        if (internalDateTime() != null) {
            hashMap.put("date_time", internalDateTime());
        }
        if (heading() != null) {
            hashMap.put("heading", heading());
        }
        if (headingTolerance() != null) {
            hashMap.put("heading_tolerance", headingTolerance());
        }
        if (preferredSide() != null) {
            hashMap.put("preferred_side", preferredSide());
        }
        if (searchCutoff() != null) {
            hashMap.put("search_cutoff", searchCutoff());
        }
        if (avoidLocations() != null) {
            hashMap.put("avoid_locations", formatCoordinates(avoidLocations()));
        }
        if (internalAvoidPolygons() != null) {
            hashMap.put("avoid_polygons", formatCoordinates(internalAvoidPolygons()));
        }
        if (routeName() != null) {
            hashMap.put("id", routeName());
        }
        if (useFerry() != null) {
            hashMap.put("use_ferry", useFerry());
        }
        if (useHighway() != null) {
            hashMap.put("use_highway", useHighway());
        }
        if (alternatives() != null) {
            hashMap.put("alternatives", alternatives());
        }
        if (excludeTunnel() != null) {
            hashMap.put("exclude_tunnel", excludeTunnel());
        }
        if (excludeBridge() != null) {
            hashMap.put("exclude_bridge", excludeBridge());
        }
        if (avoidTolls() != null) {
            hashMap.put("avoid_tolls", avoidTolls());
        }
        if (minRouteClass() != null) {
            hashMap.put("min_route_class", minRouteClass());
        }
        if (maxRouteClass() != null) {
            hashMap.put("max_route_class", maxRouteClass());
        }
        if (height() != null) {
            hashMap.put(Property.ICON_TEXT_FIT_HEIGHT, height());
        }
        if (width() != null) {
            hashMap.put(Property.ICON_TEXT_FIT_WIDTH, width());
        }
        if (length() != null) {
            hashMap.put("length", length());
        }
        if (weight() != null) {
            hashMap.put("weight", weight());
        }
        if (axleLoad() != null) {
            hashMap.put("axle_load", axleLoad());
        }
        if (hazmat() != null) {
            hashMap.put("hazmat", hazmat());
        }
        return hashMap;
    }

    @Nullable
    public abstract Integer alternatives();

    @Nullable
    public abstract List<String> avoidLocations();

    @Nullable
    public abstract Boolean avoidTolls();

    @Nullable
    public abstract Double axleLoad();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract List<String> coordinates();

    public void enqueue(Callback<PredictiveDirectionsResponse> callback) {
        enqueueCall(callback);
    }

    @Nullable
    public abstract Boolean excludeBridge();

    @Nullable
    public abstract Boolean excludeTunnel();

    public Response<PredictiveDirectionsResponse> execute() throws IOException {
        return executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract Boolean hazmat();

    @Nullable
    public abstract Integer heading();

    @Nullable
    public abstract Integer headingTolerance();

    @Nullable
    public abstract Double height();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PredictiveDirectionsResponse> initializeCall() {
        return getLoginService(true).getCall(toMap());
    }

    @Nullable
    public abstract List<String> internalAvoidPolygons();

    @Nullable
    public abstract String internalDateTime();

    @Nullable
    public abstract String internalSpeedType();

    @Nullable
    public abstract Double length();

    @Nullable
    public abstract String maxRouteClass();

    @Nullable
    public abstract String minRouteClass();

    @Nullable
    public abstract String preferredSide();

    @NonNull
    public abstract String profile();

    @Nullable
    public abstract String routeName();

    @Nullable
    public abstract Integer searchCutoff();

    @Nullable
    @FloatRange(from = 0.0d, to = 1.0d)
    public abstract Double useFerry();

    @Nullable
    @FloatRange(from = 0.0d, to = 1.0d)
    public abstract Double useHighway();

    @Nullable
    public abstract Double weight();

    @Nullable
    public abstract Double width();

    @Keep
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        private Point[] avoidLocations;
        private List<List<Point>> avoidPolygons;
        private List<String> coordinates = new ArrayList();
        private String destination;
        private String origin;

        public Builder addWaypoint(@NonNull Point point) {
            this.coordinates.add(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
            return this;
        }

        public abstract Builder alternatives(@Nullable Integer num);

        public abstract MapplsPredictiveDirections autoBuild();

        public abstract Builder avoidLocations(List<String> list);

        public Builder avoidLocations(Point... pointArr) {
            this.avoidLocations = pointArr;
            return this;
        }

        public Builder avoidPolygons(List<List<Point>> list) {
            this.avoidPolygons = list;
            return this;
        }

        public abstract Builder avoidTolls(@Nullable Boolean bool);

        public abstract Builder axleLoad(@Nullable Double d);

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsPredictiveDirections build() {
            Point[] pointArr;
            if (!MapplsUtils.isEmpty(MapplsAccountManager.getInstance().getAtlasClientId())) {
                String str = this.origin;
                if (str != null) {
                    this.coordinates.add(0, str);
                }
                String str2 = this.destination;
                if (str2 != null) {
                    this.coordinates.add(str2);
                }
                if (this.coordinates.size() >= 2) {
                    coordinates(this.coordinates);
                    if (this.avoidLocations != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Point point : this.avoidLocations) {
                            arrayList.add(point.longitude() + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + point.latitude());
                        }
                        avoidLocations(arrayList);
                    }
                    if (this.avoidPolygons != null) {
                        ArrayList arrayList2 = new ArrayList();
                        if (this.avoidPolygons.size() == 1) {
                            for (Point point2 : this.avoidPolygons.get(0)) {
                                arrayList2.add(point2.longitude() + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + point2.latitude());
                            }
                            internalAvoidPolygons(arrayList2);
                        }
                    }
                    return autoBuild();
                }
                throw new ServicesException("An origin and destination are required before making the directions API request.");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid API Key.");
        }

        public abstract Builder coordinates(@NonNull List<String> list);

        public Builder destination(@NonNull Point point) {
            this.destination = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public abstract Builder excludeBridge(@Nullable Boolean bool);

        public abstract Builder excludeTunnel(@Nullable Boolean bool);

        public abstract Builder hazmat(@Nullable Boolean bool);

        public abstract Builder heading(@Nullable Integer num);

        public abstract Builder headingTolerance(@Nullable Integer num);

        public abstract Builder height(@Nullable Double d);

        public abstract Builder internalAvoidPolygons(List<String> list);

        public abstract Builder internalDateTime(@Nullable String str);

        public abstract Builder internalSpeedType(@Nullable String str);

        public abstract Builder length(@Nullable Double d);

        public abstract Builder maxRouteClass(@Nullable String str);

        public abstract Builder minRouteClass(@Nullable String str);

        public Builder origin(@NonNull Point point) {
            this.origin = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public abstract Builder preferredSide(@Nullable String str);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder routeName(@Nullable String str);

        public abstract Builder searchCutoff(@Nullable Integer num);

        public Builder speedType(@Nullable MapplsDirectionSpeedType mapplsDirectionSpeedType) {
            return internalSpeedType(mapplsDirectionSpeedType.speedType()).internalDateTime(mapplsDirectionSpeedType.speedDateTime());
        }

        public abstract Builder useFerry(@Nullable @FloatRange(from = 0.0d, to = 1.0d) Double d);

        public abstract Builder useHighway(@Nullable @FloatRange(from = 0.0d, to = 1.0d) Double d);

        public abstract Builder weight(@Nullable Double d);

        public abstract Builder width(@Nullable Double d);

        public Builder addWaypoint(@NonNull String str) {
            this.coordinates.add(str);
            return this;
        }

        public Builder destination(@NonNull String str) {
            this.destination = str;
            return this;
        }

        public Builder origin(@NonNull String str) {
            this.origin = str;
            return this;
        }
    }
}
