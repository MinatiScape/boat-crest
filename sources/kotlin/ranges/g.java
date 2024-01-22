package kotlin.ranges;

import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class g {
    public static final void checkStepIsPositive(boolean z, @NotNull Number step) {
        Intrinsics.checkNotNullParameter(step, "step");
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + step + '.');
    }

    @NotNull
    public static final <T extends Comparable<? super T>> ClosedRange<T> rangeTo(@NotNull T t, @NotNull T that) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(that, "that");
        return new d(t, that);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalStdlibApi
    @NotNull
    public static final <T extends Comparable<? super T>> OpenEndRange<T> rangeUntil(@NotNull T t, @NotNull T that) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(that, "that");
        return new c(t, that);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final ClosedFloatingPointRange<Double> rangeTo(double d, double d2) {
        return new a(d, d2);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalStdlibApi
    @NotNull
    public static final OpenEndRange<Double> rangeUntil(double d, double d2) {
        return new e(d, d2);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final ClosedFloatingPointRange<Float> rangeTo(float f, float f2) {
        return new b(f, f2);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalStdlibApi
    @NotNull
    public static final OpenEndRange<Float> rangeUntil(float f, float f2) {
        return new f(f, f2);
    }
}
