package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class SleepItemData implements ICalculateSleepItem {
    public static final int SLEEP_STATUS_DEEP = 1;
    public static final int SLEEP_STATUS_LIGHT = 2;
    public static final int SLEEP_STATUS_SOBER = 3;

    /* renamed from: a  reason: collision with root package name */
    public int f11977a;
    public long b;
    public long c;

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public long getCalculateEndTime() {
        return this.c;
    }

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public long getCalculateStartTime() {
        return this.b;
    }

    @Override // com.htsmart.wristband2.bean.data.ICalculateSleepItem
    public int getCalculateStatus() {
        return this.f11977a;
    }

    public long getEndTime() {
        return this.c;
    }

    public long getStartTime() {
        return this.b;
    }

    public int getStatus() {
        return this.f11977a;
    }

    public void setEndTime(long j) {
        this.c = j;
    }

    public void setStartTime(long j) {
        this.b = j;
    }

    public void setStatus(int i) {
        this.f11977a = i;
    }
}
