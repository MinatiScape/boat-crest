package com.crrepa.ble.trans.tp;

import com.crrepa.ble.conn.listener.CRPTransListener;
/* loaded from: classes9.dex */
public class CRPTpHelper {
    public void abort() {
        CRPTpTransInitiator.getInstance().abort();
    }

    public void start(CRPTpInfo cRPTpInfo, CRPTransListener cRPTransListener) {
        CRPTpTransInitiator.getInstance().start(cRPTpInfo, cRPTransListener);
    }
}
