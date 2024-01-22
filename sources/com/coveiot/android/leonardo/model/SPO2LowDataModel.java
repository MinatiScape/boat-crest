package com.coveiot.android.leonardo.model;
/* loaded from: classes2.dex */
public class SPO2LowDataModel {

    /* renamed from: a  reason: collision with root package name */
    public int f4857a;
    public long b;

    public SPO2LowDataModel(int i, long j) {
        this.f4857a = i;
        this.b = j;
    }

    public long getFirstLowTimeStamp() {
        return this.b;
    }

    public int getLowCount() {
        return this.f4857a;
    }

    public void setLowCount(int i) {
        this.f4857a = i;
    }

    public String toString() {
        return "SPO2LowDataModel{lowCount=" + this.f4857a + ", firstLowTimeStamp=" + this.b + '}';
    }
}
