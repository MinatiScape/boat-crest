package com.crrepa.ble.conn.bean;

import com.crrepa.ble.conn.type.CRPTempTimeType;
import java.util.List;
/* loaded from: classes9.dex */
public class CRPTempInfo {
    public static final int DEFAULT_MEASURE_INTERVAL = 30;

    /* renamed from: a  reason: collision with root package name */
    public CRPTempTimeType f7670a;
    public long b;
    public int c = 30;
    public List<Float> d;

    public CRPTempInfo(CRPTempTimeType cRPTempTimeType, long j, List<Float> list) {
        this.f7670a = cRPTempTimeType;
        this.b = j;
        this.d = list;
    }

    public int getMeasureInterval() {
        return this.c;
    }

    public long getStartTime() {
        return this.b;
    }

    public List<Float> getTempList() {
        return this.d;
    }

    public CRPTempTimeType getType() {
        return this.f7670a;
    }

    public void setMeasureInterval(int i) {
        this.c = i;
    }

    public void setStartTime(long j) {
        this.b = j;
    }

    public void setTempList(List<Float> list) {
        this.d = list;
    }

    public void setType(CRPTempTimeType cRPTempTimeType) {
        this.f7670a = cRPTempTimeType;
    }
}
