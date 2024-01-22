package com.clevertap.android.sdk.response;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InboxResponse extends a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f2678a;
    public final BaseCallbackManager b;
    public final CleverTapResponse c;
    public final CleverTapInstanceConfig d;
    public final Logger e;
    public final ControllerManager f;

    public InboxResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, ControllerManager controllerManager) {
        this.c = cleverTapResponse;
        this.d = cleverTapInstanceConfig;
        this.b = baseCallbackManager;
        this.e = cleverTapInstanceConfig.getLogger();
        this.f2678a = cTLockManager.getInboxControllerLock();
        this.f = controllerManager;
    }

    @WorkerThread
    public final void a(JSONArray jSONArray) {
        synchronized (this.f2678a) {
            if (this.f.getCTInboxController() == null) {
                this.f.initializeInbox();
            }
            if (this.f.getCTInboxController() != null && this.f.getCTInboxController().updateMessages(jSONArray)) {
                this.b._notifyInboxMessagesDidUpdate();
            }
        }
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    @WorkerThread
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        if (this.d.isAnalyticsOnly()) {
            this.e.verbose(this.d.getAccountId(), "CleverTap instance is configured to analytics only, not processing inbox messages");
            this.c.processResponse(jSONObject, str, context);
            return;
        }
        this.e.verbose(this.d.getAccountId(), "Inbox: Processing response");
        if (!jSONObject.has(Constants.INBOX_JSON_RESPONSE_KEY)) {
            this.e.verbose(this.d.getAccountId(), "Inbox: Response JSON object doesn't contain the inbox key");
            this.c.processResponse(jSONObject, str, context);
            return;
        }
        try {
            a(jSONObject.getJSONArray(Constants.INBOX_JSON_RESPONSE_KEY));
        } catch (Throwable th) {
            this.e.verbose(this.d.getAccountId(), "InboxResponse: Failed to parse response", th);
        }
        this.c.processResponse(jSONObject, str, context);
    }
}
