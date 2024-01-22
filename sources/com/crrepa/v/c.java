package com.crrepa.v;

import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.trans.upgrade.bean.HSFirmwareInfo;
/* loaded from: classes9.dex */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    public d f7846a;
    public i b;

    public c(d dVar) {
        this.f7846a = dVar;
        this.b = dVar.b();
    }

    @Override // com.crrepa.v.b
    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener, HSFirmwareInfo hSFirmwareInfo) {
        this.b.a();
        this.b.a(cRPBleFirmwareUpgradeListener);
        this.b.c(hSFirmwareInfo);
    }

    @Override // com.crrepa.v.b
    public boolean a() {
        return this.b.b();
    }

    @Override // com.crrepa.v.b
    public void b() {
        this.b.e();
    }

    @Override // com.crrepa.v.b
    public void setConnectionStateListener(CRPBleConnectionStateListener cRPBleConnectionStateListener) {
        this.f7846a.a(cRPBleConnectionStateListener);
    }
}
