package com.goodix.ble.gr.libdfu.dfu.cmd.sdu;

import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
/* loaded from: classes5.dex */
public class XNvds implements IFrameSdu4Tx, IFrameSdu4Rx {
    public static final int OP_CONFIG = 3;
    public static final int OP_DELETE = 2;
    public static final int OP_READ = 1;
    public static final int OP_WRITE = 0;

    /* renamed from: a  reason: collision with root package name */
    public int f7988a;
    public int b;
    public int c;
    public int d;
    public byte[] e;
    public int f;
    public int g;

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx
    public void deserialize(HexReader hexReader) {
        this.f7988a = hexReader.get(1);
        int i = hexReader.get(1);
        this.b = i;
        if (i != 3) {
            this.c = hexReader.get(2);
            if (this.b == 1) {
                int i2 = hexReader.get(2);
                byte[] bArr = new byte[i2];
                this.e = bArr;
                hexReader.get(bArr, 0, i2);
            }
        }
    }

    public byte[] getData() {
        return this.e;
    }

    public int getOpration() {
        return this.b;
    }

    public int getResponse() {
        return this.f7988a;
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx
    public int getSduSize() {
        int i = this.b;
        if (i == 0) {
            byte[] bArr = this.e;
            if (bArr != null) {
                return bArr.length + 5;
            }
            return 5;
        } else if (i != 1) {
            if (i != 2) {
                return i != 3 ? 0 : 7;
            }
            return 3;
        } else {
            return 5;
        }
    }

    public int getTagId() {
        return this.c;
    }

    public void requestConfig(int i, int i2) {
        this.b = 3;
        this.f = i;
        this.g = i2;
    }

    public void requestDelete(int i) {
        this.b = 2;
        this.c = i;
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
        if (this.b == 3) {
            hexBuilder.put(this.f, 4);
            hexBuilder.put(this.g, 2);
            return;
        }
        hexBuilder.put(this.c, 2);
        int i = this.b;
        if (i == 1) {
            hexBuilder.put(this.d, 2);
        } else if (i == 0) {
            byte[] bArr = this.e;
            if (bArr != null) {
                hexBuilder.put(bArr.length, 2);
                hexBuilder.put(this.e);
                return;
            }
            hexBuilder.put(0, 2);
        }
    }

    public void requestRead(int i) {
        requestRead(i, 1024);
    }
}
