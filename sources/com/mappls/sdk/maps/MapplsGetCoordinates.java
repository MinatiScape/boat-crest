package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.e;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
@AutoValue
/* loaded from: classes11.dex */
public abstract class MapplsGetCoordinates extends MapplsService<CoordinateResponse, h> {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<String> f12642a = new ArrayList();

        public abstract MapplsGetCoordinates a();

        public abstract Builder b(@NonNull String str);

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsGetCoordinates build() {
            if (MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getRestAPIKey())) {
                List<String> list = this.f12642a;
                if (list != null && list.size() != 0) {
                    b(MapplsUtils.join(Constants.SEPARATOR_COMMA, this.f12642a.toArray()));
                    return a();
                }
                throw new ServicesException("At least one mappls id must be provided with your API request.");
            }
            throw new ServicesException("Using Mappls Services requires setting a valid rest API key.");
        }

        public Builder mapplsPin(String str) {
            this.f12642a.add(str);
            return this;
        }

        public Builder mapplsPin(List<String> list) {
            this.f12642a.addAll(list);
            return this;
        }
    }

    public MapplsGetCoordinates() {
        super(h.class);
    }

    public static Builder a() {
        return new e.b().baseUrl(com.mappls.sdk.services.utils.Constants.EXPLORE_BASE_URL);
    }

    @NonNull
    public abstract String b();

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public void enqueueCall(Callback<CoordinateResponse> callback) {
        super.enqueueCall(callback);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<CoordinateResponse> initializeCall() {
        return getService(true).getCall(b());
    }
}
