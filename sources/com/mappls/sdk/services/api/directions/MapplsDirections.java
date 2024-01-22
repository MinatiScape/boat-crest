package com.mappls.sdk.services.api.directions;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.directions.b;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
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
public abstract class MapplsDirections extends MapplsService<DirectionsResponse, DirectionsService> {
    public static final int MAX_URL_SIZE = 8192;

    /* loaded from: classes11.dex */
    public class a implements Callback<DirectionsResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callback f13164a;

        public a(Callback callback) {
            this.f13164a = callback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DirectionsResponse> call, Throwable th) {
            this.f13164a.onFailure(call, th);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
            this.f13164a.onResponse(call, new DirectionsResponseFactory(MapplsDirections.this).generate(response));
        }
    }

    public MapplsDirections() {
        super(DirectionsService.class);
    }

    public static Builder builder() {
        return new b.C0669b().baseUrl(Constants.ADVANCE_MAP_BASE_URL).profile("driving").resource(DirectionsCriteria.RESOURCE_ROUTE).user(DirectionsCriteria.PROFILE_DEFAULT_USER).overview("full").geometries("polyline6").isSort(Boolean.TRUE);
    }

    private Call<DirectionsResponse> callForUrlLength() {
        Call<DirectionsResponse> call = get();
        return call.request().url().toString().length() < 8192 ? call : post();
    }

    private static String formatCoordinates(List<String> list) {
        return MapplsUtils.join(";", list.toArray());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatWaypointTargets(Point[] pointArr) {
        String[] strArr = new String[pointArr.length];
        int i = 0;
        for (Point point : pointArr) {
            if (point == null) {
                strArr[i] = "";
                i++;
            } else {
                strArr[i] = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude()));
                i++;
            }
        }
        return MapplsUtils.join(";", strArr);
    }

    private Call<DirectionsResponse> get() {
        return getLoginService(true).getCall(profile(), resource(), formatCoordinates(coordinates()), MapplsAccountManager.getInstance().getRestAPIKey(), alternatives(), geometries(), overview(), radius(), steps(), bearing(), lessVerbose(), annotation(), language(), roundaboutExits(), continueStraight(), bannerInstructions(), exclude(), routeRefresh(), deviceId(), sessionId(), isSort(), skipWaypoints(), instructions(), routeType());
    }

    private boolean hasWalkingOptions() {
        return walkingOptions() != null;
    }

    private Call<DirectionsResponse> post() {
        return getLoginService(true).postCall(profile(), resource(), formatCoordinates(coordinates()), MapplsAccountManager.getInstance().getRestAPIKey(), alternatives(), geometries(), overview(), radius(), steps(), bearing(), lessVerbose(), annotation(), language(), roundaboutExits(), continueStraight(), bannerInstructions(), exclude(), routeRefresh(), deviceId(), sessionId(), isSort(), skipWaypoints(), instructions(), routeType());
    }

    @Nullable
    public Double alleyBias() {
        if (hasWalkingOptions()) {
            return walkingOptions().alleyBias();
        }
        return null;
    }

    @Nullable
    public abstract Boolean alternatives();

    @Nullable
    public abstract String annotation();

    @Nullable
    public abstract String approaches();

    @Nullable
    public abstract Boolean bannerInstructions();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract String bearing();

    public void cancel() {
        cancelCall();
    }

    @Nullable
    public abstract String clientAppName();

    @Nullable
    public abstract Boolean continueStraight();

    @NonNull
    public abstract List<String> coordinates();

    @Nullable
    public abstract String deviceId();

    public void enqueue(Callback<DirectionsResponse> callback) {
        getCall().enqueue(new a(callback));
    }

    @Nullable
    public abstract String exclude();

    public Response<DirectionsResponse> execute() throws IOException {
        return new DirectionsResponseFactory(this).generate(super.executeCall());
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract String geometries();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        return super.getGsonBuilder().registerTypeAdapterFactory(DirectionsAdapterFactory.create());
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<DirectionsResponse> initializeCall() {
        if (usePostMethod() == null) {
            return callForUrlLength();
        }
        if (usePostMethod().booleanValue()) {
            return post();
        }
        return get();
    }

    @Nullable
    public abstract Boolean instructions();

    @Nullable
    public abstract Boolean isSort();

    @Nullable
    public abstract String language();

    @Nullable
    public abstract Boolean lessVerbose();

    @Nullable
    public abstract String overview();

    @NonNull
    public abstract String profile();

    @Nullable
    public abstract String radius();

    @NonNull
    public abstract String resource();

    @Nullable
    public abstract Boolean roundaboutExits();

    @Nullable
    public abstract Boolean routeRefresh();

    @Nullable
    public abstract Integer routeType();

    @Nullable
    public abstract String sessionId();

    @Nullable
    public abstract Boolean skipWaypoints();

    @Nullable
    public abstract Boolean steps();

    public abstract Builder toBuilder();

    @Nullable
    public abstract Boolean usePostMethod();

    @NonNull
    public abstract String user();

    @Nullable
    public abstract WalkingOptions walkingOptions();

    @Nullable
    public Double walkingSpeed() {
        if (hasWalkingOptions()) {
            return walkingOptions().walkingSpeed();
        }
        return null;
    }

    @Nullable
    public Double walkwayBias() {
        if (hasWalkingOptions()) {
            return walkingOptions().walkwayBias();
        }
        return null;
    }

    @Nullable
    public abstract String waypointIndices();

    @Nullable
    public abstract String waypointNames();

    @Nullable
    public abstract String waypointTargets();

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        private String[] annotations;
        private String[] approaches;
        private List<Double[]> bearings = new ArrayList();
        private List<String> coordinates = new ArrayList();
        private String destination;
        private Boolean excludeContainmentZone;
        private String[] excludes;
        private String origin;
        private double[] radiuses;
        private Integer[] waypointIndices;
        private String[] waypointNames;
        private Point[] waypointTargets;

        public Builder addApproaches(String... strArr) {
            this.approaches = strArr;
            return this;
        }

        public Builder addBearing(@Nullable @FloatRange(from = 0.0d, to = 360.0d) Double d, @Nullable @FloatRange(from = 0.0d, to = 360.0d) Double d2) {
            if (d != null && d2 != null) {
                this.bearings.add(new Double[]{d, d2});
            } else {
                this.bearings.add(new Double[0]);
            }
            return this;
        }

        public Builder addWaypoint(@NonNull Point point) {
            this.coordinates.add(String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude())));
            return this;
        }

        public Builder addWaypointIndices(@IntRange(from = 0) @Nullable Integer... numArr) {
            this.waypointIndices = numArr;
            return this;
        }

        public Builder addWaypointNames(@Nullable String... strArr) {
            this.waypointNames = strArr;
            return this;
        }

        public Builder addWaypointTargets(@Nullable Point... pointArr) {
            this.waypointTargets = pointArr;
            return this;
        }

        public abstract Builder alternatives(@Nullable Boolean bool);

        public abstract Builder annotation(@Nullable String str);

        public Builder annotations(@Nullable String... strArr) {
            this.annotations = strArr;
            return this;
        }

        public abstract Builder approaches(@Nullable String str);

        public abstract MapplsDirections autoBuild();

        public abstract Builder bannerInstructions(@Nullable Boolean bool);

        public abstract Builder baseUrl(String str);

        public abstract Builder bearing(@Nullable String str);

        public MapplsDirections build() {
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
                    Integer[] numArr = this.waypointIndices;
                    if (numArr != null) {
                        if (numArr.length >= 2) {
                            if (numArr[0].intValue() == 0) {
                                Integer[] numArr2 = this.waypointIndices;
                                if (numArr2[numArr2.length - 1].intValue() == this.coordinates.size() - 1) {
                                    int i = 1;
                                    while (true) {
                                        Integer[] numArr3 = this.waypointIndices;
                                        if (i >= numArr3.length - 1) {
                                            break;
                                        } else if (numArr3[i].intValue() < 0 || this.waypointIndices[i].intValue() >= this.coordinates.size()) {
                                            break;
                                        } else {
                                            i++;
                                        }
                                    }
                                    throw new ServicesException("Waypoints index too large (no corresponding coordinate)");
                                }
                            }
                            throw new ServicesException("Waypoints must contain indices of the first and last coordinates");
                        }
                        throw new ServicesException("Waypoints must be a list of at least two indexes separated by ';'");
                    }
                    String[] strArr = this.waypointNames;
                    if (strArr != null) {
                        waypointNames(MapplsUtils.formatWaypointNames(strArr));
                    }
                    Point[] pointArr = this.waypointTargets;
                    if (pointArr != null) {
                        if (pointArr.length == this.coordinates.size()) {
                            waypointTargets(MapplsDirections.formatWaypointTargets(this.waypointTargets));
                        } else {
                            throw new ServicesException("Number of waypoint targets must match  the number of waypoints provided.");
                        }
                    }
                    String[] strArr2 = this.approaches;
                    if (strArr2 != null) {
                        if (strArr2.length == this.coordinates.size()) {
                            String formatApproaches = MapplsUtils.formatApproaches(this.approaches);
                            if (formatApproaches != null) {
                                approaches(formatApproaches);
                            } else {
                                throw new ServicesException("All approaches values must be one of curb, unrestricted");
                            }
                        } else {
                            throw new ServicesException("Number of approach elements must match number of coordinates provided.");
                        }
                    }
                    coordinates(this.coordinates);
                    bearing(MapplsUtils.formatBearing(this.bearings));
                    String join = MapplsUtils.join(com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, this.annotations);
                    String str3 = null;
                    if (join != null && join.equalsIgnoreCase("null")) {
                        join = null;
                    }
                    annotation(join);
                    String join2 = MapplsUtils.join(com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, this.excludes);
                    if (join2 == null || !join2.equalsIgnoreCase("null")) {
                        str3 = join2;
                    }
                    exclude(str3);
                    radius(MapplsUtils.formatRadiuses(this.radiuses));
                    waypointIndices(MapplsUtils.join(";", this.waypointIndices));
                    Boolean bool = this.excludeContainmentZone;
                    if (bool != null && bool.booleanValue()) {
                        routeType(2);
                    }
                    return autoBuild();
                }
                throw new ServicesException("An origin and destination are required before making the directions API request.");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid API Key.");
        }

        public abstract Builder clientAppName(@NonNull String str);

        public abstract Builder continueStraight(@Nullable Boolean bool);

        public abstract Builder coordinates(@NonNull List<String> list);

        public Builder destination(@NonNull Point point) {
            this.destination = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public abstract Builder deviceId(@Nullable String str);

        public abstract Builder exclude(String str);

        public Builder excludeContainmentZone(@Nullable Boolean bool) {
            this.excludeContainmentZone = bool;
            return this;
        }

        public Builder excludes(String... strArr) {
            this.excludes = strArr;
            return this;
        }

        public abstract Builder geometries(String str);

        public Builder get() {
            usePostMethod(Boolean.FALSE);
            return this;
        }

        public abstract Builder instructions(@Nullable Boolean bool);

        public abstract Builder isSort(@Nullable Boolean bool);

        public abstract Builder language(@Nullable String str);

        public Builder language(@Nullable Locale locale) {
            if (locale != null) {
                language(locale.getLanguage());
            }
            return this;
        }

        public abstract Builder lessVerbose(@Nullable Boolean bool);

        public Builder origin(@NonNull Point point) {
            this.origin = String.format(Locale.US, "%s,%s", MapplsUtils.formatCoordinate(point.longitude()), MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public abstract Builder overview(@Nullable String str);

        public Builder post() {
            usePostMethod(Boolean.TRUE);
            return this;
        }

        public abstract Builder profile(@NonNull String str);

        public abstract Builder radius(@Nullable String str);

        public Builder radiuses(@FloatRange(from = 0.0d) double... dArr) {
            this.radiuses = dArr;
            return this;
        }

        public abstract Builder resource(@NonNull String str);

        public abstract Builder roundaboutExits(@Nullable Boolean bool);

        public abstract Builder routeRefresh(@Nullable Boolean bool);

        public abstract Builder routeType(@Nullable Integer num);

        public abstract Builder sessionId(@Nullable String str);

        public abstract Builder skipWaypoints(@Nullable Boolean bool);

        public abstract Builder steps(@Nullable Boolean bool);

        public abstract Builder usePostMethod(@NonNull Boolean bool);

        public abstract Boolean usePostMethod();

        public abstract Builder user(@NonNull String str);

        public abstract Builder walkingOptions(@NonNull WalkingOptions walkingOptions);

        public abstract WalkingOptions walkingOptions();

        public abstract Builder waypointIndices(@Nullable String str);

        public abstract Builder waypointNames(@Nullable String str);

        public abstract Builder waypointTargets(@Nullable String str);

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
