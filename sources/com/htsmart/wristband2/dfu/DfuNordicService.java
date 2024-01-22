package com.htsmart.wristband2.dfu;

import android.app.Activity;
import androidx.annotation.Nullable;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes11.dex */
public class DfuNordicService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    @Nullable
    public Class<? extends Activity> getNotificationTarget() {
        return null;
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    public boolean isDebug() {
        return true;
    }
}
