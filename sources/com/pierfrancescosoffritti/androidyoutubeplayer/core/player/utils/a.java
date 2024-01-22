package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class a extends BroadcastReceiver {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function0<Unit> f13327a;
    @NotNull
    public final Function0<Unit> b;

    public a(@NotNull Function0<Unit> onNetworkAvailable, @NotNull Function0<Unit> onNetworkUnavailable) {
        Intrinsics.checkNotNullParameter(onNetworkAvailable, "onNetworkAvailable");
        Intrinsics.checkNotNullParameter(onNetworkUnavailable, "onNetworkUnavailable");
        this.f13327a = onNetworkAvailable;
        this.b = onNetworkUnavailable;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        boolean a2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        a2 = NetworkObserverKt.a(context);
        if (a2) {
            this.f13327a.invoke();
        } else {
            this.b.invoke();
        }
    }
}
