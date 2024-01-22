package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.utils.utility.LogHelper;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RequestNavigationEventReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.REQUEST_NAVIGATION_EVENT_BROADCAST_INTENT)) {
            Date date = new Date();
            LogHelper.d("RequestNavigationEventReceiver", "RequestNavigationEventReceiver " + date);
            Toast.makeText(context, "Send nav start or stop command", 1).show();
        }
    }
}