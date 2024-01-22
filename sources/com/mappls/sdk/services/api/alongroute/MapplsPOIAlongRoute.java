package com.mappls.sdk.services.api.alongroute;

import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.alongroute.a;
import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class MapplsPOIAlongRoute extends MapplsService<POIAlongRouteResponse, POIAlongRouteService> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsPOIAlongRoute autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder buffer(@IntRange(from = 25, to = 1000) @Nullable Integer num);

        public MapplsPOIAlongRoute build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid keys.");
        }

        public abstract Builder category(@NonNull String str);

        public abstract Builder geometries(@Nullable String str);

        public abstract Builder page(@Nullable Integer num);

        public abstract Builder path(@NonNull String str);

        public abstract Builder sort(@Nullable Boolean bool);
    }

    public MapplsPOIAlongRoute() {
        super(POIAlongRouteService.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.ATLAS_BASE_URL).geometries("polyline6");
    }

    private HashMap<String, Object> createRequest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("path", path());
        hashMap.put(SavingTrackHelper.POINT_COL_CATEGORY, category());
        if (buffer() != null) {
            hashMap.put("buffer", buffer());
        }
        if (page() != null) {
            hashMap.put("page", page());
        }
        if (geometries() != null) {
            hashMap.put("geometries", geometries());
        }
        if (sort() != null) {
            hashMap.put("sort", sort());
        }
        return hashMap;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public abstract String baseUrl();

    @Nullable
    public abstract Integer buffer();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract String category();

    public void enqueue(Callback<POIAlongRouteResponse> callback) {
        enqueueCall(callback);
    }

    public Response<POIAlongRouteResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract String geometries();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<POIAlongRouteResponse> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract Integer page();

    @NonNull
    public abstract String path();

    @Nullable
    public abstract Boolean sort();
}
