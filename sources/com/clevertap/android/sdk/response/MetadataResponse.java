package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.NetworkManager;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MetadataResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2679a;
    public final CleverTapInstanceConfig b;
    public final DeviceInfo c;
    public final Logger d;
    public final NetworkManager e;

    public MetadataResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, NetworkManager networkManager) {
        this.f2679a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.d = cleverTapInstanceConfig.getLogger();
        this.c = deviceInfo;
        this.e = networkManager;
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
            if (jSONObject.has("g")) {
                String string = jSONObject.getString("g");
                this.c.forceUpdateDeviceId(string);
                Logger logger = this.d;
                String accountId = this.b.getAccountId();
                logger.verbose(accountId, "Got a new device ID: " + string);
            }
        } catch (Throwable th) {
            this.d.verbose(this.b.getAccountId(), "Failed to update device ID!", th);
        }
        try {
            if (jSONObject.has("_i")) {
                this.e.setI(context, jSONObject.getLong("_i"));
            }
        } catch (Throwable unused) {
        }
        try {
            if (jSONObject.has("_j")) {
                this.e.setJ(context, jSONObject.getLong("_j"));
            }
        } catch (Throwable unused2) {
        }
        this.f2679a.processResponse(jSONObject, str, context);
    }
}
