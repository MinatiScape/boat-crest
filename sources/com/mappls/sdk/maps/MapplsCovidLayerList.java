package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.d;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsCovidLayerList extends MapplsService<List<InteractiveLayer>, CovidLayerService> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsCovidLayerList autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsCovidLayerList build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getRestAPIKey())) {
                return autoBuild();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }
    }

    public MapplsCovidLayerList() {
        super(CovidLayerService.class);
    }

    public static Builder a() {
        return new d.b().baseUrl(Constants.MGIS_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<List<InteractiveLayer>> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<List<InteractiveLayer>> initializeCall() {
        return getService(true).getCall();
    }
}
