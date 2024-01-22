package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
/* loaded from: classes5.dex */
public class XRwReg implements IFrameSdu4Tx {

    /* renamed from: a  reason: collision with root package name */
    public int f7991a;
    public int b;
    public Integer c;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        return (this.c == null ? 0 : 4) + 5;
    }

    public XRwReg readReg(int i) {
        this.b = i;
        this.c = null;
        this.f7991a = 1;
        return this;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.f7991a, 1);
        hexBuilder.put(this.b, 4);
        Integer num = this.c;
        if (num != null) {
            hexBuilder.put(num.intValue(), 4);
        }
    }

    public XRwReg writeReg(int i, int i2) {
        this.b = i;
        this.c = Integer.valueOf(i2);
        this.f7991a = 0;
        return this;
    }
}
