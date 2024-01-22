package com.mappls.sdk.services.api.autosuggest;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsAutosuggestManager {
    private MapplsAutoSuggest mapplsAutoSuggest;

    /* loaded from: classes11.dex */
    public class a implements Callback<AutoSuggestAtlasResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13156a;

        public a(MapplsAutosuggestManager mapplsAutosuggestManager, OnResponseCallback onResponseCallback) {
            this.f13156a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AutoSuggestAtlasResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13156a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13156a.onError(1, th.getMessage());
            } else {
                this.f13156a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AutoSuggestAtlasResponse> call, Response<AutoSuggestAtlasResponse> response) {
            if (response.code() == 200) {
                this.f13156a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13156a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13156a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13156a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13156a.onError(response.code(), response.message());
                }
            } else {
                this.f13156a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsAutosuggestManager(MapplsAutoSuggest mapplsAutoSuggest) {
        this.mapplsAutoSuggest = mapplsAutoSuggest;
    }

    public static MapplsAutosuggestManager newInstance(MapplsAutoSuggest mapplsAutoSuggest) {
        return new MapplsAutosuggestManager(mapplsAutoSuggest);
    }

    public void call(@NonNull OnResponseCallback<AutoSuggestAtlasResponse> onResponseCallback) {
        this.mapplsAutoSuggest.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsAutoSuggest.cancel();
    }

    public AutoSuggestAtlasResponse execute() throws IOException {
        return this.mapplsAutoSuggest.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsAutoSuggest.executed();
    }
}