package kotlin.comparisons;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class g extends f {
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a2.compareTo(b) >= 0 ? a2 : b;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T b) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a2.compareTo(b) <= 0 ? a2 : b;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T b, @NotNull T c) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(c, "c");
        return (T) maxOf(a2, maxOf(b, c));
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T b, @NotNull T c) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(c, "c");
        return (T) minOf(a2, minOf(b, c));
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t : other) {
            a2 = (T) maxOf(a2, t);
        }
        return a2;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t : other) {
            a2 = (T) minOf(a2, t);
        }
        return a2;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final byte maxOf(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = (byte) Math.max((int) b, (int) b2);
        }
        return b;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final byte minOf(byte b, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b2 : other) {
            b = (byte) Math.min((int) b, (int) b2);
        }
        return b;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final short maxOf(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = (short) Math.max((int) s, (int) s2);
        }
        return s;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final short minOf(short s, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s2 : other) {
            s = (short) Math.min((int) s, (int) s2);
        }
        return s;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final int maxOf(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = Math.max(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final int minOf(int i, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i2 : other) {
            i = Math.min(i, i2);
        }
        return i;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final long maxOf(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = Math.max(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final long minOf(long j, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j2 : other) {
            j = Math.min(j, j2);
        }
        return j;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final float maxOf(float f, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f2 : other) {
            f = Math.max(f, f2);
        }
        return f;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final float minOf(float f, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f2 : other) {
            f = Math.min(f, f2);
        }
        return f;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final double maxOf(double d, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d2 : other) {
            d = Math.max(d, d2);
        }
        return d;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static final double minOf(double d, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d2 : other) {
            d = Math.min(d, d2);
        }
        return d;
    }
}
