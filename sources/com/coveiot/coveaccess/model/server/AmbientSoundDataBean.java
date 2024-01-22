package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AmbientSoundDataBean {
    @SerializedName("avg")
    private int avg;
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("baseUnits")
    private BaseUnits baseUnits;
    @SerializedName("date")
    private String date;
    @SerializedName(Constants.PRIORITY_MAX)
    private int max;
    @SerializedName("min")
    private int min;
    @SerializedName("timeLog")
    private TimeLogBean timeLog;
    @SerializedName("totalDuration")
    private int totalDuration;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;

    /* loaded from: classes8.dex */
    public static class BaseUnits {
        @SerializedName("totalDuration")
        private String totalDuration;

        public String getTotalDuration() {
            return this.totalDuration;
        }

        public void setTotalDuration(String str) {
            this.totalDuration = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("avg")
            private int avg;
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

            public int getAvg() {
                return this.avg;
            }

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

            public void setAvg(int i) {
                this.avg = i;
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

    public int getAvg() {
        return this.avg;
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public BaseUnits getBaseUnits() {
        return this.baseUnits;
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

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public int getTotalDuration() {
        return this.totalDuration;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public void setAvg(int i) {
        this.avg = i;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setBaseUnits(BaseUnits baseUnits) {
        this.baseUnits = baseUnits;
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

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setTotalDuration(int i) {
        this.totalDuration = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }
}
