package com.crrepa.k0;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7746a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public boolean h;

    public e(byte[] bArr, int i) {
        int length = bArr.length;
        this.c = length;
        byte[] bArr2 = new byte[length];
        this.f7746a = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, length);
        this.b = i;
        this.d = 0;
        this.h = false;
        this.e = 1;
        int i2 = this.c;
        int i3 = i2 % 548;
        int i4 = i2 / 548;
        this.f = i3 != 0 ? i4 + 1 : i4;
    }

    public int a() {
        return this.e;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public byte[] a(int i) {
        byte[] bArr = new byte[i];
        int i2 = this.d;
        int i3 = i + i2;
        int i4 = this.c;
        if (i3 <= i4) {
            System.arraycopy(this.f7746a, i2, bArr, 0, i);
            this.d += i;
        } else {
            int i5 = i4 - i2;
            System.arraycopy(this.f7746a, i2, bArr, 0, i5);
            this.d += i5;
        }
        this.e++;
        return bArr;
    }

    public int b() {
        return this.g;
    }

    public void b(int i) {
        this.e = i;
    }

    public void c(int i) {
        if (i == 0) {
            this.g = 0;
            return;
        }
        int i2 = this.f;
        int i3 = i2 % i;
        int i4 = i2 / i;
        if (i3 != 0) {
            i4++;
        }
        this.g = i4;
    }

    public byte[] c() {
        return this.f7746a;
    }

    public int d() {
        return this.b;
    }

    public void d(int i) {
        this.d = i;
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.f7746a, ((e) obj).f7746a);
    }

    public boolean f() {
        return this.h;
    }

    public int g() {
        return this.f;
    }

    public int h() {
        return this.d / this.c;
    }

    public int hashCode() {
        return (Objects.hash(Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g)) * 31) + Arrays.hashCode(this.f7746a);
    }

    public int i() {
        return this.d;
    }
}
