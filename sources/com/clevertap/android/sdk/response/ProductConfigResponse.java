package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.Logger;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ProductConfigResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2680a;
    public final CleverTapInstanceConfig b;
    public final CoreMetaData c;
    public final Logger d;
    public final ControllerManager e;

    public ProductConfigResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, ControllerManager controllerManager) {
        this.f2680a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.d = cleverTapInstanceConfig.getLogger();
        this.c = coreMetaData;
        this.e = controllerManager;
    }

    public final void a() {
        if (this.c.isProductConfigRequested()) {
            if (this.e.getCTProductConfigController() != null) {
                this.e.getCTProductConfigController().onFetchFailed();
            }
            this.c.setProductConfigRequested(false);
        }
    }

    public final void b(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getJSONArray(Constants.KEY_KV) != null && this.e.getCTProductConfigController() != null) {
            this.e.getCTProductConfigController().onFetchSuccess(jSONObject);
        } else {
            a();
        }
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.d.verbose(this.b.getAccountId(), "Processing Product Config response...");
        if (this.b.isAnalyticsOnly()) {
            this.d.verbose(this.b.getAccountId(), "CleverTap instance is configured to analytics only, not processing Product Config response");
            this.f2680a.processResponse(jSONObject, str, context);
        } else if (jSONObject == null) {
            this.d.verbose(this.b.getAccountId(), "Product Config : Can't parse Product Config Response, JSON response object is null");
            a();
        } else if (!jSONObject.has(Constants.REMOTE_CONFIG_FLAG_JSON_RESPONSE_KEY)) {
            this.d.verbose(this.b.getAccountId(), "Product Config : JSON object doesn't contain the Product Config key");
            a();
            this.f2680a.processResponse(jSONObject, str, context);
        } else {
            try {
                this.d.verbose(this.b.getAccountId(), "Product Config : Processing Product Config response");
                b(jSONObject.getJSONObject(Constants.REMOTE_CONFIG_FLAG_JSON_RESPONSE_KEY));
            } catch (Throwable th) {
                a();
                this.d.verbose(this.b.getAccountId(), "Product Config : Failed to parse Product Config response", th);
            }
            this.f2680a.processResponse(jSONObject, str, context);
        }
    }
}
