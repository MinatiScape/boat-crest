package kotlin.jvm.internal;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DoubleCompanionObject {
    @NotNull
    public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
    public static final double MAX_VALUE = Double.MAX_VALUE;
    public static final double MIN_VALUE = Double.MIN_VALUE;
    public static final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    public static final double NaN = Double.NaN;
    public static final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getMAX_VALUE$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getMIN_VALUE$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getNEGATIVE_INFINITY$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getNaN$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getPOSITIVE_INFINITY$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    public final double getMAX_VALUE() {
        return Double.MAX_VALUE;
    }

    public final double getMIN_VALUE() {
        return Double.MIN_VALUE;
    }

    public final double getNEGATIVE_INFINITY() {
        return Double.NEGATIVE_INFINITY;
    }

    public final double getNaN() {
        return Double.NaN;
    }

    public final double getPOSITIVE_INFINITY() {
        return Double.POSITIVE_INFINITY;
    }
}
