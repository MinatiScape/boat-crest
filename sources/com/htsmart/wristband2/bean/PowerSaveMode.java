package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class PowerSaveMode {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11965a;
    public int b;
    public int c;

    public PowerSaveMode() {
    }

    public PowerSaveMode(boolean z, int i, int i2) {
        this.f11965a = z;
        this.b = i;
        this.c = i2;
    }

    public int getEnd() {
        return this.c;
    }

    public int getStart() {
        return this.b;
    }

    public boolean isEnabled() {
        return this.f11965a;
    }

    public void setEnabled(boolean z) {
        this.f11965a = z;
    }

    public void setEnd(int i) {
        this.c = i;
    }

    public void setStart(int i) {
        this.b = i;
    }
}
