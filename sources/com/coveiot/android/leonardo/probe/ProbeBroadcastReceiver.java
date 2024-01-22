package com.coveiot.android.leonardo.probe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ProbeBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        LogHelper.d("ProbeBroadcastReceiver", " Broadcast received");
        PayUtils.INSTANCE.scheduleJobImmediatly(context);
    }
}
