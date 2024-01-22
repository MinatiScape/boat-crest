package com.abupdate.mqtt_libs.mqttv3.a.c;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class v extends InputStream {
    public byte[] h;
    public int i;
    public int j;
    public byte[] k;
    public int l;
    public int m;
    public int n = 0;

    public v(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.h = bArr;
        this.k = bArr2;
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
