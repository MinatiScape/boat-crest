package com.mappls.sdk.services.api.generateotp;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes6.dex */
public class MapplsGenerateOtpManager {
    private final MapplsGenerateOTP mapplsGenerateOTP;

    /* loaded from: classes6.dex */
    public class a implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13235a;

        public a(MapplsGenerateOtpManager mapplsGenerateOtpManager, OnResponseCallback onResponseCallback) {
            this.f13235a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13235a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13235a.onError(1, th.getMessage());
            } else {
                this.f13235a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.code() == 201) {
                this.f13235a.onSuccess(response.headers().get(HttpHeaders.LOCATION));
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13235a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13235a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13235a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13235a.onError(response.code(), response.message());
                }
            } else {
                this.f13235a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsGenerateOtpManager(MapplsGenerateOTP mapplsGenerateOTP) {
        this.mapplsGenerateOTP = mapplsGenerateOTP;
    }

    public static MapplsGenerateOtpManager newInstance(MapplsGenerateOTP mapplsGenerateOTP) {
        return new MapplsGenerateOtpManager(mapplsGenerateOTP);
    }

    public void call(@NonNull OnResponseCallback<String> onResponseCallback) {
        this.mapplsGenerateOTP.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsGenerateOTP.cancel();
    }

    public String execute() throws IOException {
        Response<Void> execute = this.mapplsGenerateOTP.execute();
        if (execute.code() == 201) {
            return execute.headers().get(HttpHeaders.LOCATION);
        }
        return null;
    }

    public boolean isExecuted() {
        return this.mapplsGenerateOTP.executed();
    }
}
