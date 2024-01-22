package com.coveiot.android.theme;

import android.content.Context;
import android.widget.Toast;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class ExtensionFuncsKt {
    public static final int percentage(int i, float f) {
        return (int) ((f / 100.0f) * i);
    }

    public static final void toastLong(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 1).show();
    }

    public static final void toastShort(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 0).show();
    }
}
