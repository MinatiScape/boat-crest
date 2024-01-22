package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;
/* loaded from: classes2.dex */
public interface InAppListener {
    void inAppNotificationDidClick(CTInAppNotification cTInAppNotification, Bundle bundle, HashMap<String, String> hashMap);

    void inAppNotificationDidDismiss(Context context, CTInAppNotification cTInAppNotification, Bundle bundle);

    void inAppNotificationDidShow(CTInAppNotification cTInAppNotification, Bundle bundle);
}
