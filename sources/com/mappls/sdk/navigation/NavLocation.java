package com.mappls.sdk.navigation;
/* loaded from: classes11.dex */
public class NavLocation {

    /* renamed from: a  reason: collision with root package name */
    public String f12866a;
    public long b;
    public double c;
    public double d;
    public boolean e;
    public double f;
    public boolean g;
    public float h;
    public boolean i;
    public float j;
    public boolean k;
    public float l;
    public double m;
    public double n;
    public double o;
    public double p;
    public float q;
    public float r;
    public float[] s;

    public NavLocation(NavLocation navLocation) {
        this.b = 0L;
        this.c = 0.0d;
        this.d = 0.0d;
        this.e = false;
        this.f = 0.0d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = 0.0f;
        this.m = 0.0d;
        this.n = 0.0d;
        this.o = 0.0d;
        this.p = 0.0d;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = new float[2];
        set(navLocation);
    }

    public NavLocation(String str) {
        this.b = 0L;
        this.c = 0.0d;
        this.d = 0.0d;
        this.e = false;
        this.f = 0.0d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = 0.0f;
        this.m = 0.0d;
        this.n = 0.0d;
        this.o = 0.0d;
        this.p = 0.0d;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = new float[2];
        this.f12866a = str;
    }

    public static void a(double d, double d2, double d3, double d4, float[] fArr) {
        double d5;
        double d6;
        float a2 = (float) com.mappls.sdk.navigation.util.d.a(d, d2, d3, d4);
        double d7 = (0.017453292519943295d * d4) - (d2 * 0.017453292519943295d);
        double atan = Math.atan(Math.tan(d * 0.017453292519943295d) * 0.996647189328169d);
        double atan2 = Math.atan(Math.tan(d3 * 0.017453292519943295d) * 0.996647189328169d);
        double cos = Math.cos(atan);
        double cos2 = Math.cos(atan2);
        double sin = Math.sin(atan);
        double sin2 = Math.sin(atan2);
        double d8 = cos * cos2;
        double d9 = sin * sin2;
        double d10 = d7;
        int i = 0;
        double d11 = 0.0d;
        double d12 = 0.0d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        double d15 = 0.0d;
        while (true) {
            if (i >= 20) {
                d5 = sin;
                d6 = sin2;
                break;
            }
            d14 = Math.cos(d10);
            d15 = Math.sin(d10);
            double d16 = cos2 * d15;
            double d17 = (cos * sin2) - ((sin * cos2) * d14);
            d5 = sin;
            double sqrt = Math.sqrt((d17 * d17) + (d16 * d16));
            d6 = sin2;
            double d18 = (d8 * d14) + d9;
            d12 = Math.atan2(sqrt, d18);
            double d19 = sqrt == 0.0d ? 0.0d : (d8 * d15) / sqrt;
            double d20 = 1.0d - (d19 * d19);
            double d21 = d20 == 0.0d ? 0.0d : d18 - ((d9 * 2.0d) / d20);
            double d22 = 0.006739496756586903d * d20;
            double d23 = ((((((320.0d - (175.0d * d22)) * d22) - 768.0d) * d22) + 4096.0d) * (d22 / 16384.0d)) + 1.0d;
            double d24 = (((((74.0d - (47.0d * d22)) * d22) - 128.0d) * d22) + 256.0d) * (d22 / 1024.0d);
            double d25 = (((4.0d - (d20 * 3.0d)) * 0.0033528106718309896d) + 4.0d) * 2.0955066698943685E-4d * d20;
            double d26 = d21 * d21;
            double d27 = ((((((d26 * 2.0d) - 1.0d) * d18) - (((d26 * 4.0d) - 3.0d) * ((((sqrt * 4.0d) * sqrt) - 3.0d) * ((d24 / 6.0d) * d21)))) * (d24 / 4.0d)) + d21) * d24 * sqrt;
            double d28 = ((((((((2.0d * d21) * d21) - 1.0d) * d25 * d18) + d21) * sqrt * d25) + d12) * (1.0d - d25) * 0.0033528106718309896d * d19) + d7;
            if (Math.abs((d28 - d10) / d28) < 1.0E-12d) {
                d13 = d27;
                d11 = d23;
                break;
            }
            i++;
            sin = d5;
            sin2 = d6;
            d10 = d28;
            d13 = d27;
            d11 = d23;
        }
        fArr[0] = (float) (6356752.3142d * d11 * (d12 - d13));
        if (fArr.length > 1) {
            double d29 = cos * d6;
            double d30 = d5;
            fArr[1] = (float) (((float) Math.atan2(cos2 * d15, d29 - ((d30 * cos2) * d14))) * 57.29577951308232d);
            if (fArr.length > 2) {
                fArr[2] = (float) (((float) Math.atan2(cos * d15, (d29 * d14) + ((-d30) * cos2))) * 57.29577951308232d);
            }
        }
        fArr[0] = a2;
    }

