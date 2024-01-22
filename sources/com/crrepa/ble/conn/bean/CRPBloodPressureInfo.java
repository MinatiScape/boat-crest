package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPBloodPressureInfo {

    /* renamed from: a  reason: collision with root package name */
    public long f7640a;
    public TimeType b;
    public List<BpBean> c;
    public int d;

    /* loaded from: classes9.dex */
    public static class BpBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7641a;
        public int b;

        public BpBean(int i, int i2) {
            this.f7641a = i;
            this.b = i2;
        }

        public int getDbp() {
            return this.b;
        }

        public int getSbp() {
            return this.f7641a;
        }

        public void setDbp(int i) {
            this.b = i;
        }

        public void setSbp(int i) {
            this.f7641a = i;
        }
    }

    /* loaded from: classes9.dex */
    public enum TimeType {
        TODAY,
        YESTERDAY
    }

    public CRPBloodPressureInfo() {
    }

    public CRPBloodPressureInfo(long j, TimeType timeType, List<BpBean> list, int i) {
        this.f7640a = j;
        this.b = timeType;
        this.c = list;
        this.d = i;
    }

    public List<BpBean> getList() {
        return this.c;
    }

    public long getStartTime() {
        return this.f7640a;
    }

    public int getTimeInterval() {
        return this.d;
    }

    public TimeType getType() {
        return this.b;
    }

    public void setList(List<BpBean> list) {
        this.c = list;
    }

    public void setStartTime(long j) {
        this.f7640a = j;
    }

    public void setTimeInterval(int i) {
        this.d = i;
    }

    public void setType(TimeType timeType) {
        this.b = timeType;
    }
}
