package com.coveiot.coveaccess.hrv.model;

import java.util.List;
/* loaded from: classes8.dex */
public class HrvData {

    /* renamed from: a  reason: collision with root package name */
    public String f6614a;
    public double b;
    public TimeLogBean c;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {

        /* renamed from: a  reason: collision with root package name */
        public List<LogsBean> f6615a;

        /* loaded from: classes8.dex */
        public static class LogsBean {

            /* renamed from: a  reason: collision with root package name */
            public String f6616a;
            public String b;
            public double c;
            public HRVMethod d;
            public int e;
            public List<List<Double>> f;
            public List<Integer> g;
            public List<Double> h;
            public List<Integer> i;
            public List<Integer> j;

            /* loaded from: classes8.dex */
            public enum HRVMethod {
                RMSSD,
                SDNN,
                SDANN
            }

            public int getAvgHr() {
                return this.e;
            }

            public List<List<Double>> getCoordinateValues() {
                return this.f;
            }

            public String getEndTime() {
                return this.b;
            }

            public List<Integer> getFatigueValues() {
                return this.g;
            }

            public List<Integer> getHrValues() {
                return this.i;
            }

            public HRVMethod getHrvMethod() {
                return this.d;
            }

            public List<Integer> getRriValues() {
                return this.j;
            }

            public String getStartTime() {
                return this.f6616a;
            }

            public double getValue() {
                return this.c;
            }

            public List<Double> getVascOcclValues() {
                return this.h;
            }

            public void setAvgHr(int i) {
                this.e = i;
            }

            public void setCoordinateValues(List<List<Double>> list) {
                this.f = list;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setFatigueValues(List<Integer> list) {
                this.g = list;
            }

            public void setHrValues(List<Integer> list) {
                this.i = list;
            }

            public void setHrvMethod(HRVMethod hRVMethod) {
                this.d = hRVMethod;
            }

            public void setRriValues(List<Integer> list) {
                this.j = list;
            }

            public void setStartTime(String str) {
                this.f6616a = str;
            }

            public void setValue(double d) {
                this.c = d;
            }

            public void setVascOcclValues(List<Double> list) {
                this.h = list;
            }

            public String toString() {
                return "LogsBean{startTime='" + this.f6616a + "', endTime='" + this.b + "', value=" + this.c + ", hrvMethod='" + this.d + "', avgHr=" + this.e + ", coordinateValues=" + this.f + ", fatigueValues=" + this.g + ", vascOcclValues=" + this.h + ", hrValues=" + this.i + ", rriValues=" + this.j + '}';
            }
        }

        public List<LogsBean> getLogs() {
            return this.f6615a;
        }

        public void setLogs(List<LogsBean> list) {
            this.f6615a = list;
        }

        public String toString() {
            return "TimeLogBean{logs=" + this.f6615a + '}';
        }
    }

    public double getAvgHrv() {
        return this.b;
    }

    public String getDate() {
        return this.f6614a;
    }

    public TimeLogBean getTimeLog() {
        return this.c;
    }

    public void setAvgHrv(double d) {
        this.b = d;
    }

    public void setDate(String str) {
        this.f6614a = str;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.c = timeLogBean;
    }

    public String toString() {
        return "HrvData{date='" + this.f6614a + "', avgHrv=" + this.b + ", timeLog=" + this.c + '}';
    }
}
