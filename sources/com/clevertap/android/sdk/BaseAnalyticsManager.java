package com.clevertap.android.sdk;

import android.os.Bundle;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public abstract class BaseAnalyticsManager {
    public abstract void addMultiValuesForKey(String str, ArrayList<String> arrayList);

    public abstract void decrementValue(String str, Number number);

    public abstract void fetchFeatureFlags();

    public abstract void forcePushAppLaunchedEvent();

    public abstract void incrementValue(String str, Number number);

    public abstract void pushAppLaunchedEvent();

    public abstract void pushDefineVarsEvent(JSONObject jSONObject);

    public abstract void pushDisplayUnitClickedEventForID(String str);

    public abstract void pushDisplayUnitViewedEventForID(String str);

    public abstract void pushError(String str, int i);

    public abstract void pushEvent(String str, Map<String, Object> map);

    public abstract void pushInAppNotificationStateEvent(boolean z, CTInAppNotification cTInAppNotification, Bundle bundle);

    public abstract void pushInstallReferrer(String str);

    public abstract void pushInstallReferrer(String str, String str2, String str3);

    public abstract void pushNotificationClickedEvent(Bundle bundle);

    public abstract void pushNotificationViewedEvent(Bundle bundle);

    public abstract void pushProfile(Map<String, Object> map);

    public abstract void removeMultiValuesForKey(String str, ArrayList<String> arrayList);

    public abstract void removeValueForKey(String str);

    public abstract void sendDataEvent(JSONObject jSONObject);

    public abstract void sendFetchEvent(JSONObject jSONObject);
}
