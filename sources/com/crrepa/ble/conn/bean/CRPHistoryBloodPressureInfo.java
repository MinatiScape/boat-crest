package com.crrepa.ble.conn.bean;

import java.util.Date;
/* loaded from: classes9.dex */
public class CRPHistoryBloodPressureInfo {

    /* renamed from: a  reason: collision with root package name */
    public Date f7653a;
    public int b;
    public int c;

    public CRPHistoryBloodPressureInfo(Date date, int i, int i2) {
        this.f7653a = date;
        this.b = i;
        this.c = i2;
    }

    public Date getDate() {
        return this.f7653a;
    }

    public int getDbp() {
        return this.c;
    }

    public int getSbp() {
        return this.b;
    }

    public void setDate(Date date) {
        this.f7653a = date;
    }

    public void setDbp(int i) {
        this.c = i;
    }

    public void setSbp(int i) {
        this.b = i;
    }
}
