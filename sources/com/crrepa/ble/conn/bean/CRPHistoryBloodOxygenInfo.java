package com.crrepa.ble.conn.bean;

import java.util.Date;
/* loaded from: classes9.dex */
public class CRPHistoryBloodOxygenInfo {

    /* renamed from: a  reason: collision with root package name */
    public Date f7652a;
    public int b;

    public CRPHistoryBloodOxygenInfo(Date date, int i) {
        this.f7652a = date;
        this.b = i;
    }

    public int getBo() {
        return this.b;
    }

    public Date getDate() {
        return this.f7652a;
    }

    public void setBo(int i) {
        this.b = i;
    }

    public void setDate(Date date) {
        this.f7652a = date;
    }
}
