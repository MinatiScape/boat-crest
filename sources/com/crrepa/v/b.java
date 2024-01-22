package com.crrepa.v;

import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.trans.upgrade.bean.HSFirmwareInfo;
/* loaded from: classes9.dex */
public interface b {
    void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener, HSFirmwareInfo hSFirmwareInfo);

    boolean a();

    void b();

    void setConnectionStateListener(CRPBleConnectionStateListener cRPBleConnectionStateListener);
}
