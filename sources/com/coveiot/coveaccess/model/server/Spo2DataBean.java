package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Spo2DataBean {
    @SerializedName("avg")
    @Expose
    private Double avg;
    @SerializedName("baseUnit")
    @Expose
    private String baseUnit;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName(Constants.PRIORITY_MAX)
    @Expose
    private Integer max;
    @SerializedName("min")
    @Expose
    private Integer min;
    @SerializedName("timeLog")
    @Expose
    private TimeLog timeLog;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tzOffset")
    @Expose
    private String tzOffset;

    /* loaded from: classes8.dex */
    public static class TimeLog {
        @SerializedName("logs")
        @Expose
        private List<Log> logs = null;

        /* loaded from: classes8.dex */
        public static class Log {
            @SerializedName("avg")
            @Expose
            private Double avg;
            @SerializedName("codedValues")
            @Expose
            private List<Integer> codedValues = null;
            @SerializedName("endTime")
            @Expose
            private String endTime;
            @SerializedName(Constants.PRIORITY_MAX)
            @Expose
            private Integer max;
            @SerializedName("min")
            @Expose
            private Integer min;
            @SerializedName("startTime")
            @Expose
            private String startTime;

            public Double getAvg() {
                return this.avg;
            }

            public List<Integer> getCodedValues() {
                return this.codedValues;
            }

            public String getEndTime() {
                return this.endTime;
            }

            public Integer getMax() {
                return this.max;
            }

            public Integer getMin() {
                return this.min;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setAvg(Double d) {
                this.avg = d;
            }

            public void setCodedValues(List<Integer> list) {
                this.codedValues = list;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setMax(Integer num) {
                this.max = num;
            }

            public void setMin(Integer num) {
                this.min = num;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public List<Log> getLogs() {
            return this.logs;
        }

        public void setLogs(List<Log> list) {
            this.logs = list;
        }
    }

    public Double getAvg() {
        return this.avg;
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getDate() {
        return this.date;
    }

    public Integer getMax() {
        return this.max;
    }

    public Integer getMin() {
        return this.min;
    }

    public TimeLog getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public void setAvg(Double d) {
        this.avg = d;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMax(Integer num) {
        this.max = num;
    }

    public void setMin(Integer num) {
        this.min = num;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.timeLog = timeLog;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }
}
