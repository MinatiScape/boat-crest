package com.mappls.sdk.navigation.common;

import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public double f12892a;
    public double b;
    public int c;
    public char d;

    public static void a(a aVar, b bVar) {
        double d = aVar.f12891a;
        double d2 = aVar.b;
        int i = ((int) ((d2 + 180.0d) / 6.0d)) + 1;
        if (d2 == 180.0d) {
            i = 60;
        }
        if (d >= 56.0d && d < 64.0d && d2 >= 3.0d && d2 < 12.0d) {
            i = 32;
        }
        if (d >= 72.0d && d < 84.0d) {
            if (d2 >= 0.0d && d2 < 9.0d) {
                i = 31;
            } else if (d2 >= 9.0d && d2 < 21.0d) {
                i = 33;
            } else if (d2 >= 21.0d && d2 < 33.0d) {
                i = 35;
            } else if (d2 >= 33.0d && d2 < 42.0d) {
                i = 37;
            }
        }
        boolean z = ((float) d) >= 0.0f;
        double d3 = aVar.c;
        double d4 = aVar.d;
        double radians = Math.toRadians((((i - 1) * 6) - 180) + 3);
        double tan = Math.tan(d3);
        double sin = Math.sin(d3);
        double cos = Math.cos(d3);
        double sqrt = 6378137.0d / Math.sqrt(1.0d - ((0.00669438d * sin) * sin));
        double d5 = tan * tan;
        double d6 = 0.006739496752268451d * cos * cos;
        double d7 = (d4 - radians) * cos;
        double d8 = d5 * d5;
        double d9 = (((((((((((72.0d * d6) + ((5.0d - (18.0d * d5)) + d8)) - 0.39089081163157013d) * d7) * d7) * d7) * d7) * d7) / 120.0d) + ((((((1.0d - d5) + d6) * d7) * d7) * d7) / 6.0d) + d7) * sqrt * 0.9996d) + 500000.0d;
        double d10 = 4.0d * d6 * d6;
        double tan2 = (((((((((((((d6 * 600.0d) + ((61.0d - (d5 * 58.0d)) + d8)) - 2.2240339282485886d) * d7) * d7) * d7) * d7) * d7) * d7) / 720.0d) + ((((((d10 + ((9.0d * d6) + (5.0d - d5))) * d7) * d7) * d7) * d7) / 24.0d) + ((d7 * d7) / 2.0d)) * Math.tan(d3) * sqrt) + ((((Math.sin(d3 * 4.0d) * 2.6390466021299826E-6d) + ((0.9983242984503243d * d3) - (Math.sin(d3 * 2.0d) * 0.002514607064228144d))) - (Math.sin(d3 * 6.0d) * 3.418046101696858E-9d)) * 6378137.0d)) * 0.9996d;
        if (!z) {
            tan2 += 1.0E7d;
        }
        double d11 = tan2;
        b bVar2 = bVar == null ? new b() : bVar;
        bVar2.f12892a = d11;
        bVar2.b = d9;
        bVar2.c = i;
        bVar2.d = z ? 'N' : 'S';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            return this.f12892a == bVar.f12892a && this.b == bVar.b && this.c == bVar.c && this.d == bVar.d;
        }
        return false;
    }

    public final String toString() {
        StringBuilder a2 = h.a("Zone_number=");
        a2.append(this.c);
        a2.append(", Hemisphere=");
        a2.append(this.d);
        a2.append(", Northing=");
        a2.append(this.f12892a);
        a2.append(", Easting=");
        a2.append(this.b);
        return a2.toString();
    }
}
