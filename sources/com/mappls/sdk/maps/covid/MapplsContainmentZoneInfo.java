package com.mappls.sdk.maps.covid;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.maps.covid.b;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.ApiCallHelper;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsContainmentZoneInfo extends MapplsService<ZoneInfo, e> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f12697a;
        public Double b;

        public abstract MapplsContainmentZoneInfo a();

        public abstract Builder b(@NonNull Double d);

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsContainmentZoneInfo build() throws ServicesException {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getRestAPIKey())) {
                if (this.f12697a.doubleValue() > 0.0d && this.b.doubleValue() > 0.0d) {
                    b(this.f12697a);
                    c(this.b);
                    return a();
                }
                throw new ServicesException("Please pass valid latitude longitude");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public abstract Builder c(@NonNull Double d);

        public abstract Builder distance(@NonNull Integer num);

        public abstract Builder keywords(@NonNull String str);

        public Builder location(@NonNull Double d, @NonNull Double d2) {
            this.f12697a = d;
            this.b = d2;
            return this;
        }

        public abstract Builder range(@NonNull Integer num);
    }

    public MapplsContainmentZoneInfo() {
        super(e.class);
    }

    public static Builder a() {
        return new b.C0628b().baseUrl(Constants.ATLAS_BASE_URL);
    }

    @NonNull
    public abstract Integer b();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    public List<ZoneInfoRequestData> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZoneInfoRequestData(latitude(), longitude()));
        return arrayList;
    }

    @NonNull
    public abstract String e();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<ZoneInfo> callback) {
        super.enqueueCall(callback);
    }

    @NonNull
    public abstract Integer f();

    @Override // com.mappls.sdk.services.api.MapplsService
    public GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZoneInfo.class, new ContainmentZoneDeserializer()).create();
        return gsonBuilder;
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<ZoneInfo> initializeCall() {
        return getLoginService(true).a(ApiCallHelper.getHeaderUserAgent(null), e(), b(), f(), d());
    }

    @NonNull
    public abstract Double latitude();

    @NonNull
    public abstract Double longitude();
}
