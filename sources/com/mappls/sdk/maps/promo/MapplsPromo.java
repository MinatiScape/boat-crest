package com.mappls.sdk.maps.promo;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.promo.a;
import com.mappls.sdk.maps.promo.model.Promo;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.Constants;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsPromo extends MapplsService<List<Promo>, b> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsPromo a();

        public MapplsPromo build() throws ServicesException {
            return a();
        }
    }

    public MapplsPromo() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().b(Constants.ANCHOR_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<List<Promo>> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<List<Promo>> initializeCall() {
        return getLoginService(true).getCall();
    }
}
