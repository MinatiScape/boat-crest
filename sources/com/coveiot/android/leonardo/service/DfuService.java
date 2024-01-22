package com.coveiot.android.leonardo.service;

import android.app.Activity;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaRealTek;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes5.dex */
public class DfuService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    public Class<? extends Activity> getNotificationTarget() {
        if (DeviceUtils.isJstyleDevice(this)) {
            return ActivityFirmwareUpdateJStyle.class;
        }
        if (DeviceUtils.isSmaDevice(this)) {
            if (DeviceUtils.isSmaJieieDevice(this)) {
                return ActivityFirmwareUpdateSmaJL.class;
            }
            return ActivityFirmwareUpdateSmaRealTek.class;
        } else if (DeviceUtils.isCADevice(this) || DeviceUtils.isCYDevice(this) || DeviceUtils.isPS1Device(this)) {
            return ActivityFirmwareUpdateKaHaRealTek.class;
        } else {
            if (DeviceUtils.isMatrixDevice(this)) {
                return ActivityFirmwareUpdateMatrix.class;
            }
            return ActivityFirmwareUpdate.class;
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    public boolean isDebug() {
        return false;
    }
}
