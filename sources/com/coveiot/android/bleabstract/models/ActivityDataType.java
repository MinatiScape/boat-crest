package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class ActivityDataType {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3390a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;

    public ActivityDataType(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this.f3390a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.f3390a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = z5;
        this.f = z6;
        this.g = z7;
        this.h = z8;
        this.i = z9;
        this.j = z10;
    }

    public boolean isALTITUDE() {
        return this.g;
    }

    public boolean isCALORIE() {
        return this.i;
    }

    public boolean isDISTANCE() {
        return this.j;
    }

    public boolean isGPS() {
        return this.h;
    }

    public boolean isHRS() {
        return this.f3390a;
    }

    public boolean isPACE() {
        return this.f;
    }

    public boolean isSPEED() {
        return this.e;
    }

    public boolean isSTEP() {
        return this.b;
    }

    public boolean isSTEP_FREQ() {
        return this.d;
    }

    public boolean isSTEP_STRIDE() {
        return this.c;
    }
}
