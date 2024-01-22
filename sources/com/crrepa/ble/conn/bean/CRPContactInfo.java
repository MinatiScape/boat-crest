package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPContactInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7643a;
    public int b;
    public int c;
    public int d;
    public String e;
    public String f;

    public CRPContactInfo() {
    }

    public CRPContactInfo(int i, int i2, int i3, int i4, String str, String str2) {
        this.f7643a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = str;
        this.f = str2;
    }

    public int getAddress() {
        return this.d;
    }

    public int getHeight() {
        return this.c;
    }

    public int getId() {
        return this.f7643a;
    }

    public String getName() {
        return this.e;
    }

    public String getNumber() {
        return this.f;
    }

    public int getWidth() {
        return this.b;
    }

    public void setAddress(int i) {
        this.d = i;
    }

    public void setHeight(int i) {
        this.c = i;
    }

    public void setId(int i) {
        this.f7643a = i;
    }

    public void setName(String str) {
        this.e = str;
    }

    public void setNumber(String str) {
        this.f = str;
    }

    public void setWidth(int i) {
        this.b = i;
    }
}
