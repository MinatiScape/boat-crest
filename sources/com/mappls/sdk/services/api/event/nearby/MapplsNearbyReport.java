package com.mappls.sdk.services.api.event.nearby;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.event.nearby.a;
import com.mappls.sdk.services.api.event.nearby.model.NearbyReportResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes3.dex */
public abstract class MapplsNearbyReport extends MapplsService<NearbyReportResponse, NearbyReportService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes3.dex */
    public static abstract class Builder {
        public Point bottomRight;
        public Point topLeft;

        public abstract MapplsNearbyReport autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public Builder bottomRight(@NonNull Point point) {
            this.bottomRight = point;
            return this;
        }

        public MapplsNearbyReport build() throws ServicesException {
            if (!MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId()) && !MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                throw new ServicesException("Using Mappls Services requires setting a valid client_id and client_secret.");
            }
            Point point = this.topLeft;
            if (point != null) {
                minX(Double.valueOf(point.longitude()));
                minY(Double.valueOf(this.topLeft.latitude()));
                Point point2 = this.bottomRight;
                if (point2 != null) {
                    maxX(Double.valueOf(point2.longitude()));
                    maxY(Double.valueOf(this.bottomRight.latitude()));
                    return autoBuild();
                }
                throw new ServicesException("Please pass a valid bottom right coordinates.");
            }
            throw new ServicesException("Please pass a valid top left coordinates.");
        }

        public abstract Builder maxX(@NonNull Double d);

        public abstract Builder maxY(@NonNull Double d);

        public abstract Builder minX(@NonNull Double d);

        public abstract Builder minY(@NonNull Double d);

        public Builder topLeft(@NonNull Point point) {
            this.topLeft = point;
            return this;
        }
    }

    public MapplsNearbyReport() {
        super(NearbyReportService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.EXPLORE_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    public void enqueue(Callback<NearbyReportResponse> callback) {
        enqueueCall(callback);
    }

    public Response<NearbyReportResponse> execute() throws IOException {
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
    public Call<NearbyReportResponse> initializeCall() {
        return getLoginService(true).getCall(minX().doubleValue(), minY().doubleValue(), maxX().doubleValue(), maxY().doubleValue());
    }

    public abstract Double maxX();

    public abstract Double maxY();

    public abstract Double minX();

    public abstract Double minY();
}
