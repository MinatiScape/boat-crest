package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class XGetFlashInfo implements IFrameSdu4Tx {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7987a;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return 1;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.f7987a ? 1 : 0, 1);
    }

    public XGetFlashInfo setFlashType(boolean z) {
        this.f7987a = z;
        return this;
    }
}
