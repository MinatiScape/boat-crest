package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class XProgramFlash implements IFrameSdu4Tx {

    /* renamed from: a  reason: collision with root package name */
    public int f7989a;
    public int address;
    public byte[] b;
    public int c;
    public int d;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return this.d + 7;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.f7989a, 1);
        hexBuilder.put(this.address, 4);
        hexBuilder.put(this.d, 2);
        hexBuilder.put(this.b, this.c, this.d);
    }

    public void setData(byte[] bArr, int i, int i2) {
        this.b = bArr;
        this.c = i;
        this.d = i2;
    }

    public void setType(boolean z, int i) {
        int i2 = i & 15;
        this.f7989a = i2;
        if (z) {
            this.f7989a = i2 | 16;
        }
    }
}
