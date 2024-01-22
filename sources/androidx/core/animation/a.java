package androidx.core.animation;

import android.animation.Animator;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@RequiresApi(19)
/* loaded from: classes.dex */
public final class a {
    static {
        new a();
    }

    @JvmStatic
    @DoNotInline
    public static final void a(@NotNull Animator animator, @NotNull Animator.AnimatorPauseListener listener) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        Intrinsics.checkNotNullParameter(listener, "listener");
        animator.addPauseListener(listener);
    }
}
