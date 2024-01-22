package com.coveiot.coveaccess.temperature.model;

import java.util.List;
/* loaded from: classes8.dex */
public class TemperatureData {

    /* renamed from: a  reason: collision with root package name */
    public String f6796a;
    public String b;
    public String c;
    public String d;
    public Double e;
    public Double f;
    public Double g;
    public TimeLog h;

    /* loaded from: classes8.dex */
    public static class TimeLog {

        /* renamed from: a  reason: collision with root package name */
        public List<Log> f6797a = null;

        /* loaded from: classes8.dex */
        public static class Log {

            /* renamed from: a  reason: collision with root package name */
            public String f6798a;
            public String b;
            public Double c;
            public Double d;
            public Double e;
            public List<Double> f = null;
            public List<Double> g = null;

            public Double getAvg() {
                return this.e;
            }

            public List<Double> getCodedValues() {
                return this.f;
            }

            public String getEndTime() {
                return this.b;
            }

            public Double getMax() {
                return this.d;
            }

            public Double getMin() {
                return this.c;
            }

            public String getStartTime() {
                return this.f6798a;
            }

            public List<Double> getSurfaceTempValues() {
                return this.g;
            }

            public void setAvg(Double d) {
                this.e = d;
            }

            public void setCodedValues(List<Double> list) {
                this.f = list;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setMax(Double d) {
                this.d = d;
            }

            public void setMin(Double d) {
                this.c = d;
            }

            public void setStartTime(String str) {
                this.f6798a = str;
            }

            public void setSurfaceTempValues(List<Double> list) {
                this.g = list;
            }
        }

        public List<Log> getLogs() {
            return this.f6797a;
        }

        public void setLogs(List<Log> list) {
            this.f6797a = list;
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

    public Double getMax() {
        return this.f;
    }

    public Double getMin() {
        return this.e;
    }

    public TimeLog getTimeLog() {
        return this.h;
    }

    public String getType() {
        return this.f6796a;
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

    public void setMax(Double d) {
        this.f = d;
    }

    public void setMin(Double d) {
        this.e = d;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.h = timeLog;
    }

    public void setType(String str) {
        this.f6796a = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }
}
