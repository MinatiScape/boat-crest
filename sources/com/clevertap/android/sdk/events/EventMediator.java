package com.clevertap.android.sdk.events;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class EventMediator {

    /* renamed from: a  reason: collision with root package name */
    public final CoreMetaData f2609a;
    public final CleverTapInstanceConfig b;
    public final Context c;

    public EventMediator(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData) {
        this.c = context;
        this.b = cleverTapInstanceConfig;
        this.f2609a = coreMetaData;
    }

    public final boolean a() {
        return ((int) (System.currentTimeMillis() / 1000)) - StorageHelper.getIntFromPrefs(this.c, this.b, Constants.KEY_MUTED, 0) < 86400;
    }

    public boolean shouldDeferProcessingEvent(JSONObject jSONObject, int i) {
        if (i == 8 || this.b.isCreatedPostAppLaunch()) {
            return false;
        }
        if (jSONObject.has("evtName")) {
            try {
                if (Arrays.asList(Constants.SYSTEM_EVENTS).contains(jSONObject.getString("evtName"))) {
                    return false;
                }
            } catch (JSONException unused) {
            }
        }
        return i == 4 && !this.f2609a.isAppLaunchPushed();
    }

    public boolean shouldDropEvent(JSONObject jSONObject, int i) {
        if (i != 7 && i != 8) {
            if (this.f2609a.isCurrentUserOptedOut()) {
                String jSONObject2 = jSONObject == null ? "null" : jSONObject.toString();
                Logger logger = this.b.getLogger();
                String accountId = this.b.getAccountId();
                logger.debug(accountId, "Current user is opted out dropping event: " + jSONObject2);
                return true;
            } else if (a()) {
                Logger logger2 = this.b.getLogger();
                String accountId2 = this.b.getAccountId();
                logger2.verbose(accountId2, "CleverTap is muted, dropping event - " + jSONObject.toString());
                return true;
            }
        }
        return false;
    }
}
