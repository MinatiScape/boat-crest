package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
/* loaded from: classes5.dex */
public class XEfuse implements IFrameSdu4Tx, IFrameSdu4Rx {
    public static final int OP_READ = 1;
    public static final int OP_WRITE = 0;

    /* renamed from: a  reason: collision with root package name */
    public int f7986a;
    public int b;
    public int c;
    public int d;
    public byte[] e;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx
    public void deserialize(HexReader hexReader) {
        this.f7986a = hexReader.get(1);
        int i = hexReader.get(1);
        this.b = i;
        if (i == 1) {
            this.c = hexReader.get(2);
            int i2 = hexReader.get(2);
            byte[] bArr = new byte[i2];
            this.e = bArr;
            hexReader.get(bArr, 0, i2);
        }
    }

    public byte[] getData() {
        return this.e;
    }

    public int getOffset() {
        return this.c;
    }

    public int getOperation() {
        return this.b;
    }

    public int getResponse() {
        return this.f7986a;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        if (this.b == 0) {
            byte[] bArr = this.e;
            return (bArr != null ? bArr.length : 0) + 5;
        }
        return 5;
    }

    public void requestRead(int i, int i2) {
        this.b = 1;
        this.c = i;
        this.d = i2;
    }

    public void requestWrite(int i, byte[] bArr) {
        this.b = 0;
        this.c = i;
        this.e = bArr;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public void serialize(HexBuilder hexBuilder) {
        hexBuilder.put(this.b, 1);
        hexBuilder.put(this.c, 2);
        if (this.b == 1) {
            hexBuilder.put(this.d, 2);
            return;
        }
        byte[] bArr = this.e;
        if (bArr != null) {
            hexBuilder.put(bArr.length, 2);
            hexBuilder.put(this.e);
            return;
        }
        hexBuilder.put(0, 2);
    }
}
