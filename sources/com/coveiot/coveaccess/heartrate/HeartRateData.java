package com.coveiot.coveaccess.heartrate;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class HeartRateData {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    private String f6606a;
    @SerializedName("date")
    private String b;
    @SerializedName("baseUnit")
    private String c;
    @SerializedName("min")
    private int d;
    @SerializedName(Constants.PRIORITY_MAX)
    private int e;
    @SerializedName("rest")
    private int f;
    @SerializedName("timeLog")
    private TimeLogBean g;

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")

        /* renamed from: a  reason: collision with root package name */
        private List<LogsBean> f6607a;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("startTime")

            /* renamed from: a  reason: collision with root package name */
            private String f6608a;
            @SerializedName("endTime")
            private String b;
            @SerializedName("min")
            private int c;
            @SerializedName(Constants.PRIORITY_MAX)
            private int d;
            @SerializedName("codedValues")
            private List<Integer> e;

            public List<Integer> getCodedValues() {
                return this.e;
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
                return this.f6608a;
            }

            public void setCodedValues(List<Integer> list) {
                this.e = list;
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
                this.f6608a = str;
            }
        }

        public List<LogsBean> getLogs() {
            return this.f6607a;
        }

        public void setLogs(List<LogsBean> list) {
            this.f6607a = list;
        }
    }

    public String getBaseUnit() {
        return this.c;
    }

    public String getDate() {
        return this.b;
    }

    public int getMax() {
        return this.e;
    }

    public int getMin() {
        return this.d;
    }

    public int getRest() {
        return this.f;
    }

    public TimeLogBean getTimeLog() {
        return this.g;
    }

    public String getType() {
        return this.f6606a;
    }

    public void setBaseUnit(String str) {
        this.c = str;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setMax(int i) {
        this.e = i;
    }

    public void setMin(int i) {
        this.d = i;
    }

    public void setRest(int i) {
        this.f = i;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.g = timeLogBean;
    }

    public void setType(String str) {
        this.f6606a = str;
    }
}
