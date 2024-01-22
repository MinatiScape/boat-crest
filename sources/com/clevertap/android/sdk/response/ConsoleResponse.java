package com.clevertap.android.sdk.response;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ConsoleResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2672a;
    public final CleverTapInstanceConfig b;
    public final Logger c;

    public ConsoleResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.f2672a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.c = cleverTapInstanceConfig.getLogger();
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        int i;
        try {
            if (jSONObject.has("console")) {
                JSONArray jSONArray = (JSONArray) jSONObject.get("console");
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        this.c.debug(this.b.getAccountId(), jSONArray.get(i2).toString());
                    }
                }
            }
        } catch (Throwable unused) {
        }
        try {
            if (jSONObject.has("dbg_lvl") && (i = jSONObject.getInt("dbg_lvl")) >= 0) {
                CleverTapAPI.setDebugLevel(i);
                Logger logger = this.c;
                String accountId = this.b.getAccountId();
                logger.verbose(accountId, "Set debug level to " + i + " for this session (set by upstream)");
            }
        } catch (Throwable unused2) {
        }
        this.f2672a.processResponse(jSONObject, str, context);
    }
}
