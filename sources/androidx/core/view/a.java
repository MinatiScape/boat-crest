package androidx.core.view;

import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@RequiresApi(16)
/* loaded from: classes.dex */
public final class a {
    static {
        new a();
    }

    @JvmStatic
    @DoNotInline
    public static final void a(@NotNull View view, @NotNull Runnable action, long j) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(action, "action");
        view.postOnAnimationDelayed(action, j);
    }
}
