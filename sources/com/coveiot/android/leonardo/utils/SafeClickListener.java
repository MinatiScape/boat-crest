package com.coveiot.android.leonardo.utils;

import android.os.SystemClock;
import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SafeClickListener implements View.OnClickListener {
    public int h;
    @NotNull
    public final Function1<View, Unit> i;
    public long j;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeClickListener(int i, @NotNull Function1<? super View, Unit> onSafeCLick) {
        Intrinsics.checkNotNullParameter(onSafeCLick, "onSafeCLick");
        this.h = i;
        this.i = onSafeCLick;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (SystemClock.elapsedRealtime() - this.j < this.h) {
            return;
        }
        this.j = SystemClock.elapsedRealtime();
        this.i.invoke(v);
    }

    public /* synthetic */ SafeClickListener(int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 600 : i, function1);
    }
}
