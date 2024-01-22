package com.mappls.sdk.services.api.whitelist;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes8.dex */
public class MapplsWhitelistManager {
    private final MapplsWhitelist mapplsWhitelist;

    /* loaded from: classes8.dex */
    public class a implements Callback<AtlasAuthToken> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13303a;

        public a(MapplsWhitelistManager mapplsWhitelistManager, OnResponseCallback onResponseCallback) {
            this.f13303a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AtlasAuthToken> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13303a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13303a.onError(1, th.getMessage());
            } else {
                this.f13303a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AtlasAuthToken> call, Response<AtlasAuthToken> response) {
            if (response.code() == 200) {
                AtlasAuthToken body = response.body();
                if (body != null) {
                    if (MapplsLMSManager.isInitialised()) {
                        MapplsLMSManager.getInstance().setUserId(body.userId);
                    }
                    MapplsAccountManager.getInstance().setRefreshToken(body.refreshToken, body.accessToken);
                    MapplsAccountManager.getInstance().setUserId(body.userId);
                    MapplsApiConfiguration.getInstance().getTokenRepo().setToken(body.refreshToken);
                    this.f13303a.onSuccess(null);
                    return;
                }
                this.f13303a.onError(response.code(), "");
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13303a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13303a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13303a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13303a.onError(response.code(), response.message());
                }
            } else {
                this.f13303a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsWhitelistManager(MapplsWhitelist mapplsWhitelist) {
        this.mapplsWhitelist = mapplsWhitelist;
    }

    public static MapplsWhitelistManager newInstance(MapplsWhitelist mapplsWhitelist) {
        return new MapplsWhitelistManager(mapplsWhitelist);
    }

    public void call(@NonNull OnResponseCallback<Void> onResponseCallback) {
        this.mapplsWhitelist.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsWhitelist.cancel();
    }

    public boolean isExecuted() {
        return this.mapplsWhitelist.executed();
    }
}
