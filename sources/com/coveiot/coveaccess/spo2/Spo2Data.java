package com.coveiot.coveaccess.spo2;

import java.util.List;
/* loaded from: classes8.dex */
public class Spo2Data {

    /* renamed from: a  reason: collision with root package name */
    public String f6738a;
    public String b;
    public String c;
    public String d;
    public int e;
    public int f;
    public Double g;
    public TimeLog h;

    /* loaded from: classes8.dex */
    public static class TimeLog {

        /* renamed from: a  reason: collision with root package name */
        public List<Log> f6739a = null;

        /* loaded from: classes8.dex */
        public static class Log {

            /* renamed from: a  reason: collision with root package name */
            public String f6740a;
            public String b;
            public int c;
            public int d;
            public Double e;
            public List<Integer> f = null;

            public Double getAvg() {
                return this.e;
            }

            public List<Integer> getCodedValues() {
                return this.f;
            }

            public String getEndTime() {
                return this.b;
            }

            public int getMax() {
                return this.d;
            }

            public int getMin() {
                return this.c;
            }

            public String getStartTime() {
                return this.f6740a;
            }

            public void setAvg(Double d) {
                this.e = d;
            }

            public void setCodedValues(List<Integer> list) {
                this.f = list;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setMax(int i) {
                this.d = i;
            }

            public void setMin(int i) {
                this.c = i;
            }

            public void setStartTime(String str) {
                this.f6740a = str;
            }
        }

        public List<Log> getLogs() {
            return this.f6739a;
        }

        public void setLogs(List<Log> list) {
            this.f6739a = list;
        }
    }

    public Double getAvg() {
        return this.g;
    }

    public String getBaseUnit() {
        return this.d;
    }

    public String getDate() {
        return this.b;
    }

    public int getMax() {
        return this.f;
    }

    public int getMin() {
        return this.e;
    }

    public TimeLog getTimeLog() {
        return this.h;
    }

    public String getType() {
        return this.f6738a;
    }

    public String getTzOffset() {
        return this.c;
    }

    public void setAvg(Double d) {
        this.g = d;
    }

    public void setBaseUnit(String str) {
        this.d = str;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setMax(int i) {
        this.f = i;
    }

    public void setMin(int i) {
        this.e = i;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.h = timeLog;
    }

    public void setType(String str) {
        this.f6738a = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }
}
