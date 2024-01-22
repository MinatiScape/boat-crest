package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPUserInfo {
    public static final int FEMALE = 1;
    public static final int MALE = 0;

    /* renamed from: a  reason: collision with root package name */
    public int f7673a;
    public int b;
    public int c;
    public int d;

    public CRPUserInfo(int i, int i2, int i3, int i4) {
        this.f7673a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getAge() {
        return this.d;
    }

    public int getGender() {
        return this.c;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWeight() {
        return this.f7673a;
    }

    public void setAge(int i) {
        this.d = i;
    }

    public void setGender(int i) {
        this.c = i;
    }

    public void setHeight(int i) {
        this.b = i;
    }

    public void setWeight(int i) {
        this.f7673a = i;
    }
}
