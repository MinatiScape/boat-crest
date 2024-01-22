package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPStepsCategoryInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7668a;
    public int b;
    public List<Integer> c;

    public CRPStepsCategoryInfo(int i, int i2, List<Integer> list) {
        this.f7668a = i;
        this.b = i2;
        this.c = list;
    }

    public int getDateType() {
        return this.f7668a;
    }

    public List<Integer> getStepsList() {
        return this.c;
    }

    public int getTimeInterval() {
        return this.b;
    }
}
