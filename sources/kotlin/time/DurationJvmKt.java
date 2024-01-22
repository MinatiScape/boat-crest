package kotlin.time;

import com.jstyle.blesdk1860.constant.BleConst;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DurationJvmKt {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f14130a = false;
    @NotNull
    public static final ThreadLocal<DecimalFormat>[] b;

    static {
        ThreadLocal<DecimalFormat>[] threadLocalArr = new ThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            threadLocalArr[i] = new ThreadLocal<>();
        }
        b = threadLocalArr;
    }

    public static final DecimalFormat a(int i) {
        DecimalFormat decimalFormat = new DecimalFormat(BleConst.GetDeviceTime);
        if (i > 0) {
            decimalFormat.setMinimumFractionDigits(i);
        }
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat;
    }

    @NotNull
    public static final String formatToExactDecimals(double d, int i) {
        DecimalFormat a2;
        ThreadLocal<DecimalFormat>[] threadLocalArr = b;
        if (i < threadLocalArr.length) {
            ThreadLocal<DecimalFormat> threadLocal = threadLocalArr[i];
            DecimalFormat decimalFormat = threadLocal.get();
            if (decimalFormat == null) {
                decimalFormat = a(i);
                threadLocal.set(decimalFormat);
            } else {
                Intrinsics.checkNotNullExpressionValue(decimalFormat, "get() ?: default().also(this::set)");
            }
            a2 = decimalFormat;
        } else {
            a2 = a(i);
        }
        String format = a2.format(d);
        Intrinsics.checkNotNullExpressionValue(format, "format.format(value)");
        return format;
    }

    @NotNull
    public static final String formatUpToDecimals(double d, int i) {
        DecimalFormat a2 = a(0);
        a2.setMaximumFractionDigits(i);
        String format = a2.format(d);
        Intrinsics.checkNotNullExpressionValue(format, "createFormatForDecimals(… }\n        .format(value)");
        return format;
    }

    public static final boolean getDurationAssertionsEnabled() {
        return f14130a;
    }
}
