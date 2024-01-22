package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class HrvDataBean {
    private double avgHrv;
    private String baseUnit;
    private String date;
    private TimeLogBean timeLog;
    private String type;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            private int avgHr;
            private List<List<Double>> coordinateValues;
            private String endTime;
            private List<Integer> fatigueValues;
            private List<Integer> hrValues;
            private String hrvMethod;
            private List<Integer> rriValues;
            private String startTime;
            private double value;
            private List<Double> vascOcclValues;

            public int getAvgHr() {
                return this.avgHr;
            }

            public List<List<Double>> getCoordinateValues() {
                return this.coordinateValues;
            }

            public String getEndTime() {
                return this.endTime;
            }

            public List<Integer> getFatigueValues() {
                return this.fatigueValues;
            }

            public List<Integer> getHrValues() {
                return this.hrValues;
            }

            public String getHrvMethod() {
                return this.hrvMethod;
            }

            public List<Integer> getRriValues() {
                return this.rriValues;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public double getValue() {
                return this.value;
            }

            public List<Double> getVascOcclValues() {
                return this.vascOcclValues;
            }

            public void setAvgHr(int i) {
                this.avgHr = i;
            }

            public void setCoordinateValues(List<List<Double>> list) {
                this.coordinateValues = list;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setFatigueValues(List<Integer> list) {
                this.fatigueValues = list;
            }

            public void setHrValues(List<Integer> list) {
                this.hrValues = list;
            }

            public void setHrvMethod(String str) {
                this.hrvMethod = str;
            }

            public void setRriValues(List<Integer> list) {
                this.rriValues = list;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }

            public void setValue(double d) {
                this.value = d;
            }

            public void setVascOcclValues(List<Double> list) {
                this.vascOcclValues = list;
            }
        }

        public List<LogsBean> getLogs() {
            return this.logs;
        }

        public void setLogs(List<LogsBean> list) {
            this.logs = list;
        }
    }

    public double getAvgHrv() {
        return this.avgHrv;
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getDate() {
        return this.date;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public void setAvgHrv(double d) {
        this.avgHrv = d;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setType(String str) {
        this.type = str;
    }
}
