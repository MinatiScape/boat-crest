package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitController;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DisplayUnitResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f2673a = new Object();
    public final BaseCallbackManager b;
    public final CleverTapResponse c;
    public final CleverTapInstanceConfig d;
    public final ControllerManager e;
    public final Logger f;

    public DisplayUnitResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.c = cleverTapResponse;
        this.d = cleverTapInstanceConfig;
        this.f = cleverTapInstanceConfig.getLogger();
        this.b = baseCallbackManager;
        this.e = controllerManager;
    }

    public final void a(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() != 0) {
            synchronized (this.f2673a) {
                if (this.e.getCTDisplayUnitController() == null) {
                    this.e.setCTDisplayUnitController(new CTDisplayUnitController());
                }
            }
            this.b.notifyDisplayUnitsLoaded(this.e.getCTDisplayUnitController().updateDisplayUnits(jSONArray));
            return;
        }
        this.f.verbose(this.d.getAccountId(), "DisplayUnit : Can't parse Display Units, jsonArray is either empty or null");
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        this.f.verbose(this.d.getAccountId(), "Processing Display Unit items...");
        if (this.d.isAnalyticsOnly()) {
            this.f.verbose(this.d.getAccountId(), "CleverTap instance is configured to analytics only, not processing Display Unit response");
            this.c.processResponse(jSONObject, str, context);
        } else if (jSONObject == null) {
            this.f.verbose(this.d.getAccountId(), "DisplayUnit : Can't parse Display Unit Response, JSON response object is null");
        } else if (!jSONObject.has(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY)) {
            this.f.verbose(this.d.getAccountId(), "DisplayUnit : JSON object doesn't contain the Display Units key");
            this.c.processResponse(jSONObject, str, context);
        } else {
            try {
                this.f.verbose(this.d.getAccountId(), "DisplayUnit : Processing Display Unit response");
                a(jSONObject.getJSONArray(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY));
            } catch (Throwable th) {
                this.f.verbose(this.d.getAccountId(), "DisplayUnit : Failed to parse response", th);
            }
            this.c.processResponse(jSONObject, str, context);
        }
    }
}
