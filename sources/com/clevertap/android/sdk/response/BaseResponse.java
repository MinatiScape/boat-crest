package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.NetworkManager;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BaseResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2671a;
    public final CleverTapInstanceConfig b;
    public final LocalDataStore c;
    public final Logger d;
    public final NetworkManager e;

    public BaseResponse(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, NetworkManager networkManager, LocalDataStore localDataStore, CleverTapResponse cleverTapResponse) {
        this.f2671a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.d = cleverTapInstanceConfig.getLogger();
        this.e = networkManager;
        this.c = localDataStore;
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        if (str == null) {
            this.d.verbose(this.b.getAccountId(), "Problem processing queue response, response is null");
            return;
        }
        try {
            Logger logger = this.d;
            String accountId = this.b.getAccountId();
            logger.verbose(accountId, "Trying to process response: " + str);
            JSONObject jSONObject2 = new JSONObject(str);
            this.f2671a.processResponse(jSONObject2, str, context);
            this.c.syncWithUpstream(context, jSONObject2);
        } catch (Throwable th) {
            this.e.incrementResponseFailureCount();
            this.d.verbose(this.b.getAccountId(), "Problem process send queue response", th);
        }
    }
}
