package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.7")
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: classes12.dex */
public final class DeepRecursiveFunction<T, R> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function3<DeepRecursiveScope<T, R>, T, Continuation<? super R>, Object> f14045a;

    /* JADX WARN: Multi-variable type inference failed */
    public DeepRecursiveFunction(@NotNull Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.f14045a = block;
    }

    @NotNull
    public final Function3<DeepRecursiveScope<T, R>, T, Continuation<? super R>, Object> getBlock$kotlin_stdlib() {
        return this.f14045a;
    }
}
