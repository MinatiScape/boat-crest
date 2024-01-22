package com.mappls.sdk.services.api.feedback;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
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
public class MapplsFeedbackManager {
    private MapplsFeedback mapplsFeedback;

    /* loaded from: classes6.dex */
    public class a implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13229a;

        public a(MapplsFeedbackManager mapplsFeedbackManager, OnResponseCallback onResponseCallback) {
            this.f13229a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13229a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13229a.onError(1, th.getMessage());
            } else {
                this.f13229a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.code() == 201) {
                this.f13229a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13229a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13229a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13229a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13229a.onError(response.code(), response.message());
                }
            } else {
                this.f13229a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsFeedbackManager(MapplsFeedback mapplsFeedback) {
        this.mapplsFeedback = mapplsFeedback;
    }

    public static MapplsFeedbackManager newInstance(MapplsFeedback mapplsFeedback) {
        return new MapplsFeedbackManager(mapplsFeedback);
    }

    public void call(@NonNull OnResponseCallback<Void> onResponseCallback) {
        this.mapplsFeedback.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsFeedback.cancel();
    }

    public Void execute() throws IOException {
        return this.mapplsFeedback.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsFeedback.executed();
    }
}