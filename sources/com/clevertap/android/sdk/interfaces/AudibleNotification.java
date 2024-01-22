package com.clevertap.android.sdk.interfaces;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
/* loaded from: classes2.dex */
public interface AudibleNotification {
    NotificationCompat.Builder setSound(Context context, Bundle bundle, NotificationCompat.Builder builder, CleverTapInstanceConfig cleverTapInstanceConfig);
}
