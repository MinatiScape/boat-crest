package com.coveiot.android.leonardo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.covepreferences.SessionManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class RebootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!SessionManager.getInstance(context).isLoggedIn() || BleApiManager.getInstance(context).getBleApi() == null) {
            return;
        }
        BleApiManager.getInstance(context).getBleApi().restartService();
    }
}
