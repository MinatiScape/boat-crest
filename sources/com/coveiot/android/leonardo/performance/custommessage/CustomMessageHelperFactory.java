package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.devicemodels.DeviceUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class CustomMessageHelperFactory {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CustomMessageHelper getCustomMessageHelper(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isCYDevice(context)) {
                return CustomMessageHelperCY.INSTANCE;
            }
            if (companion.isOPP1Device(context)) {
                return CustomMessageHelperOPP1.INSTANCE;
            }
            if (companion.isOPP2Device(context)) {
                return CustomMessageHelperOPP2.INSTANCE;
            }
            if (companion.isOPP3Device(context)) {
                return CustomMessageHelperOPP3.INSTANCE;
            }
            if (companion.isOPP4Device(context)) {
                return CustomMessageHelperOPP4.INSTANCE;
            }
            if (companion.isOPP5Device(context)) {
                return CustomMessageHelperOPP5.INSTANCE;
            }
            if (companion.isPS1Device(context)) {
                return CustomMessageHelperPS1.INSTANCE;
            }
            if (companion.isCADevice(context)) {
                if (companion.isOPP1Device(context)) {
                    return CustomMessageHelperOPP1.INSTANCE;
                }
                if (companion.isOPP2Device(context)) {
                    return CustomMessageHelperOPP2.INSTANCE;
                }
                if (companion.isOPP3Device(context)) {
                    return CustomMessageHelperOPP3.INSTANCE;
                }
                if (companion.isOPP4Device(context)) {
                    return CustomMessageHelperOPP4.INSTANCE;
                }
                if (companion.isOPP5Device(context)) {
                    return CustomMessageHelperOPP5.INSTANCE;
                }
                return CustomMessageHelperCA.INSTANCE;
            }
            return CustomMessageHelperCA.INSTANCE;
        }
    }
}
