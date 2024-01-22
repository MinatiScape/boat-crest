package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.f;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import retrofit2.Call;
import retrofit2.Callback;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsGetStyle extends MapplsService<GetStylesResponse, d0> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsGetStyle a();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsGetStyle build() throws ServicesException {
            return a();
        }

        public abstract Builder logoResolution(@NonNull String str);
    }

    public MapplsGetStyle() {
        super(d0.class);
    }

    public static Builder a() {
        return new f.b().baseUrl("https://apis.mappls.com/advancedmaps/vapi/");
    }

    @NonNull
    public abstract String b();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<GetStylesResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<GetStylesResponse> initializeCall() {
        return getLoginService(true).a(b());
    }
}
