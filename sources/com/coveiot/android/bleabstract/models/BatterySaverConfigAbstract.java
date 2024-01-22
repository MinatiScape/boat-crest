package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class BatterySaverConfigAbstract {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3401a;
    public int b;
    public int c;
    public boolean d;

    public BatterySaverConfigAbstract(boolean z, int i, int i2, boolean z2) {
        this.f3401a = z;
        this.b = i;
        this.c = i2;
        this.d = z2;
    }

    public int getAutoStart() {
        return this.c;
    }

    public int getMode() {
        return this.b;
    }

    public boolean isActive() {
        return this.d;
    }

    public boolean isEnabled() {
        return this.f3401a;
    }

    public void setAutoStart(int i) {
        this.c = i;
    }

    public void setEnabled(boolean z) {
        this.f3401a = z;
    }

    public void setMode(int i) {
        this.b = i;
    }

    public String toString() {
        return "BatterySaverConfigAbstract{isEnabled=" + this.f3401a + ", mode=" + this.b + ", autoStart=" + this.c + ", isActive=" + this.d + '}';
    }
}
