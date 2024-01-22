package com.sifli.siflidfu;

import java.util.ArrayList;
/* loaded from: classes12.dex */
public class CurrentSendFile {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<byte[]> f13698a;
    public int b;
    public int c;
    public int d;
    public String e;

    public CurrentSendFile(byte[] bArr, int i, String str) {
        byte[] bArr2;
        this.b = 2048;
        int length = bArr.length;
        this.d = length;
        this.e = str;
        if (length == 0) {
            this.c = 0;
            this.f13698a = null;
            return;
        }
        this.b = i;
        if (bArr.length % i == 0) {
            this.c = bArr.length / i;
        } else {
            this.c = (bArr.length / i) + 1;
        }
        this.f13698a = new ArrayList<>(this.c);
        int i2 = 0;
        for (int i3 = 0; i3 < this.c; i3++) {
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
            this.f13698a.add(i3, bArr2);
        }
    }

    public byte[] getData(int i) {
        return this.f13698a.get(i);
    }

    public String getFileName() {
        return this.e;
    }

    public int getPacketSize() {
        return this.b;
    }

    public int getTotalCount() {
        return this.c;
    }

    public int getTotalSize() {
        return this.d;
    }

    public CurrentSendFile(int i, int i2, String str) {
        this.b = 2048;
        this.d = i;
        this.e = str;
        this.b = i2;
        if (i % i2 == 0) {
            this.c = i / i2;
        } else {
            this.c = (i / i2) + 1;
        }
    }
}
