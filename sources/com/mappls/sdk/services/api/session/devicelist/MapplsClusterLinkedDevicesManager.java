package com.mappls.sdk.services.api.session.devicelist;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.session.devicelist.model.Device;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes8.dex */
public class MapplsClusterLinkedDevicesManager {
    private final MapplsClusterLinkedDevices mapplsClusterLinkedDevices;

    /* loaded from: classes8.dex */
    public class a implements Callback<List<Device>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13272a;

        public a(MapplsClusterLinkedDevicesManager mapplsClusterLinkedDevicesManager, OnResponseCallback onResponseCallback) {
            this.f13272a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<List<Device>> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13272a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13272a.onError(1, th.getMessage());
            } else {
                this.f13272a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
            if (response.code() == 200) {
                this.f13272a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13272a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13272a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13272a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13272a.onError(response.code(), response.message());
                }
            } else {
                this.f13272a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsClusterLinkedDevicesManager(MapplsClusterLinkedDevices mapplsClusterLinkedDevices) {
        this.mapplsClusterLinkedDevices = mapplsClusterLinkedDevices;
    }

    public static MapplsClusterLinkedDevicesManager newInstance(MapplsClusterLinkedDevices mapplsClusterLinkedDevices) {
        return new MapplsClusterLinkedDevicesManager(mapplsClusterLinkedDevices);
    }

    public void call(@NonNull OnResponseCallback<List<Device>> onResponseCallback) {
        this.mapplsClusterLinkedDevices.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsClusterLinkedDevices.cancel();
    }

    public List<Device> execute() throws IOException {
        return this.mapplsClusterLinkedDevices.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsClusterLinkedDevices.executed();
    }
}
