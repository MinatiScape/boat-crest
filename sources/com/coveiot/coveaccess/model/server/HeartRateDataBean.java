package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class HeartRateDataBean {
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("date")
    private String date;
    @SerializedName(Constants.PRIORITY_MAX)
    private int max;
    @SerializedName("min")
    private int min;
    @SerializedName("rest")
    private int rest;
    @SerializedName("timeLog")
    private TimeLogBean timeLog;
    @SerializedName("type")
    private String type;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("codedValues")
            private List<Integer> codedValues;
            @SerializedName("endTime")
            private String endTime;
            @SerializedName(Constants.PRIORITY_MAX)
            private int max;
            @SerializedName("min")
            private int min;
            @SerializedName("startTime")
            private String startTime;

            public List<Integer> getCodedValues() {
                return this.codedValues;
            }

            public String getEndTime() {
                return this.endTime;
            }

            public int getMax() {
                return this.max;
            }

            public int getMin() {
                return this.min;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setCodedValues(List<Integer> list) {
                this.codedValues = list;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setMax(int i) {
                this.max = i;
            }

            public void setMin(int i) {
                this.min = i;
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

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getDate() {
        return this.date;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }

    public int getRest() {
        return this.rest;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setRest(int i) {
        this.rest = i;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setType(String str) {
        this.type = str;
    }
}
