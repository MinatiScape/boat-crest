package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public abstract class CustomReminderAbstract {

    /* renamed from: a  reason: collision with root package name */
    public int f3421a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public String j;
    public boolean k;

    public CustomReminderAbstract(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i2, String str, boolean z8) {
        this.f3421a = i;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = z5;
        this.g = z6;
        this.h = z7;
        this.i = i2;
        this.j = str;
        this.k = z8;
    }

    public String getDescription() {
        return this.j;
    }

    public int getImageId() {
        return this.f3421a;
    }

    public int getVibrationSeqID() {
        return this.i;
    }

    public boolean isFriday() {
        return this.g;
    }

    public boolean isMonday() {
        return this.c;
    }

    public boolean isReminderOn() {
        return this.k;
    }

    public boolean isSaturday() {
        return this.h;
    }

    public boolean isSunday() {
        return this.b;
    }

    public boolean isThursday() {
        return this.f;
    }

    public boolean isTuesday() {
        return this.d;
    }

    public boolean isWednesday() {
        return this.e;
    }
}
