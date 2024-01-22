package com.mappls.sdk.navigation.common;

import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public double f12891a;
    public double b;
    public transient double c;
    public transient double d;

    public a() {
    }

    public a(double d, double d2) {
        a(d, d2);
    }

    public final void a(double d, double d2) {
        double d3;
        double d4 = d <= 90.0d ? d : 90.0d;
        if (d4 < -90.0d) {
            d4 = -90.0d;
        }
        this.f12891a = d4;
        if (d2 < -180.0d || d2 > 180.0d) {
            double d5 = (d2 + 180.0d) % 360.0d;
            d3 = d5 < 0.0d ? d5 + 180.0d : d5 - 180.0d;
        } else {
            d3 = d2;
        }
        this.b = d3;
        this.c = Math.toRadians(d);
        this.d = Math.toRadians(d2);
    }

    public final boolean equals(Object obj) {
        if (obj != null && a.class == obj.getClass()) {
            a aVar = (a) obj;
            if (Math.abs(this.f12891a - aVar.f12891a) <= 1.0E-5d) {
                return (Math.abs(this.b - aVar.b) > 1.0E-5d ? 1 : (Math.abs(this.b - aVar.b) == 1.0E-5d ? 0 : -1)) <= 0;
            }
            return false;
        }
        return false;
    }

    public final String toString() {
        StringBuilder a2 = h.a("Lat=");
        a2.append(this.f12891a);
        a2.append(", Lon=");
        a2.append(this.b);
        return a2.toString();
    }
}
