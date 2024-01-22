package com.coveiot.coveaccess.hrvdata;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class HRVDataNew {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    private String f6617a;
    @SerializedName("baseUnit")
    private String b;
    @SerializedName("date")
    private String c;
    @SerializedName("tzOffset")
    private String d;
    @SerializedName("baseline")
    private Double e;
    @SerializedName("readiness")
    private Double f;
    @SerializedName("timeLog")
    private TimeLog g;
    @SerializedName("min")
    private Double h;
    @SerializedName(Constants.PRIORITY_MAX)
    private Double i;
    @SerializedName("avg")
    private Double j;

    /* loaded from: classes8.dex */
    public static class TimeLog {
        @SerializedName("logs")

        /* renamed from: a  reason: collision with root package name */
        private List<Logs> f6618a;

        /* loaded from: classes8.dex */
        public static class Logs {
            @SerializedName("startTime")

            /* renamed from: a  reason: collision with root package name */
            private String f6619a;
            @SerializedName("endTime")
            private String b;
            @SerializedName("codedValues")
            private List<Double> c;
            @SerializedName("min")
            private Double d;
            @SerializedName(Constants.PRIORITY_MAX)
            private Double e;
            @SerializedName("avg")
            private Double f;

            public Double getAvg() {
                return this.f;
            }

            public List<Double> getCodedValues() {
                return this.c;
            }

            public String getEndTime() {
                return this.b;
            }

            public Double getMax() {
                return this.e;
            }

            public Double getMin() {
                return this.d;
            }

            public String getStartTime() {
                return this.f6619a;
            }

            public void setAvg(Double d) {
                this.f = d;
            }

            public void setCodedValues(List<Double> list) {
                this.c = list;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setMax(Double d) {
                this.e = d;
            }

            public void setMin(Double d) {
                this.d = d;
            }

            public void setStartTime(String str) {
                this.f6619a = str;
            }
        }

        public List<Logs> getLogs() {
            return this.f6618a;
        }

        public void setLogs(List<Logs> list) {
            this.f6618a = list;
        }
    }

    public Double getAvg() {
        return this.j;
    }

    public String getBaseUnit() {
        return this.b;
    }

    public Double getBaseline() {
        return this.e;
    }

    public String getDate() {
        return this.c;
    }

    public Double getMax() {
        return this.i;
    }

    public Double getMin() {
        return this.h;
    }

    public Double getReadiness() {
        return this.f;
    }

    public TimeLog getTimeLog() {
        return this.g;
    }

    public String getType() {
        return this.f6617a;
    }

    public String getTzOffset() {
        return this.d;
    }

    public void setAvg(Double d) {
        this.j = d;
    }

    public void setBaseUnit(String str) {
        this.b = str;
    }

    public void setBaseline(Double d) {
        this.e = d;
    }

    public void setDate(String str) {
        this.c = str;
    }

    public void setMax(Double d) {
        this.i = d;
    }

    public void setMin(Double d) {
        this.h = d;
    }

    public void setReadiness(Double d) {
        this.f = d;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.g = timeLog;
    }

    public void setType(String str) {
        this.f6617a = str;
    }

    public void setTzOffset(String str) {
        this.d = str;
    }
}
