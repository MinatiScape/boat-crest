package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class XUpdateFlash implements IFrameSdu4Tx {
    public int address;
    public byte[] data;
    public boolean extFlash;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return 7;
        }
        return bArr.length + 1 + 4 + 2;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.extFlash ? 1 : 0, 1);
        hexBuilder.put(this.address, 4);
        byte[] bArr = this.data;
        if (bArr != null) {
            hexBuilder.put(bArr.length, 2);
            hexBuilder.put(this.data);
            return;
        }
        hexBuilder.put(0, 2);
    }
}
