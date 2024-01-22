package com.clevertap.android.sdk.interfaces;

import android.content.Context;
import android.os.Bundle;
/* loaded from: classes2.dex */
public interface NotificationHandler {
    boolean onMessageReceived(Context context, Bundle bundle, String str);

    boolean onNewToken(Context context, String str, String str2);
}
