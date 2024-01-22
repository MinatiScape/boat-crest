package com.coveiot.android.dashboard2.util;

import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import java.text.NumberFormat;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class FragmentUtilsKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Lazy f4255a = LazyKt__LazyJVMKt.lazy(a.INSTANCE);

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function0<Handler> {
        public static final a INSTANCE = new a();

        public a() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    @NotNull
    public static final String getFormatCount(int i) {
        String format = NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(i));
        Intrinsics.checkNotNullExpressionValue(format, "getNumberInstance(Locale.US).format(this)");
        return format;
    }

    @NotNull
    public static final Handler getHandler(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return (Handler) f4255a.getValue();
    }

    public static final void postDelayed(@NotNull Fragment fragment, @NotNull Runnable action, long j) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        getHandler(fragment).postDelayed(action, j);
    }
}
