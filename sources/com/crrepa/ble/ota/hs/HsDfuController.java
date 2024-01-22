package com.crrepa.ble.ota.hs;

import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.v.h;
import java.io.File;
/* loaded from: classes9.dex */
public class HsDfuController {

    /* renamed from: a  reason: collision with root package name */
    public h f7680a = new h();

    public void abort() {
        this.f7680a.a();
    }

    public void resume() {
        this.f7680a.e();
    }

    public void setAddress(String str) {
        this.f7680a.d(str);
    }

    public void setUpgradeListener(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.f7680a.a(cRPBleFirmwareUpgradeListener);
    }

    public void start(File file, boolean z, boolean z2) {
        this.f7680a.b(file, z, z2);
    }
}
