package com.sifli.watchfacelibrary;

import java.util.ArrayList;
/* loaded from: classes12.dex */
public class CurrentSendFile {

    /* renamed from: a  reason: collision with root package name */
    public int f13705a;
    public int b;
    public ArrayList<byte[]> c;

    public CurrentSendFile(byte[] bArr, int i) {
        byte[] bArr2;
        int length = bArr.length;
        this.b = length;
        if (length == 0) {
            this.f13705a = 0;
            this.c = null;
            return;
        }
        if (bArr.length % i == 0) {
            this.f13705a = bArr.length / i;
        } else {
            this.f13705a = (bArr.length / i) + 1;
        }
        this.c = new ArrayList<>(this.f13705a);
        int i2 = 0;
        for (int i3 = 0; i3 < this.f13705a; i3++) {
            int i4 = i2 + i;
            if (i4 <= bArr.length) {
                bArr2 = new byte[i];
                System.arraycopy(bArr, i2, bArr2, 0, i);
                i2 = i4;
            } else {
                int length2 = bArr.length - i2;
                bArr2 = new byte[length2];
                System.arraycopy(bArr, i2, bArr2, 0, length2);
                i2 += length2;
            }
            this.c.add(i3, bArr2);
        }
    }

    public byte[] getData(int i) {
        return this.c.get(i);
    }

    public int getTotalCount() {
        return this.f13705a;
    }
}
