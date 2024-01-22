package com.coveiot.android.leonardo.utils;

import android.app.Activity;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class FirmwareUpdateActivityFactory {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void navigateToFirmwareUpdateActivity(@NotNull Activity context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isJeiLiChipSet(context)) {
                AppNavigator.Companion.navigateToKaHaMKIFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isJstyleDevice(context)) {
                AppNavigator.Companion.navigateToJStyleFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isSmaDevice(context)) {
                if (companion.isSmaJieieDevice(context)) {
                    AppNavigator.Companion.navigateToSmaJLFirmwareUpdateActivity(context, firmwareBean);
                } else {
                    AppNavigator.Companion.navigateToSmaRealTekFirmwareUpdateActivity(context, firmwareBean);
                }
            } else if (companion.isCADevice(context)) {
                AppNavigator.Companion.navigateToKaHaRealTekFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isCYDevice(context)) {
                AppNavigator.Companion.navigateToKaHaMKIFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isMoyangDevice(context)) {
                AppNavigator.Companion.navigateToMoyangFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isMatrixDevice(context)) {
                AppNavigator.Companion.navigateToMatrixFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isIDODevice(context)) {
                AppNavigator.Companion.navigateToIDOFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isTouchELXDevice(context)) {
                AppNavigator.Companion.navigateToTouchElxFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isEastApexDevice(context)) {
                AppNavigator.Companion.navigateToEastApexFirmwareUpdateActivity(context, firmwareBean);
            } else if (companion.isPS1Device(context)) {
                AppNavigator.Companion.navigateToPS1FirmwareUpdateActivity(context, firmwareBean);
            } else {
                AppNavigator.Companion.navigateToFirmwareUpdateActivity(context, firmwareBean);
            }
        }
    }
}
