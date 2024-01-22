package kotlin;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class c extends LazyKt__LazyJVMKt {
    @NotNull
    public static final <T> Lazy<T> lazyOf(T t) {
        return new InitializedLazyImpl(t);
    }
}
