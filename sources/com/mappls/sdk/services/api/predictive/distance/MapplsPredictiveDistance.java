package com.mappls.sdk.services.api.predictive.distance;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.Scopes;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType;
import com.mappls.sdk.services.api.predictive.distance.a;
import com.mappls.sdk.services.api.predictive.distance.models.PredictiveDistanceResponse;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes7.dex */
public abstract class MapplsPredictiveDistance extends MapplsService<PredictiveDistanceResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        private final List<String> sources = new ArrayList();
        private final List<String> target = new ArrayList();

        public Builder addDestination(Point point) {
            List<String> list = this.target;
            list.add(MapplsUtils.formatCoordinate(point.longitude()) + Constants.SEPARATOR_COMMA + MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public Builder addSource(Point point) {
            List<String> list = this.sources;
            list.add(MapplsUtils.formatCoordinate(point.longitude()) + Constants.SEPARATOR_COMMA + MapplsUtils.formatCoordinate(point.latitude()));
            return this;
        }

        public abstract MapplsPredictiveDistance autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsPredictiveDistance build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                if (this.sources.size() >= 1) {
                    if (this.target.size() >= 1) {
                        internalSources(this.sources);
                        internalDestination(this.target);
                        return autoBuild();
                    }
                    throw new ServicesException("At least one coordinates must be provided for destination with your API request.");
                }
                throw new ServicesException("At least one coordinates must be provided for source with your API request.");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public Builder destinationList(List<String> list) {
            this.target.addAll(list);
            return this;
        }

        public Builder destinations(List<Point> list) {
            ArrayList arrayList = new ArrayList();
            for (Point point : list) {
                arrayList.add(MapplsUtils.formatCoordinate(point.longitude()) + Constants.SEPARATOR_COMMA + MapplsUtils.formatCoordinate(point.latitude()));
            }
            this.target.addAll(arrayList);
            return this;
        }

        public abstract Builder internalDateTime(@Nullable String str);

        public abstract Builder internalDestination(List<String> list);

        public abstract Builder internalSources(List<String> list);

        public abstract Builder internalSpeedType(@Nullable String str);

        public abstract Builder profile(@NonNull String str);

        public Builder sourceList(List<String> list) {
            this.sources.addAll(list);
            return this;
        }

        public Builder sources(List<Point> list) {
            ArrayList arrayList = new ArrayList();
            for (Point point : list) {
                arrayList.add(MapplsUtils.formatCoordinate(point.longitude()) + Constants.SEPARATOR_COMMA + MapplsUtils.formatCoordinate(point.latitude()));
            }
            this.sources.addAll(arrayList);
            return this;
        }

        public Builder speedType(@Nullable MapplsDirectionSpeedType mapplsDirectionSpeedType) {
            return internalSpeedType(mapplsDirectionSpeedType.speedType()).internalDateTime(mapplsDirectionSpeedType.speedDateTime());
        }

        public Builder addDestination(String str) {
            this.target.add(str);
            return this;
        }

        public Builder addSource(String str) {
            this.sources.add(str);
            return this;
        }
    }

    public MapplsPredictiveDistance() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(com.mappls.sdk.services.utils.Constants.ADVANCE_MAP_BASE_URL).profile("driving");
    }

    private static String formatCoordinates(List<String> list) {
        return MapplsUtils.join(";", list.toArray());
    }

    private Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("source", formatCoordinates(internalSources()));
        hashMap.put(TypedValues.AttributesType.S_TARGET, formatCoordinates(internalDestination()));
        hashMap.put(Scopes.PROFILE, profile());
        if (internalSpeedType() != null) {
            hashMap.put("speedTypes", internalSpeedType());
        }
        if (internalDateTime() != null) {
            hashMap.put("date_time", internalDateTime());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<PredictiveDistanceResponse> callback) {
        enqueueCall(callback);
    }

    public Response<PredictiveDistanceResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PredictiveDistanceResponse> initializeCall() {
        return getLoginService(true).getCall(toMap());
    }

    @Nullable
    public abstract String internalDateTime();

    @NonNull
    public abstract List<String> internalDestination();

    @NonNull
    public abstract List<String> internalSources();

    @Nullable
    public abstract String internalSpeedType();

    @NonNull
    public abstract String profile();
}
