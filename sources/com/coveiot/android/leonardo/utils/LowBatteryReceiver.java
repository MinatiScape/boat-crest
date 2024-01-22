package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.dfu.DfuManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class LowBatteryReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final int f5425a = DfuManager.ERROR_CODE_DFU_PROCESS_STARTUP_FAILED;

    public final int getLOW_BATTERY_NOTIFICATION_ID() {
        return this.f5425a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        Dashboard2Constants.Companion companion = Dashboard2Constants.Companion;
        if (kotlin.text.m.equals$default(action, companion.getACTION_LOW_BATTERY_NOTIFICATION_SHOW(), false, 2, null)) {
            LogHelper.d("LowBatteryReceiver", "ACTION_LOW_BATTERY_NOTIFICATION_SHOW");
            NotificationManager.getInstance().notifyLowBatterAlertAlert(context, this.f5425a);
        } else if (kotlin.text.m.equals$default(intent.getAction(), companion.getACTION_LOW_BATTERY_NOTIFICATION_DISMISS(), false, 2, null)) {
            LogHelper.d("LowBatteryReceiver", "ACTION_LOW_BATTERY_NOTIFICATION_DISMISS");
            NotificationManager.getInstance().cancleNotification(context, this.f5425a);
        }
    }
}
