package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class FetchVariablesResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2675a;
    public final CleverTapInstanceConfig b;
    public final ControllerManager c;
    public final BaseCallbackManager d;

    public FetchVariablesResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager, BaseCallbackManager baseCallbackManager) {
        this.f2675a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.c = controllerManager;
        this.d = baseCallbackManager;
    }

    public final void a(String str) {
        Logger.d("variables", str);
    }

    public final void b(String str) {
        Logger.d("variables", str);
    }

    public final void c(String str, Throwable th) {
        Logger.i("variables", str, th);
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        CleverTapResponse cleverTapResponse;
        b("Processing Variable response...");
        a("processResponse() called with: response = [" + jSONObject + "], stringBody = [" + str + "], context = [" + context + "]");
        if (this.b.isAnalyticsOnly()) {
            b("CleverTap instance is configured to analytics only, not processing Variable response");
        } else if (jSONObject == null) {
            b("Can't parse Variable Response, JSON response object is null");
        } else if (!jSONObject.has("vars")) {
            b("JSON object doesn't contain the vars key");
        } else {
            try {
                b("Processing Request Variables response");
                JSONObject jSONObject2 = jSONObject.getJSONObject("vars");
                if (this.c.getCtVariables() != null) {
                    this.c.getCtVariables().handleVariableResponse(jSONObject2, this.d.getFetchVariablesCallback());
                    this.d.setFetchVariablesCallback(null);
                } else {
                    b("Can't parse Variable Response, CTVariables is null");
                }
            } finally {
                try {
                } finally {
                }
            }
        }
    }
}
