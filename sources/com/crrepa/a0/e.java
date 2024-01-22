package com.crrepa.a0;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public RandomAccessFile f7636a;
    public int b;
    public int c;
    public int d;
    public int e = -1;
    public int f;

    public e(File file, int i, int i2) {
        this.c = i;
        this.d = i2;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
            this.f7636a = randomAccessFile;
            this.b = (int) ((randomAccessFile.length() - i2) / this.c);
        } catch (Exception e) {
            e.printStackTrace();
            a();
        }
    }

    public static e a(File file, int i, int i2) {
        if (file == null || !file.exists()) {
            return null;
        }
        e eVar = new e(file, i, i2);
        if (eVar.f7636a == null) {
            return null;
        }
        return eVar;
    }

    public void a() {
        try {
            RandomAccessFile randomAccessFile = this.f7636a;
            if (randomAccessFile != null) {
                randomAccessFile.close();
                this.f7636a = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] a(int i) {
        try {
            int i2 = this.c;
            int i3 = this.d + (i * i2);
            if (-1 != this.e) {
                int i4 = this.f;
                if (i4 <= i3) {
                    return null;
                }
                if (i4 < i3 + i2) {
                    i2 = i4 - i3;
                }
            }
            this.f7636a.seek(i3);
            byte[] bArr = new byte[i2];
            int read = this.f7636a.read(bArr);
            if (read == this.c) {
                return bArr;
            }
            if (read != -1) {
                byte[] bArr2 = new byte[read];
                System.arraycopy(bArr, 0, bArr2, 0, read);
                return bArr2;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.e = i;
        this.b = i / this.c;
        this.f = i + this.d;
    }

    public int c() {
        try {
            this.f7636a.seek(this.d);
            int i = b.f7634a;
            int i2 = this.d;
            byte[] bArr = new byte[4096];
            while (true) {
                int read = this.f7636a.read(bArr);
                if (read == -1) {
                    return i;
                }
                byte[] bArr2 = null;
                if (-1 != this.e) {
                    int i3 = this.f;
                    if (i3 <= i2) {
                        return i;
                    }
                    if (i3 < this.c + i2) {
                        bArr2 = new byte[i3 - i2];
                    }
                }
                if (4096 == read && bArr2 == null) {
                    bArr2 = bArr;
                } else {
                    if (bArr2 == null) {
                        bArr2 = new byte[read];
                    }
                    System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
                }
                i2 += bArr2.length;
                byte[] a2 = b.a(bArr2, i);
                i = com.crrepa.i0.e.b(a2[0], a2[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int d() {
        int i = this.e;
        if (-1 != i) {
            return i;
        }
        try {
            return (int) (this.f7636a.length() - this.d);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int e() {
        return this.b + 1;
    }
}
