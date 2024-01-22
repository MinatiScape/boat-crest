package androidx.core.os;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class b {
    static {
        new b();
    }

    @JvmStatic
    @DoNotInline
    public static final void a(@NotNull Bundle bundle, @NotNull String key, @Nullable Size size) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        bundle.putSize(key, size);
    }

    @JvmStatic
    @DoNotInline
    public static final void b(@NotNull Bundle bundle, @NotNull String key, @Nullable SizeF sizeF) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(key, "key");
        bundle.putSizeF(key, sizeF);
    }
}
