package com.coveiot.android.leonardo.more;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.leonardo.more.AppListHelper;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AppListLoaderReceiver extends BroadcastReceiver {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4875a;

    public AppListLoaderReceiver() {
        String simpleName = AppListLoaderReceiver.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.f4875a = simpleName;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.getAction() != null) {
            String action = intent.getAction();
            Boolean valueOf = action != null ? Boolean.valueOf(action.equals("android.intent.action.PACKAGE_REMOVED")) : null;
            Intrinsics.checkNotNull(valueOf);
            if (!valueOf.booleanValue()) {
                String action2 = intent.getAction();
                Boolean valueOf2 = action2 != null ? Boolean.valueOf(action2.equals("android.intent.action.PACKAGE_ADDED")) : null;
                Intrinsics.checkNotNull(valueOf2);
                if (!valueOf2.booleanValue()) {
                    return;
                }
            }
            LogHelper.d(this.f4875a, "App list updated");
            LogHelper.d(this.f4875a, intent.toString());
            AppListHelper.Companion.saveAppListFromSystem$default(AppListHelper.Companion, context, false, 2, null);
        }
    }
}
