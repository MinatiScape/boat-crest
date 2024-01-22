package com.mappls.sdk.services.api.event.catmaster;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsService;
import com.mappls.sdk.services.api.ServicesException;
import com.mappls.sdk.services.api.event.catmaster.a;
import com.mappls.sdk.services.api.event.catmaster.model.ReportMasterResponse;
import com.mappls.sdk.services.utils.Constants;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AutoValue
@Keep
/* loaded from: classes2.dex */
public abstract class MapplsCategoryMaster extends MapplsService<ReportMasterResponse, b> {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract MapplsCategoryMaster autoBuild();

        public abstract Builder baseUrl(@NonNull String str);

        public MapplsCategoryMaster build() throws ServicesException {
            if (!MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientId()) && !MapplsUtils.isAccessTokenValid(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                throw new ServicesException("Using Mappls Services requires setting a valid client_id and client_secret.");
            }
            return autoBuild();
        }
    }

    public MapplsCategoryMaster() {
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

    public void enqueue(Callback<ReportMasterResponse> callback) {
        enqueueCall(callback);
    }

    public Response<ReportMasterResponse> execute() throws IOException {
        return super.executeCall();
    }

    public boolean executed() {
        return super.isExecuted();
    }

    @Override // com.mappls.sdk.services.api.MapplsService
    public Call<ReportMasterResponse> initializeCall() {
        return getLoginService(true).getCall();
    }
}
