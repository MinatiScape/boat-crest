package androidx.activity;

import android.view.View;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@RequiresApi(19)
/* loaded from: classes.dex */
public final class Api19Impl {
    @NotNull
    public static final Api19Impl INSTANCE = new Api19Impl();

    public final boolean isAttachedToWindow(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return view.isAttachedToWindow();
    }
}
