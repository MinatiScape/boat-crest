package kotlin.jvm.internal;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class FloatCompanionObject {
    @NotNull
    public static final FloatCompanionObject INSTANCE = new FloatCompanionObject();
    public static final float MAX_VALUE = Float.MAX_VALUE;
    public static final float MIN_VALUE = Float.MIN_VALUE;
    public static final float NEGATIVE_INFINITY = Float.NEGATIVE_INFINITY;
    public static final float NaN = Float.NaN;
    public static final float POSITIVE_INFINITY = Float.POSITIVE_INFINITY;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;

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

    public final float getMAX_VALUE() {
        return Float.MAX_VALUE;
    }

    public final float getMIN_VALUE() {
        return Float.MIN_VALUE;
    }

    public final float getNEGATIVE_INFINITY() {
        return Float.NEGATIVE_INFINITY;
    }

    public final float getNaN() {
        return Float.NaN;
    }

    public final float getPOSITIVE_INFINITY() {
        return Float.POSITIVE_INFINITY;
    }
}