    public static void distanceBetween(double d, double d2, double d3, double d4, float[] fArr) {
        if (fArr == null || fArr.length < 1) {
            throw new IllegalArgumentException("results is null or has length < 1");
        }
        a(d, d2, d3, d4, fArr);
    }

    public float bearingTo(NavLocation navLocation) {
        float f;
        synchronized (this.s) {
            double d = this.c;
            if (d != this.m || this.d != this.n || navLocation.c != this.o || navLocation.d != this.p) {
                a(d, this.d, navLocation.c, navLocation.d, this.s);
                this.m = this.c;
                this.n = this.d;
                this.o = navLocation.c;
                this.p = navLocation.d;
                float[] fArr = this.s;
                this.q = fArr[0];
                this.r = fArr[1];
            }
            f = this.r;
        }
        return f;
    }

    public float distanceTo(NavLocation navLocation) {
        float f;
        synchronized (this.s) {
            double d = this.c;
            if (d != this.m || this.d != this.n || navLocation.c != this.o || navLocation.d != this.p) {
                a(d, this.d, navLocation.c, navLocation.d, this.s);
                this.m = this.c;
                this.n = this.d;
                this.o = navLocation.c;
                this.p = navLocation.d;
                float[] fArr = this.s;
                this.q = fArr[0];
                this.r = fArr[1];
            }
            f = this.q;
        }
        return f;
    }

    public float getAccuracy() {
        return this.l;
    }

    public double getAltitude() {
        return this.f;
    }

    public float getBearing() {
        return this.j;
    }

    public double getLatitude() {
        return this.c;
    }

    public double getLongitude() {
        return this.d;
    }

    public String getProvider() {
        return this.f12866a;
    }

    public float getSpeed() {
        return this.h;
    }

    public long getTime() {
        return this.b;
    }

    public boolean hasAccuracy() {
        return this.k;
    }

    public boolean hasAltitude() {
        return this.e;
    }

    public boolean hasBearing() {
        return this.i;
    }

    public boolean hasSpeed() {
        return this.g;
    }

    public void removeAccuracy() {
        this.l = 0.0f;
        this.k = false;
    }

    public void removeAltitude() {
        this.f = 0.0d;
        this.e = false;
    }

    public void removeBearing() {
        this.j = 0.0f;
        this.i = false;
    }

    public void removeSpeed() {
        this.h = 0.0f;
        this.g = false;
    }

    public void reset() {
        this.f12866a = null;
        this.b = 0L;
        this.c = 0.0d;
        this.d = 0.0d;
        this.e = false;
        this.f = 0.0d;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = 0.0f;
    }

    public void set(NavLocation navLocation) {
        this.f12866a = navLocation.f12866a;
        this.b = navLocation.b;
        this.c = navLocation.c;
        this.d = navLocation.d;
        this.e = navLocation.e;
        this.f = navLocation.f;
        this.g = navLocation.g;
        this.h = navLocation.h;
        this.i = navLocation.i;
        this.j = navLocation.j;
        this.k = navLocation.k;
        this.l = navLocation.l;
    }

    public void setAccuracy(float f) {
        this.l = f;
        this.k = true;
    }

    public void setAltitude(double d) {
        this.f = d;
        this.e = true;
    }

    public void setBearing(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        while (f >= 360.0f) {
            f -= 360.0f;
        }
        this.j = f;
        this.i = true;
    }

    public void setLatitude(double d) {
        this.c = d;
    }

    public void setLongitude(double d) {
        this.d = d;
    }

    public void setProvider(String str) {
        this.f12866a = str;
    }

    public void setSpeed(float f) {
        this.h = f;
        this.g = true;
    }

    public void setTime(long j) {
        this.b = j;
    }

    public String toString() {
        StringBuilder a2 = h.a("Location[mProvider=");
        a2.append(this.f12866a);
        a2.append(",mTime=");
        a2.append(this.b);
        a2.append(",mLatitude=");
        a2.append(this.c);
        a2.append(",mLongitude=");
        a2.append(this.d);
        a2.append(",mHasAltitude=");
        a2.append(this.e);
        a2.append(",mAltitude=");
        a2.append(this.f);
        a2.append(",mHasSpeed=");
        a2.append(this.g);
        a2.append(",mSpeed=");
        a2.append(this.h);
        a2.append(",mHasBearing=");
        a2.append(this.i);
        a2.append(",mBearing=");
        a2.append(this.j);
        a2.append(",mHasAccuracy=");
        a2.append(this.k);
        a2.append(",mAccuracy=");
        a2.append(this.l);
        return a2.toString();
    }
}
