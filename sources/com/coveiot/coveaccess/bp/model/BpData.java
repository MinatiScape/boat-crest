package com.coveiot.coveaccess.bp.model;

import java.util.List;
/* loaded from: classes8.dex */
public class BpData {

    /* renamed from: a  reason: collision with root package name */
    public String f6439a;
    public int b;
    public int c;
    public TimeLogBean d;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {

        /* renamed from: a  reason: collision with root package name */
        public List<LogsBean> f6440a;

        /* loaded from: classes8.dex */
        public static class LogsBean {

            /* renamed from: a  reason: collision with root package name */
            public String f6441a;
            public String b;
            public List<List<Integer>> c;
            public List<List<Double>> d;
            public List<Integer> e;

            public List<List<Integer>> getCodedValues() {
                return this.c;
            }

            public List<List<Double>> getCoordinateValues() {
                return this.d;
            }

            public String getEndTime() {
                return this.b;
            }

            public List<Integer> getHrValues() {
                return this.e;
            }

            public String getStartTime() {
                return this.f6441a;
            }

            public void setCodedValues(List<List<Integer>> list) {
                this.c = list;
            }

            public void setCoordinateValues(List<List<Double>> list) {
                this.d = list;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setHrValues(List<Integer> list) {
                this.e = list;
            }

            public void setStartTime(String str) {
                this.f6441a = str;
            }

            public String toString() {
                return "LogsBean{startTime='" + this.f6441a + "', endTime='" + this.b + "', codedValues=" + this.c + ", coordinateValues=" + this.d + ", hrValues=" + this.e + '}';
            }
        }

        public List<LogsBean> getLogs() {
            return this.f6440a;
        }

        public void setLogs(List<LogsBean> list) {
            this.f6440a = list;
        }

        public String toString() {
            return "TimeLogBean{logs=" + this.f6440a + '}';
        }
    }

    public int getAvgDiastolic() {
        return this.c;
    }

    public int getAvgSystolic() {
        return this.b;
    }

    public String getDate() {
        return this.f6439a;
    }

    public TimeLogBean getTimeLog() {
        return this.d;
    }

    public void setAvgDiastolic(int i) {
        this.c = i;
    }

    public void setAvgSystolic(int i) {
        this.b = i;
    }

    public void setDate(String str) {
        this.f6439a = str;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.d = timeLogBean;
    }

    public String toString() {
        return "BpData{date='" + this.f6439a + "', avgSystolic=" + this.b + ", avgDiastolic=" + this.c + ", timeLog=" + this.d + '}';
    }
}
