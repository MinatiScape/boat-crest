package com.mappls.sdk.services.api.geolocation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.geolocation.a;
import com.mappls.sdk.services.api.geolocation.model.GeolocationResponse;
import com.mappls.sdk.services.utils.Constants;
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
/* loaded from: classes6.dex */
public abstract class MapplsGeolocation extends MapplsService<GeolocationResponse, GeolocationService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public List<GeolocationRequest> geolocationRequests = new ArrayList();

        public Builder addCellTower(@NonNull Integer num, @NonNull Integer num2, @NonNull Integer num3, @NonNull Integer num4) {
            this.geolocationRequests.add(new GeolocationRequest(num, num2, num3, num4));
            return this;
        }

        public abstract MapplsGeolocation autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsGeolocation build() throws ServicesException {
            if (this.geolocationRequests.size() != 0) {
                cellTowers(this.geolocationRequests);
                return autoBuild();
            }
            throw new ServicesException("Please provide atleast one tower detail");
        }

        public abstract Builder cellTowers(@NonNull List<GeolocationRequest> list);
    }

    public MapplsGeolocation() {
        super(GeolocationService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ATLAS_BASE_URL);
    }

    private Map createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("cellTowers", cellTowers());
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract List<GeolocationRequest> cellTowers();

    public void enqueue(Callback<GeolocationResponse> callback) {
        super.enqueueCall(callback);
    }

    public Response<GeolocationResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<GeolocationResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }
}
