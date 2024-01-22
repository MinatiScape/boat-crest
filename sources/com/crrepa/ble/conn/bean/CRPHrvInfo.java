package com.crrepa.ble.conn.bean;

import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class CRPHrvInfo {

    /* renamed from: a  reason: collision with root package name */
    public Date f7656a;
    public int b;
    public List<Integer> c;

    public CRPHrvInfo() {
    }

    public CRPHrvInfo(Date date, int i, List<Integer> list) {
        this.f7656a = date;
        this.b = i;
        this.c = list;
    }

    public int getAtiveLevel() {
        return this.b;
    }

    public Date getDate() {
        return this.f7656a;
    }

    public List<Integer> getRriList() {
        return this.c;
    }

    public void setAtiveLevel(int i) {
        this.b = i;
    }

    public void setDate(Date date) {
        this.f7656a = date;
    }

    public void setRriList(List<Integer> list) {
        this.c = list;
    }
}
