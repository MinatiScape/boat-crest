package com.coveiot.android.theme.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.coveiot.utils.utility.LogHelper;
import java.text.NumberFormat;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentUtilsKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Lazy f6103a = LazyKt__LazyJVMKt.lazy(a.INSTANCE);

    /* loaded from: classes7.dex */
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

    @Nullable
    public static final DialogFragment getExternalModuleDialogFragment(@NotNull String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            Object newInstance = Class.forName(path).newInstance();
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type androidx.fragment.app.DialogFragment");
            return (DialogFragment) newInstance;
        } catch (Exception e) {
            e.printStackTrace();
            LogHelper.d("getExternalModuleDialogFragment", Unit.INSTANCE.toString());
            return null;
        }
    }

    public static final int getFormatCount(int i) {
        return Integer.parseInt(NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(i)));
    }

    @NotNull
    public static final Handler getHandler(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return (Handler) f6103a.getValue();
    }

    public static final void postDelayed(@NotNull Fragment fragment, @NotNull Runnable action, long j) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        getHandler(fragment).postDelayed(action, j);
    }
}
