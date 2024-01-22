package com.realsil.sdk.dfu.c;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public int f13599a;
    public int b;
    public int c;
    public int d;

    public b(int i, int i2, int i3, int i4) {
        this.f13599a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static b a(int i, int i2) {
        if (i == 1) {
            return new b(i2 & 255, (i2 >> 8) & 255, (i2 >> 16) & 255, (i2 >> 24) & 255);
        }
        if (i == 515) {
            return new b((i2 >> 24) & 255, (i2 >> 16) & 255, (i2 >> 8) & 255, i2 & 255);
        }
        if (i == 516) {
            return new b((i2 >> 24) & 255, (i2 >> 16) & 255, (i2 >> 8) & 255, i2 & 255);
        }
        if (i == 2) {
            return new b((i2 >> 24) & 255, (i2 >> 16) & 255, (i2 >> 8) & 255, i2 & 255);
        }
        if (i == 3) {
            return new b(i2 & 15, (i2 >> 4) & 255, (i2 >> 12) & 32767, (i2 >> 27) & 31);
        }
        if (i == 5) {
            return new b(i2 & 15, (i2 >> 4) & 255, (i2 >> 12) & 511, (i2 >> 21) & 32767);
        }
        if (i == 4) {
            return new b(0, 0, 0, i2);
        }
        if (i == 7) {
            return new b(0, 0, 0, i2);
        }
        if (i == 514) {
            return new b((i2 >> 8) & 255, i2 & 255, (i2 >> 24) & 255, (i2 >> 16) & 255);
        }
        return new b(0, 0, 0, i2);
    }

    public int a(b bVar) {
        int i = this.f13599a;
        int i2 = bVar.f13599a;
        if (i > i2) {
            return 1;
        }
        if (i < i2) {
            return -1;
        }
        int i3 = this.b;
        int i4 = bVar.b;
        if (i3 > i4) {
            return 1;
        }
        if (i3 < i4) {
            return -1;
        }
        int i5 = this.c;
        int i6 = bVar.c;
        if (i5 > i6) {
            return 1;
        }
        if (i5 < i6) {
            return -1;
        }
        int i7 = this.d;
        int i8 = bVar.d;
        if (i7 > i8) {
            return 1;
        }
        return i7 == i8 ? 0 : -1;
    }
}
