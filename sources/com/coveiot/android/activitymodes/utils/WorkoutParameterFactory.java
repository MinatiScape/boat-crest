package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.devicemodels.DeviceUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class WorkoutParameterFactory {
    @NotNull
    public static final WorkoutParameterFactory INSTANCE = new WorkoutParameterFactory();

    @NotNull
    public final WorkoutParameterInterface getWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isSmaDevice(context)) {
            return SMAWorkoutUtils.Companion.getInstance(context);
        }
        if (companion.isMatrixDevice(context)) {
            if (companion.isRuggedDevice(context)) {
                return RuggedWorkoutUtils.Companion.getInstance(context);
            }
            return MatrixWorkoutUtils.Companion.getInstance(context);
        } else if (companion.isIDODevice(context)) {
            return IDOWorkoutUtils.Companion.getInstance(context);
        } else {
            if (companion.isTouchELXDevice(context)) {
                return TouchELXWorkoutUtils.Companion.getInstance(context);
            }
            if (companion.isJstyleDevice(context)) {
                return JstyleWorkoutUtils.Companion.getInstance(context);
            }
            if (companion.isEastApexDevice(context)) {
                return EastApexWorkoutUtils.Companion.getInstance(context);
            }
            if (companion.isKaHaDevice(context)) {
                return KahaWorkoutUtils.Companion.getInstance(context);
            }
            if (companion.isMoyangDevice(context)) {
                return MoyangWorkoutUtils.Companion.getInstance(context);
            }
            return KahaWorkoutUtils.Companion.getInstance(context);
        }
    }
}
