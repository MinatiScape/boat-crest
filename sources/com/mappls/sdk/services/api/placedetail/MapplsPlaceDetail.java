package com.mappls.sdk.services.api.placedetail;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.placedetail.a;
import com.mappls.sdk.services.api.placedetail.model.PlaceDetailResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsPlaceDetail extends MapplsService<PlaceDetailResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract MapplsPlaceDetail autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsPlaceDetail build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public abstract Builder mapplsPin(@NonNull String str);
    }

    public MapplsPlaceDetail() {
        super(b.class);
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

    public void enqueue(Callback<PlaceDetailResponse> callback) {
        enqueueCall(callback);
    }

    public Response<PlaceDetailResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PlaceDetailResponse> initializeCall() {
        return getLoginService(true).getCall(mapplsPin());
    }

    @NonNull
    public abstract String mapplsPin();
}
