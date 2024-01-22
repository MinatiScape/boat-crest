package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
/* loaded from: classes11.dex */
public final class Version {
    public static final Version[] h = a();

    /* renamed from: a  reason: collision with root package name */
    public final int f11800a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final c f;
    public final int g;

    /* loaded from: classes11.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f11801a;
        public final int b;

        public int a() {
            return this.f11801a;
        }

        public int b() {
            return this.b;
        }

        public b(int i, int i2) {
            this.f11801a = i;
            this.b = i2;
        }
    }

    public Version(int i, int i2, int i3, int i4, int i5, c cVar) {
        b[] a2;
        this.f11800a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = cVar;
        int b2 = cVar.b();
        int i6 = 0;
        for (b bVar : cVar.a()) {
            i6 += bVar.a() * (bVar.b() + b2);
        }
        this.g = i6;
    }

    public static Version[] a() {
        return new Version[]{new Version(1, 10, 10, 8, 8, new c(5, new b(1, 3))), new Version(2, 12, 12, 10, 10, new c(7, new b(1, 5))), new Version(3, 14, 14, 12, 12, new c(10, new b(1, 8))), new Version(4, 16, 16, 14, 14, new c(12, new b(1, 12))), new Version(5, 18, 18, 16, 16, new c(14, new b(1, 18))), new Version(6, 20, 20, 18, 18, new c(18, new b(1, 22))), new Version(7, 22, 22, 20, 20, new c(20, new b(1, 30))), new Version(8, 24, 24, 22, 22, new c(24, new b(1, 36))), new Version(9, 26, 26, 24, 24, new c(28, new b(1, 44))), new Version(10, 32, 32, 14, 14, new c(36, new b(1, 62))), new Version(11, 36, 36, 16, 16, new c(42, new b(1, 86))), new Version(12, 40, 40, 18, 18, new c(48, new b(1, 114))), new Version(13, 44, 44, 20, 20, new c(56, new b(1, 144))), new Version(14, 48, 48, 22, 22, new c(68, new b(1, 174))), new Version(15, 52, 52, 24, 24, new c(42, new b(2, 102))), new Version(16, 64, 64, 14, 14, new c(56, new b(2, 140))), new Version(17, 72, 72, 16, 16, new c(36, new b(4, 92))), new Version(18, 80, 80, 18, 18, new c(48, new b(4, 114))), new Version(19, 88, 88, 20, 20, new c(56, new b(4, 144))), new Version(20, 96, 96, 22, 22, new c(68, new b(4, 174))), new Version(21, 104, 104, 24, 24, new c(56, new b(6, 136))), new Version(22, 120, 120, 18, 18, new c(68, new b(6, 175))), new Version(23, 132, 132, 20, 20, new c(62, new b(8, 163))), new Version(24, 144, 144, 22, 22, new c(62, new b(8, 156), new b(2, 155))), new Version(25, 8, 18, 6, 16, new c(7, new b(1, 5))), new Version(26, 8, 32, 6, 14, new c(11, new b(1, 10))), new Version(27, 12, 26, 10, 24, new c(14, new b(1, 16))), new Version(28, 12, 36, 10, 16, new c(18, new b(1, 22))), new Version(29, 16, 36, 14, 16, new c(24, new b(1, 32))), new Version(30, 16, 48, 14, 22, new c(28, new b(1, 49)))};
    }

    public static Version getVersionForDimensions(int i, int i2) throws FormatException {
        Version[] versionArr;
        if ((i & 1) == 0 && (i2 & 1) == 0) {
            for (Version version : h) {
                if (version.b == i && version.c == i2) {
                    return version;
                }
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    public c b() {
        return this.f;
    }

    public int getDataRegionSizeColumns() {
        return this.e;
    }

    public int getDataRegionSizeRows() {
        return this.d;
    }

    public int getSymbolSizeColumns() {
        return this.c;
    }

    public int getSymbolSizeRows() {
        return this.b;
    }

    public int getTotalCodewords() {
        return this.g;
    }

    public int getVersionNumber() {
        return this.f11800a;
    }

    public String toString() {
        return String.valueOf(this.f11800a);
    }

    /* loaded from: classes11.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f11802a;
        public final b[] b;

        public b[] a() {
            return this.b;
        }

        public int b() {
            return this.f11802a;
        }

        public c(int i, b bVar) {
            this.f11802a = i;
            this.b = new b[]{bVar};
        }

        public c(int i, b bVar, b bVar2) {
            this.f11802a = i;
            this.b = new b[]{bVar, bVar2};
        }
    }
}
