package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes13.dex */
public class MultiByteArrayInputStream extends InputStream {
    public byte[] h;
    public int i;
    public int j;
    public byte[] k;
    public int l;
    public int m;
    public int n = 0;

    public MultiByteArrayInputStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.h = (byte[]) bArr.clone();
        this.k = (byte[]) bArr2.clone();
        this.i = i;
        this.l = i3;
        this.j = i2;
        this.m = i4;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i;
        int i2 = this.n;
        int i3 = this.j;
        if (i2 < i3) {
            i = this.h[this.i + i2];
        } else if (i2 >= this.m + i3) {
            return -1;
        } else {
            i = this.k[(this.l + i2) - i3];
        }
        if (i < 0) {
            i += 256;
        }
        this.n = i2 + 1;
        return i;
    }
}
