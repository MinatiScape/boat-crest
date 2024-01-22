package com.ido.ble.dfu;

import android.app.Activity;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes11.dex */
public class DFUService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    public Class<? extends Activity> getNotificationTarget() {
        return null;
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    public boolean isDebug() {
        return true;
    }
}
