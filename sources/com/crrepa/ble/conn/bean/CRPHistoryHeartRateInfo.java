package com.crrepa.ble.conn.bean;

import java.util.Date;
/* loaded from: classes9.dex */
public class CRPHistoryHeartRateInfo {

    /* renamed from: a  reason: collision with root package name */
    public Date f7654a;
    public int b;

    public CRPHistoryHeartRateInfo(Date date, int i) {
        this.f7654a = date;
        this.b = i;
    }

    public Date getDate() {
        return this.f7654a;
    }

    public int getHr() {
        return this.b;
    }

    public void setDate(Date date) {
        this.f7654a = date;
    }

    public void setHr(int i) {
        this.b = i;
    }
}
