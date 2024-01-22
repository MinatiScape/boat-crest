package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class XDumpFlash implements IFrameSdu4Tx {
    public int address;
    public boolean extFlash;
    public int length;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return 7;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.extFlash ? 1 : 0, 1);
        hexBuilder.put(this.address, 4);
        hexBuilder.put(this.length, 2);
    }
}
