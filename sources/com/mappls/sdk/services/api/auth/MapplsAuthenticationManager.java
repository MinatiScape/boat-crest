package com.mappls.sdk.services.api.auth;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsAuthenticationManager {
    private MapplsAuthentication mapplsAuthentication;

    /* loaded from: classes11.dex */
    public class a implements Callback<AtlasAuthToken> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13153a;

        public a(MapplsAuthenticationManager mapplsAuthenticationManager, OnResponseCallback onResponseCallback) {
            this.f13153a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AtlasAuthToken> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13153a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13153a.onError(1, th.getMessage());
            } else {
                this.f13153a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AtlasAuthToken> call, Response<AtlasAuthToken> response) {
            if (response.code() == 200) {
                this.f13153a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13153a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13153a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13153a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13153a.onError(response.code(), response.message());
                }
            } else {
                this.f13153a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsAuthenticationManager(MapplsAuthentication mapplsAuthentication) {
        this.mapplsAuthentication = mapplsAuthentication;
    }

    public static MapplsAuthenticationManager newInstance(MapplsAuthentication mapplsAuthentication) {
        return new MapplsAuthenticationManager(mapplsAuthentication);
    }

    public void call(OnResponseCallback<AtlasAuthToken> onResponseCallback) {
        this.mapplsAuthentication.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsAuthentication.cancelCall();
    }

    public AtlasAuthToken execute() throws IOException {
        return this.mapplsAuthentication.executeCall().body();
    }

    public boolean isExecuted() {
        return this.mapplsAuthentication.isExecuted();
    }
}
