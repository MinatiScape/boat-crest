package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPTrainingInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7672a;
    public long b;
    public long c;
    public int d;
    public int e;
    public int f;
    public int g;
    public List<Integer> h;

    public int getCalories() {
        return this.g;
    }

    public int getDistance() {
        return this.f;
    }

    public long getEndTime() {
        return this.c;
    }

    public List<Integer> getHrList() {
        return this.h;
    }

    public long getStartTime() {
        return this.b;
    }

    public int getSteps() {
        return this.e;
    }

    public int getType() {
        return this.f7672a;
    }

    public int getValidTime() {
        return this.d;
    }

    public void setCalories(int i) {
        this.g = i;
    }

    public void setDistance(int i) {
        this.f = i;
    }

    public void setEndTime(long j) {
        this.c = j;
    }

    public void setHrList(List<Integer> list) {
        this.h = list;
    }

    public void setStartTime(long j) {
        this.b = j;
    }

    public void setSteps(int i) {
        this.e = i;
    }

    public void setType(int i) {
        this.f7672a = i;
    }

    public void setValidTime(int i) {
        this.d = i;
    }
}
