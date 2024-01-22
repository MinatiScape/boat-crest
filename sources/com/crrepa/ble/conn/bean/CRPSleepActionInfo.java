package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPSleepActionInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7664a;
    public List<Integer> b;

    public CRPSleepActionInfo() {
    }

    public CRPSleepActionInfo(int i, List<Integer> list) {
        this.f7664a = i;
        this.b = list;
    }

    public List<Integer> getActionList() {
        return this.b;
    }

    public int getHour() {
        return this.f7664a;
    }

    public void setActionList(List<Integer> list) {
        this.b = list;
    }

    public void setHour(int i) {
        this.f7664a = i;
    }
}
