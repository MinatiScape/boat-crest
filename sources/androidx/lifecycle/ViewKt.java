package androidx.lifecycle;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ViewKt {
    @Nullable
    public static final LifecycleOwner findViewTreeLifecycleOwner(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return ViewTreeLifecycleOwner.get(view);
    }
}
