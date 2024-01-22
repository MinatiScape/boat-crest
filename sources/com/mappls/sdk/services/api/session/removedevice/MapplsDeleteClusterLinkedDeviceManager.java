package com.mappls.sdk.services.api.session.removedevice;

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
/* loaded from: classes8.dex */
public class MapplsDeleteClusterLinkedDeviceManager {
    private MapplsDeleteClusterLinkedDevice mapplsDeleteClusterLinkedDevice;

    /* loaded from: classes8.dex */
    public class a implements Callback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13278a;

        public a(MapplsDeleteClusterLinkedDeviceManager mapplsDeleteClusterLinkedDeviceManager, OnResponseCallback onResponseCallback) {
            this.f13278a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Void> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13278a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13278a.onError(1, th.getMessage());
            } else {
                this.f13278a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.code() == 200) {
                this.f13278a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13278a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13278a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13278a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13278a.onError(response.code(), response.message());
                }
            } else {
                this.f13278a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsDeleteClusterLinkedDeviceManager(MapplsDeleteClusterLinkedDevice mapplsDeleteClusterLinkedDevice) {
        this.mapplsDeleteClusterLinkedDevice = mapplsDeleteClusterLinkedDevice;
    }

    public static MapplsDeleteClusterLinkedDeviceManager newInstance(MapplsDeleteClusterLinkedDevice mapplsDeleteClusterLinkedDevice) {
        return new MapplsDeleteClusterLinkedDeviceManager(mapplsDeleteClusterLinkedDevice);
    }

    public void call(@NonNull OnResponseCallback<Void> onResponseCallback) {
        this.mapplsDeleteClusterLinkedDevice.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsDeleteClusterLinkedDevice.cancel();
    }

    public Void execute() throws IOException {
        return this.mapplsDeleteClusterLinkedDevice.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsDeleteClusterLinkedDevice.executed();
    }
}
