package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class FeatureFlagResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2674a;
    public final CleverTapInstanceConfig b;
    public final Logger c;
    public final ControllerManager d;

    public FeatureFlagResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager) {
        this.f2674a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.c = cleverTapInstanceConfig.getLogger();
        this.d = controllerManager;
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getJSONArray(Constants.KEY_KV) != null && this.d.getCTFeatureFlagsController() != null) {
            this.d.getCTFeatureFlagsController().updateFeatureFlags(jSONObject);
        } else {
            this.b.getLogger().verbose(this.b.getAccountId(), "Feature Flag : Can't parse feature flags, CTFeatureFlagsController is null");
        }
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.c.verbose(this.b.getAccountId(), "Processing Feature Flags response...");
        if (this.b.isAnalyticsOnly()) {
            this.c.verbose(this.b.getAccountId(), "CleverTap instance is configured to analytics only, not processing Feature Flags response");
            this.f2674a.processResponse(jSONObject, str, context);
        } else if (jSONObject == null) {
            this.c.verbose(this.b.getAccountId(), "Feature Flag : Can't parse Feature Flags Response, JSON response object is null");
        } else if (!jSONObject.has(Constants.FEATURE_FLAG_JSON_RESPONSE_KEY)) {
            this.c.verbose(this.b.getAccountId(), "Feature Flag : JSON object doesn't contain the Feature Flags key");
            this.f2674a.processResponse(jSONObject, str, context);
        } else {
            try {
                this.c.verbose(this.b.getAccountId(), "Feature Flag : Processing Feature Flags response");
                a(jSONObject.getJSONObject(Constants.FEATURE_FLAG_JSON_RESPONSE_KEY));
            } catch (Throwable th) {
                this.c.verbose(this.b.getAccountId(), "Feature Flag : Failed to parse response", th);
            }
            this.f2674a.processResponse(jSONObject, str, context);
        }
    }
}
