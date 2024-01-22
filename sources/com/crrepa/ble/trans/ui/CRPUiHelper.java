package com.crrepa.ble.trans.ui;

import com.crrepa.ble.conn.listener.CRPTransListener;
import java.io.File;
/* loaded from: classes9.dex */
public class CRPUiHelper {
    public void abort() {
        CRPUiTransInitiator.getInstance().abort();
    }

    public void start(File file, int i, String str, CRPTransListener cRPTransListener) {
        CRPUiTransInitiator cRPUiTransInitiator = CRPUiTransInitiator.getInstance();
        cRPUiTransInitiator.setListener(cRPTransListener);
        cRPUiTransInitiator.start(file, i, str);
    }
}
