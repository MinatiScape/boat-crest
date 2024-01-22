package com.clevertap.android.sdk.response;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.mappls.sdk.services.api.nearby.NearbyCriteria;
import java.util.concurrent.Callable;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InAppResponse extends com.clevertap.android.sdk.response.a {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapResponse f2677a;
    public final CleverTapInstanceConfig b;
    public final ControllerManager c;
    public final boolean d;
    public final Logger e;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ Context h;

        public a(Context context) {
            this.h = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            InAppResponse.this.c.getInAppController().showNotificationIfAvailable(this.h);
            return null;
        }
    }

    public InAppResponse(CleverTapResponse cleverTapResponse, CleverTapInstanceConfig cleverTapInstanceConfig, ControllerManager controllerManager, boolean z) {
        this.f2677a = cleverTapResponse;
        this.b = cleverTapInstanceConfig;
        this.e = cleverTapInstanceConfig.getLogger();
        this.c = controllerManager;
        this.d = z;
    }

    @Override // com.clevertap.android.sdk.response.a, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
        } catch (Throwable th) {
            Logger.v("InAppManager: Failed to parse response", th);
        }
        if (this.b.isAnalyticsOnly()) {
            this.e.verbose(this.b.getAccountId(), "CleverTap instance is configured to analytics only, not processing inapp messages");
            this.f2677a.processResponse(jSONObject, str, context);
            return;
        }
        this.e.verbose(this.b.getAccountId(), "InApp: Processing response");
        if (!jSONObject.has(Constants.INAPP_JSON_RESPONSE_KEY)) {
            this.e.verbose(this.b.getAccountId(), "InApp: Response JSON object doesn't contain the inapp key, failing");
            this.f2677a.processResponse(jSONObject, str, context);
            return;
        }
        int i = 10;
        int i2 = (jSONObject.has(Constants.INAPP_MAX_PER_SESSION) && (jSONObject.get(Constants.INAPP_MAX_PER_SESSION) instanceof Integer)) ? jSONObject.getInt(Constants.INAPP_MAX_PER_SESSION) : 10;
        if (jSONObject.has(NearbyCriteria.IMPORTANCE) && (jSONObject.get(NearbyCriteria.IMPORTANCE) instanceof Integer)) {
            i = jSONObject.getInt(NearbyCriteria.IMPORTANCE);
        }
        if (!this.d && this.c.getInAppFCManager() != null) {
            Logger.v("Updating InAppFC Limits");
            this.c.getInAppFCManager().updateLimits(context, i, i2);
            this.c.getInAppFCManager().processResponse(context, jSONObject);
        } else {
            this.e.verbose(this.b.getAccountId(), "controllerManager.getInAppFCManager() is NULL, not Updating InAppFC Limits");
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.INAPP_JSON_RESPONSE_KEY);
            SharedPreferences.Editor edit = StorageHelper.getPreferences(context).edit();
            JSONArray jSONArray2 = new JSONArray(StorageHelper.getStringFromPrefs(context, this.b, Constants.INAPP_KEY, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    try {
                        jSONArray2.put(jSONArray.getJSONObject(i3));
                    } catch (JSONException unused) {
                        Logger.v("InAppManager: Malformed inapp notification");
                    }
                }
            }
            edit.putString(StorageHelper.storageKeyWithSuffix(this.b, Constants.INAPP_KEY), jSONArray2.toString());
            StorageHelper.persist(edit);
            CTExecutorFactory.executors(this.b).postAsyncSafelyTask(Constants.TAG_FEATURE_IN_APPS).execute("InAppResponse#processResponse", new a(context));
            this.f2677a.processResponse(jSONObject, str, context);
        } catch (JSONException unused2) {
            this.e.debug(this.b.getAccountId(), "InApp: In-app key didn't contain a valid JSON array");
            this.f2677a.processResponse(jSONObject, str, context);
        }
    }
}
