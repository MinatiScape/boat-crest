package kotlin;

import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DeepRecursiveKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Object f14046a;

    static {
        Result.Companion companion = Result.Companion;
        f14046a = Result.m123constructorimpl(kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED());
    }

    @SinceKotlin(version = "1.7")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final <T, R> R invoke(@NotNull DeepRecursiveFunction<T, R> deepRecursiveFunction, T t) {
        Intrinsics.checkNotNullParameter(deepRecursiveFunction, "<this>");
        return (R) new DeepRecursiveScopeImpl(deepRecursiveFunction.getBlock$kotlin_stdlib(), t).e();
    }
}
