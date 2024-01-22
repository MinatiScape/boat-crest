package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class GpsItem {

    /* renamed from: a  reason: collision with root package name */
    public int f11976a;
    public double b;
    public double c;
    public float d;
    public int e;
    public boolean f;

    public float getAltitude() {
        return this.d;
    }

    public int getDuration() {
        return this.f11976a;
    }

    public double getLat() {
        return this.c;
    }

    public double getLng() {
        return this.b;
    }

    public int getSatellites() {
        return this.e;
    }

    public boolean isRestart() {
        return this.f;
    }

    public void setAltitude(float f) {
        this.d = f;
    }

    public void setDuration(int i) {
        this.f11976a = i;
    }

    public void setLat(double d) {
        this.c = d;
    }

    public void setLng(double d) {
        this.b = d;
    }

    public void setRestart(boolean z) {
        this.f = z;
    }

    public void setSatellites(int i) {
        this.e = i;
    }
}
