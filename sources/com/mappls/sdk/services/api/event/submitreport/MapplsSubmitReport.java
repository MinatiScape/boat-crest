package com.mappls.sdk.services.api.event.submitreport;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.event.submitreport.a;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes6.dex */
public abstract class MapplsSubmitReport extends MapplsService<Void, b> {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13225a;
        public Double b;
        public String c;

        public abstract MapplsSubmitReport a();

        public abstract Builder accuracy(@Nullable Integer num);

        public abstract Builder altitude(@Nullable Double d);

        public abstract Builder appVersion(@Nullable String str);

        public abstract Builder b(@Nullable Double d);

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder bearing(@Nullable Integer num);

        public MapplsSubmitReport build() throws ServicesException {
            if (!MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId()) && !MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                throw new ServicesException("Using Mappls Services requires setting a valid client_id and client_secret.");
            }
            if (this.c == null && this.f13225a == null) {
                throw new ServicesException("Please pass either mappls pin or Latitude and longitude");
            }
            b(this.f13225a);
            c(this.b);
            d(this.c);
            return a();
        }

        public abstract Builder c(@Nullable Double d);

        public abstract Builder childCategory(@NonNull Integer num);

        public abstract Builder d(@Nullable String str);

        public abstract Builder description(@Nullable String str);

        public abstract Builder deviceName(@Nullable String str);

        public abstract Builder expiry(@Nullable Long l);

        public abstract Builder flag(@Nullable Integer num);

        public Builder latitude(@Nullable Double d) {
            this.f13225a = d;
            return this;
        }

        public Builder longitude(@Nullable Double d) {
            this.b = d;
            return this;
        }

        public Builder mapplsPin(@Nullable String str) {
            this.c = str;
            return this;
        }

        public abstract Builder osVersion(@Nullable String str);

        public abstract Builder parentCategory(@NonNull Integer num);

        public abstract Builder placeName(@NonNull String str);

        public abstract Builder quality(@Nullable String str);

        public abstract Builder speed(@Nullable String str);

        public abstract Builder subChildCategory(@Nullable Integer num);

        public abstract Builder utc(@Nullable Long l);

        public abstract Builder zeroId(@Nullable String str);
    }

    public MapplsSubmitReport() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.EXPLORE_BASE_URL);
    }

    private Map<String, Object> createRequest() {
        HashMap hashMap = new HashMap();
        hashMap.put("placeName", placeName());
        hashMap.put("eloc", internalMapplsPin());
        hashMap.put("latitude", internalLatitude());
        hashMap.put("longitude", internalLongitude());
        hashMap.put("parentCategory", parentCategory());
        hashMap.put("childCategory", childCategory());
        hashMap.put(SavingTrackHelper.POINT_COL_DESCRIPTION, description());
        hashMap.put("subChildCategory", subChildCategory());
        hashMap.put("bearing", bearing());
        hashMap.put("accuracy", accuracy());
        hashMap.put("speed", speed());
        hashMap.put("flag", flag());
        hashMap.put("alt", altitude());
        hashMap.put("quality", quality());
        hashMap.put("utc", utc());
        hashMap.put("expiry", expiry());
        hashMap.put("zeroId", zeroId());
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, appVersion());
        hashMap.put("osVersionoptional", osVersion());
        hashMap.put(DeviceKey.DeviceName, deviceName());
        return hashMap;
    }

    @Nullable
    public abstract Integer accuracy();

    @Nullable
    public abstract Double altitude();

    @Nullable
    public abstract String appVersion();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Nullable
    public abstract Integer bearing();

    public void cancel() {
        cancelCall();
    }

    @NonNull
    public abstract Integer childCategory();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract String deviceName();

    public void enqueue(Callback<Void> callback) {
        enqueueCall(callback);
    }

    public Response<Void> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Nullable
    public abstract Long expiry();

    @Nullable
    public abstract Integer flag();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<Void> initializeCall() {
        return getLoginService(true).getCall(createRequest());
    }

    @Nullable
    public abstract Double internalLatitude();

    @Nullable
    public abstract Double internalLongitude();

    @Nullable
    public abstract String internalMapplsPin();

    @Nullable
    public abstract String osVersion();

    @NonNull
    public abstract Integer parentCategory();

    @NonNull
    public abstract String placeName();

    @Nullable
    public abstract String quality();

    @Nullable
    public abstract String speed();

    @Nullable
    public abstract Integer subChildCategory();

    @Nullable
    public abstract Long utc();

    @Nullable
    public abstract String zeroId();
}
