package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class ExerciseTarget {

    /* renamed from: a  reason: collision with root package name */
    public int f11959a;
    public int b;
    public int c;
    public long d;

    public ExerciseTarget() {
    }

    public ExerciseTarget(int i, int i2, int i3, long j) {
        this.f11959a = i;
        this.b = i2;
        this.c = i3;
        this.d = j;
    }

    public int getCalorie() {
        return this.c;
    }

    public int getDistance() {
        return this.b;
    }

    public int getStep() {
        return this.f11959a;
    }

    public long getTimestamp() {
        return this.d;
    }

    public void setCalorie(int i) {
        this.c = i;
    }

    public void setDistance(int i) {
        this.b = i;
    }

    public void setStep(int i) {
        this.f11959a = i;
    }

    public void setTimestamp(long j) {
        this.d = j;
    }
}
