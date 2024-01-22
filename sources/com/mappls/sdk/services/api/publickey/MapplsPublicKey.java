package com.mappls.sdk.services.api.publickey;

import android.provider.Settings;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.publickey.a;
import com.mappls.sdk.services.api.publickey.model.PublicKeyResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import retrofit2.Call;
@AutoValue
/* loaded from: classes7.dex */
public abstract class MapplsPublicKey extends MapplsService<PublicKeyResponse, b> {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        public abstract MapplsPublicKey a();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsPublicKey build() {
            if (MapplsUtils.getSDKContext() != null) {
                return a();
            }
            throw new ServicesException("You should initialise component using MapplsAccountManager class");
        }
    }

    public MapplsPublicKey() {
        super(b.class);
    }

    public static Builder builder() {
        return new a.b().baseUrl(Constants.OUTPOST_BASE_URL);
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    @NonNull
    public abstract String baseUrl();

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<PublicKeyResponse> initializeCall() {
        return getService(true).a(new PublicKeyRequest(Settings.Secure.getString(MapplsUtils.getSDKContext().getContentResolver(), "android_id"), "SHA256"));
    }
}
