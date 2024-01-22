package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class BpDataBean {
    private int avgDiastolic;
    private int avgSystolic;
    private String baseUnit;
    private String date;
    private TimeLogBean timeLog;
    private String type;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            private List<List<Integer>> codedValues;
            private List<List<Double>> coordinateValues;
            private String endTime;
            private List<Integer> hrValues;
            private String startTime;

            public List<List<Integer>> getCodedValues() {
                return this.codedValues;
            }

            public List<List<Double>> getCoordinateValues() {
                return this.coordinateValues;
            }

            public String getEndTime() {
                return this.endTime;
            }

            public List<Integer> getHrValues() {
                return this.hrValues;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setCodedValues(List<List<Integer>> list) {
                this.codedValues = list;
            }

            public void setCoordinateValues(List<List<Double>> list) {
                this.coordinateValues = list;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setHrValues(List<Integer> list) {
                this.hrValues = list;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public List<LogsBean> getLogs() {
            return this.logs;
        }

        public void setLogs(List<LogsBean> list) {
            this.logs = list;
        }
    }

    public int getAvgDiastolic() {
        return this.avgDiastolic;
    }

    public int getAvgSystolic() {
        return this.avgSystolic;
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

    public void setAvgDiastolic(int i) {
        this.avgDiastolic = i;
    }

    public void setAvgSystolic(int i) {
        this.avgSystolic = i;
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
