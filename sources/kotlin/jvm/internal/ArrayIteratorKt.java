package kotlin.jvm.internal;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ArrayIteratorKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return new g(array);
    }
}
