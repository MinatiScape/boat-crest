package com.crrepa.ble.conn.bean;

import com.crrepa.ble.conn.type.CRPBloodOxygenTimeType;
import java.util.List;
/* loaded from: classes9.dex */
public class CRPBloodOxygenInfo {

    /* renamed from: a  reason: collision with root package name */
    public long f7639a;
    public CRPBloodOxygenTimeType b;
    public List<Integer> c;
    public int d;

    public CRPBloodOxygenInfo() {
    }

    public CRPBloodOxygenInfo(long j, CRPBloodOxygenTimeType cRPBloodOxygenTimeType, List<Integer> list, int i) {
        this.f7639a = j;
        this.b = cRPBloodOxygenTimeType;
        this.c = list;
        this.d = i;
    }

    public List<Integer> getList() {
        return this.c;
    }

    public long getStartTime() {
        return this.f7639a;
    }

    public int getTimeInterval() {
        return this.d;
    }

    public CRPBloodOxygenTimeType getType() {
        return this.b;
    }

    public void setList(List<Integer> list) {
        this.c = list;
    }

    public void setStartTime(long j) {
        this.f7639a = j;
    }

    public void setTimeInterval(int i) {
        this.d = i;
    }

    public void setType(CRPBloodOxygenTimeType cRPBloodOxygenTimeType) {
        this.b = cRPBloodOxygenTimeType;
    }
}
