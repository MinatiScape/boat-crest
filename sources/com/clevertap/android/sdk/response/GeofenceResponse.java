package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class GeofenceResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final BaseCallbackManager f2676a;
    public final CleverTapResponse b;
    public final CleverTapInstanceConfig c;
    public final Logger d;

    public GeofenceResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager) {
        this.b = cleverTapResponse;
        this.c = cleverTapInstanceConfig;
        this.d = cleverTapInstanceConfig.getLogger();
        this.f2676a = baseCallbackManager;
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.d.verbose(this.c.getAccountId(), "Processing GeoFences response...");
        if (this.c.isAnalyticsOnly()) {
            this.d.verbose(this.c.getAccountId(), "CleverTap instance is configured to analytics only, not processing geofence response");
            this.b.processResponse(jSONObject, str, context);
        } else if (jSONObject == null) {
            this.d.verbose(this.c.getAccountId(), "Geofences : Can't parse Geofences Response, JSON response object is null");
        } else if (!jSONObject.has(Constants.GEOFENCES_JSON_RESPONSE_KEY)) {
            this.d.verbose(this.c.getAccountId(), "Geofences : JSON object doesn't contain the Geofences key");
            this.b.processResponse(jSONObject, str, context);
        } else {
            try {
                if (this.f2676a.getGeofenceCallback() != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(Constants.GEOFENCES_JSON_RESPONSE_KEY, jSONObject.getJSONArray(Constants.GEOFENCES_JSON_RESPONSE_KEY));
                    this.d.verbose(this.c.getAccountId(), "Geofences : Processing Geofences response");
                    this.f2676a.getGeofenceCallback().handleGeoFences(jSONObject2);
                } else {
                    this.d.debug(this.c.getAccountId(), "Geofences : Geofence SDK has not been initialized to handle the response");
                }
            } catch (Throwable th) {
                this.d.verbose(this.c.getAccountId(), "Geofences : Failed to handle Geofences response", th);
            }
            this.b.processResponse(jSONObject, str, context);
        }
    }
}
