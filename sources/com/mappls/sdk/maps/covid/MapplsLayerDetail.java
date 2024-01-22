package com.mappls.sdk.maps.covid;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.covid.c;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.ResponseBody;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsLayerDetail extends MapplsService<ResponseBody, h> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract MapplsLayerDetail a();

        public abstract Builder baseUrl(@NonNull String str);

        public abstract Builder buffer(@NonNull Integer num);

        public MapplsLayerDetail build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getRestAPIKey())) {
                return a();
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public abstract Builder clickedPoint(@NonNull PointF pointF);

        public abstract Builder height(@NonNull Integer num);

        public abstract Builder isStyle(@NonNull Boolean bool);

        public abstract Builder layerType(@NonNull String str);

        public abstract Builder visibleRegion(@NonNull LatLngBounds latLngBounds);

        public abstract Builder width(@NonNull Integer num);
    }

    public MapplsLayerDetail() {
        super(h.class);
    }

    public static Builder a() {
        return new c.b().baseUrl(Constants.MGIS_BASE_URL).isStyle(Boolean.FALSE).buffer(30);
    }

    @NonNull
    public abstract PointF b();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @NonNull
    public abstract Integer buffer();

    public HashMap<String, String> createRequest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", MapplsAccountManager.getInstance().getRestAPIKey());
        hashMap.put("request", "GetFeatureInfo");
        hashMap.put(NotificationCompat.CATEGORY_SERVICE, "WMS");
        hashMap.put("srs", "EPSG:4326");
        hashMap.put("transparent", "true");
        hashMap.put("format", "image/png");
        hashMap.put("bbox", e());
        hashMap.put("layers", "covid:" + h());
        hashMap.put("QUERY_LAYERS", "covid:" + h());
        if (g().booleanValue()) {
            hashMap.put("styles", "covid:" + h());
        }
        hashMap.put("buffer", buffer() + "");
        hashMap.put("crossDomain", "true");
        hashMap.put("info_format", "application/json");
        hashMap.put("x", ((int) b().x) + "");
        hashMap.put(EllipticCurveJsonWebKey.Y_MEMBER_NAME, ((int) b().y) + "");
        hashMap.put(Property.ICON_TEXT_FIT_HEIGHT, f() + "");
        hashMap.put(Property.ICON_TEXT_FIT_WIDTH, j() + "");
        return hashMap;
    }

    public Observable<ResponseBody> d() {
        return getLoginService(true).getCall(createRequest());
    }

    public final String e() {
        return i().getSouthWest().getLongitude() + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + i().getSouthWest().getLatitude() + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + i().getNorthEast().getLongitude() + com.clevertap.android.sdk.Constants.SEPARATOR_COMMA + i().getNorthEast().getLatitude();
    }

    @NonNull
    public abstract Integer f();

    @NonNull
    public abstract Boolean g();

    @Override // com.mappls.sdk.services.api.MapplsService
    public List<CallAdapter.Factory> getCallAdapterFactory() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(RxJavaCallAdapterFactory.create());
        return arrayList;
    }

    @NonNull
    public abstract String h();

    @NonNull
    public abstract LatLngBounds i();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<ResponseBody> initializeCall() {
        return null;
    }

    @NonNull
    public abstract Integer j();
}
