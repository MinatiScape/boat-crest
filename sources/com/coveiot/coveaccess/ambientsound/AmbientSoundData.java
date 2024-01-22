package com.coveiot.coveaccess.ambientsound;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AmbientSoundData {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    private String f6417a;
    @SerializedName("date")
    private String b;
    @SerializedName("tzOffset")
    private String c;
    @SerializedName("baseUnit")
    private String d;
    @SerializedName("min")
    private int e;
    @SerializedName(Constants.PRIORITY_MAX)
    private int f;
    @SerializedName("avg")
    private int g;
    @SerializedName("totalDuration")
    private int h;
    @SerializedName("timeLog")
    private TimeLogBean i;
    @SerializedName("baseUnits")
    private BaseUnits j;

    /* loaded from: classes8.dex */
    public static class BaseUnits {
        @SerializedName("totalDuration")

        /* renamed from: a  reason: collision with root package name */
        private String f6418a;

        public String getTotalDuration() {
            return this.f6418a;
        }

        public void setTotalDuration(String str) {
            this.f6418a = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")

        /* renamed from: a  reason: collision with root package name */
        private List<LogsBean> f6419a;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("startTime")

            /* renamed from: a  reason: collision with root package name */
            private String f6420a;
            @SerializedName("endTime")
            private String b;
            @SerializedName("min")
            private int c;
            @SerializedName(Constants.PRIORITY_MAX)
            private int d;
            @SerializedName("avg")
            private int e;
            @SerializedName("codedValues")
            private List<Integer> f;

            public int getAvg() {
                return this.e;
            }

            public List<Integer> getCodedValues() {
                return this.f;
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
                return this.f6420a;
            }

            public void setAvg(int i) {
                this.e = i;
            }

            public void setCodedValues(List<Integer> list) {
                this.f = list;
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
                this.f6420a = str;
            }
        }

        public List<LogsBean> getLogs() {
            return this.f6419a;
        }

        public void setLogs(List<LogsBean> list) {
            this.f6419a = list;
        }
    }

    public int getAvg() {
        return this.g;
    }

    public String getBaseUnit() {
        return this.d;
    }

    public BaseUnits getBaseUnits() {
        return this.j;
    }

    public String getDate() {
        return this.b;
    }

    public int getMax() {
        return this.f;
    }

    public int getMin() {
        return this.e;
    }

    public TimeLogBean getTimeLog() {
        return this.i;
    }

    public int getTotalDuration() {
        return this.h;
    }

    public String getType() {
        return this.f6417a;
    }

    public String getTzOffset() {
        return this.c;
    }

    public void setAvg(int i) {
        this.g = i;
    }

    public void setBaseUnit(String str) {
        this.d = str;
    }

    public void setBaseUnits(BaseUnits baseUnits) {
        this.j = baseUnits;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setMax(int i) {
        this.f = i;
    }

    public void setMin(int i) {
        this.e = i;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.i = timeLogBean;
    }

    public void setTotalDuration(int i) {
        this.h = i;
    }

    public void setType(String str) {
        this.f6417a = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }
}
