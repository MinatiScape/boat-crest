package com.bestmafen.baseble.util;

import android.util.Log;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class BleLog {
    @NotNull
    public static final BleLog INSTANCE = new BleLog();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static Function3<? super LogLevel, ? super String, ? super String, Boolean> f2228a;

    public final void d(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Function3<? super LogLevel, ? super String, ? super String, Boolean> function3 = f2228a;
        if (function3 != null) {
            Intrinsics.checkNotNull(function3);
            if (function3.invoke(LogLevel.D, "BaseBle", msg).booleanValue()) {
                return;
            }
        }
        Log.d("BaseBle", msg);
    }

    public final void e(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Function3<? super LogLevel, ? super String, ? super String, Boolean> function3 = f2228a;
        if (function3 != null) {
            Intrinsics.checkNotNull(function3);
            if (function3.invoke(LogLevel.E, "BaseBle", msg).booleanValue()) {
                return;
            }
        }
        Log.e("BaseBle", msg);
    }

    @Nullable
    public final Function3<LogLevel, String, String, Boolean> getMInterceptor() {
        return f2228a;
    }

    public final void i(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Function3<? super LogLevel, ? super String, ? super String, Boolean> function3 = f2228a;
        if (function3 != null) {
            Intrinsics.checkNotNull(function3);
            if (function3.invoke(LogLevel.I, "BaseBle", msg).booleanValue()) {
                return;
            }
        }
        Log.i("BaseBle", msg);
    }

    public final void setMInterceptor(@Nullable Function3<? super LogLevel, ? super String, ? super String, Boolean> function3) {
        f2228a = function3;
    }

    public final void v(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Function3<? super LogLevel, ? super String, ? super String, Boolean> function3 = f2228a;
        if (function3 != null) {
            Intrinsics.checkNotNull(function3);
            if (function3.invoke(LogLevel.V, "BaseBle", msg).booleanValue()) {
                return;
            }
        }
        Log.v("BaseBle", msg);
    }

    public final void w(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Function3<? super LogLevel, ? super String, ? super String, Boolean> function3 = f2228a;
        if (function3 != null) {
            Intrinsics.checkNotNull(function3);
            if (function3.invoke(LogLevel.W, "BaseBle", msg).booleanValue()) {
                return;
            }
        }
        Log.w("BaseBle", msg);
    }
}
