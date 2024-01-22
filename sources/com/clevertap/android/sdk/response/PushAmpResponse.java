package com.clevertap.android.sdk.response;

import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushNotificationHandler;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PushAmpResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final BaseCallbackManager f2681a;
    public final CleverTapResponse b;
    public final CleverTapInstanceConfig c;
    public final Context d;
    public final Logger e;
    public final ControllerManager f;
    public final BaseDatabaseManager g;

    public PushAmpResponse(CleverTapResponse cleverTapResponse, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, BaseDatabaseManager baseDatabaseManager, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.b = cleverTapResponse;
        this.d = context;
        this.c = cleverTapInstanceConfig;
        this.e = cleverTapInstanceConfig.getLogger();
        this.g = baseDatabaseManager;
        this.f2681a = baseCallbackManager;
        this.f = controllerManager;
    }

    public final void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Bundle bundle = new Bundle();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("wzrk_ttl")) {
                    bundle.putLong("wzrk_ttl", jSONObject.getLong("wzrk_ttl"));
                }
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    bundle.putString(obj, jSONObject.getString(obj));
                }
                if (!bundle.isEmpty() && !this.g.loadDBAdapter(this.d).doesPushNotificationIdExist(jSONObject.getString(Constants.WZRK_PUSH_ID))) {
                    this.e.verbose("Creating Push Notification locally");
                    if (this.f2681a.getPushAmpListener() != null) {
                        this.f2681a.getPushAmpListener().onPushAmpPayloadReceived(bundle);
                    } else {
                        PushNotificationHandler.getPushNotificationHandler().onMessageReceived(this.d, bundle, PushConstants.PushType.FCM.toString());
                    }
                } else {
                    Logger logger = this.e;
                    String accountId = this.c.getAccountId();
                    logger.verbose(accountId, "Push Notification already shown, ignoring local notification :" + jSONObject.getString(Constants.WZRK_PUSH_ID));
                }
            } catch (JSONException unused) {
                this.e.verbose(this.c.getAccountId(), "Error parsing push notification JSON");
                return;
            }
        }
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        if (this.c.isAnalyticsOnly()) {
            this.e.verbose(this.c.getAccountId(), "CleverTap instance is configured to analytics only, not processing push amp response");
            this.b.processResponse(jSONObject, str, context);
            return;
        }
        try {
            if (jSONObject.has("pushamp_notifs")) {
                this.e.verbose(this.c.getAccountId(), "Processing pushamp messages...");
                JSONObject jSONObject2 = jSONObject.getJSONObject("pushamp_notifs");
                JSONArray jSONArray = jSONObject2.getJSONArray("list");
                if (jSONArray.length() > 0) {
                    this.e.verbose(this.c.getAccountId(), "Handling Push payload locally");
                    a(jSONArray);
                }
                if (jSONObject2.has(Constants.PING_FREQUENCY)) {
                    this.f.getPushProviders().updatePingFrequencyIfNeeded(context, jSONObject2.getInt(Constants.PING_FREQUENCY));
                }
                if (jSONObject2.has("ack")) {
                    boolean z = jSONObject2.getBoolean("ack");
                    Logger logger = this.e;
                    logger.verbose("Received ACK -" + z);
                    if (z) {
                        JSONArray renderedTargetList = CTJsonConverter.getRenderedTargetList(this.g.loadDBAdapter(context));
                        String[] strArr = new String[0];
                        if (renderedTargetList != null) {
                            strArr = new String[renderedTargetList.length()];
                        }
                        for (int i = 0; i < strArr.length; i++) {
                            strArr[i] = renderedTargetList.getString(i);
                        }
                        this.e.verbose("Updating RTL values...");
                        this.g.loadDBAdapter(context).updatePushNotificationIds(strArr);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        this.b.processResponse(jSONObject, str, context);
    }
}
