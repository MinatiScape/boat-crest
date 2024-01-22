package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPHeartRateInfo {

    /* renamed from: a  reason: collision with root package name */
    public long f7651a;
    public List<Integer> b;
    public int c;
    public HeartRateType d;

    /* loaded from: classes9.dex */
    public enum HeartRateType {
        PART_HEART_RATE,
        TODAY_HEART_RATE,
        YESTERDAY_HEART_RATE
    }

    public CRPHeartRateInfo(long j, List<Integer> list, int i, HeartRateType heartRateType) {
        this.f7651a = j;
        this.b = list;
        this.c = i;
        this.d = heartRateType;
    }

    public List<Integer> getHeartRateList() {
        return this.b;
    }

    public HeartRateType getHeartRateType() {
        return this.d;
    }

    public long getStartTime() {
        return this.f7651a;
    }

    public int getTimeInterval() {
        return this.c;
    }
}
