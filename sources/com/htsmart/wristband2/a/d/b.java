package com.htsmart.wristband2.a.d;
/* loaded from: classes11.dex */
public class b {
    public static final int c = 3;

    /* renamed from: a  reason: collision with root package name */
    public byte f11946a;
    public int b;
    public byte[] d;

    public static b a(byte[] bArr) {
        if (bArr.length < 3) {
            return null;
        }
        b bVar = new b();
        bVar.f11946a = bArr[0];
        int i = ((bArr[1] << 8) & 256) | (bArr[2] & 255);
        bVar.b = i;
        if (bArr.length < i + 3) {
            return null;
        }
        byte[] bArr2 = new byte[i];
        bVar.d = bArr2;
        System.arraycopy(bArr, 3, bArr2, 0, i);
        return bVar;
    }

    public static byte[] a(byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length + 3];
            System.arraycopy(bArr, 0, bArr2, 3, bArr.length);
            bArr2[0] = b;
            bArr2[1] = (byte) ((bArr.length >> 8) & 1);
            bArr2[2] = (byte) (bArr.length & 255);
            return bArr2;
        }
        return new byte[]{b, 0, 0};
    }

    public byte a() {
        return this.f11946a;
    }

    public void a(byte b) {
        this.f11946a = b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(byte[] bArr) {
        this.d = bArr;
    }

    public byte[] b() {
        return this.d;
    }

    public int c() {
        return this.b;
    }

    public String toString() {
        return "key: " + ((int) this.f11946a) + ", payloadLength: " + this.b;
    }
}
