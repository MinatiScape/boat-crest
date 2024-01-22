package com.htsmart.wristband2.a.d;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public byte f11945a;
    public int b;
    public List<b> c;

    public static a a(byte[] bArr) {
        if (bArr.length < 2) {
            return null;
        }
        a aVar = new a();
        aVar.f11945a = bArr[0];
        aVar.b = (bArr[1] >> 4) & 15;
        aVar.c = new ArrayList();
        int length = bArr.length - 2;
        do {
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, bArr.length - length, bArr2, 0, length);
            b a2 = b.a(bArr2);
            if (a2 == null) {
                break;
            }
            aVar.c.add(a2);
            length -= a2.c() + 3;
        } while (length > 0);
        return aVar;
    }

    public static byte[] a(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 2];
        bArr2[0] = b;
        bArr2[1] = (byte) ((d() << 4) & 255);
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public static int d() {
        return 0;
    }

    public byte a() {
        return this.f11945a;
    }

    public void a(byte b) {
        this.f11945a = b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(List<b> list) {
        this.c = list;
    }

    public List<b> b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public String toString() {
        return "cmdId: " + ((int) this.f11945a) + ", version: " + this.b;
    }
}
