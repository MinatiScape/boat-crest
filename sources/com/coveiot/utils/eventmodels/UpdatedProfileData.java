package com.coveiot.utils.eventmodels;
/* loaded from: classes9.dex */
public final class UpdatedProfileData {

    /* renamed from: a  reason: collision with root package name */
    public int f7620a;
    public int b;
    public double c;
    public int d;

    public UpdatedProfileData() {
    }

    public int getAge() {
        return this.f7620a;
    }

    public int getGender() {
        return this.d;
    }

    public int getHeight() {
        return this.b;
    }

    public double getWeight() {
        return this.c;
    }

    public void setAge(int i) {
        this.f7620a = i;
    }

    public void setGender(int i) {
        this.d = i;
    }

    public void setHeight(int i) {
        this.b = i;
    }

    public void setWeight(double d) {
        this.c = d;
    }

    public UpdatedProfileData(int i, int i2, double d, int i3) {
        this.f7620a = i;
        this.b = i2;
        this.c = d;
        this.d = i3;
    }
}
