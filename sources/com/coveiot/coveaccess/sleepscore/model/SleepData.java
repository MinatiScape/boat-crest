package com.coveiot.coveaccess.sleepscore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes8.dex */
public class SleepData {
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6713a;
    @SerializedName("date")
    @Expose
    private String b;
    @SerializedName("tzOffset")
    @Expose
    private String c;
    @SerializedName("baseUnit")
    @Expose
    private String d;
    @SerializedName("value")
    @Expose
    private Integer e;
    @SerializedName(ErrorBundle.SUMMARY_ENTRY)
    @Expose
    private Summary f;
    @SerializedName("timeLog")
    @Expose
    private TimeLog g;

    /* loaded from: classes8.dex */
    public static class Summary {
        @SerializedName("sleepStartTime")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6714a;
        @SerializedName("sleepEndTime")
        @Expose
        private String b;
        @SerializedName("awakeDurations")
        @Expose
        private List<Integer> c = null;
        @SerializedName("lightSleepDurations")
        @Expose
        private List<Integer> d = null;
        @SerializedName("deepSleepDurations")
        @Expose
        private List<Integer> e = null;
        @SerializedName("remSleepDurations")
        @Expose
        private List<Integer> f = null;

        public List<Integer> getAwakeDurations() {
            return this.c;
        }

        public List<Integer> getDeepSleepDurations() {
            return this.e;
        }

        public List<Integer> getLightSleepDurations() {
            return this.d;
        }

        public List<Integer> getRemSleepDurations() {
            return this.f;
        }

        public String getSleepEndTime() {
            return this.b;
        }

        public String getSleepStartTime() {
            return this.f6714a;
        }

        public void setAwakeDurations(List<Integer> list) {
            this.c = list;
        }

        public void setDeepSleepDurations(List<Integer> list) {
            this.e = list;
        }

        public void setLightSleepDurations(List<Integer> list) {
            this.d = list;
        }

        public void setRemSleepDurations(List<Integer> list) {
            this.f = list;
        }

        public void setSleepEndTime(String str) {
            this.b = str;
        }

        public void setSleepStartTime(String str) {
            this.f6714a = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLog {
        @SerializedName("logs")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<Log> f6715a = null;

        /* loaded from: classes8.dex */
        public static class Log {
            @SerializedName("startTime")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6716a;
            @SerializedName("endTime")
            @Expose
            private String b;
            @SerializedName("awake")
            @Expose
            private Integer c;
            @SerializedName("lightSleep")
            @Expose
            private Integer d;
            @SerializedName("deepSleep")
            @Expose
            private Integer e;
            @SerializedName("remSleep")
            @Expose
            private Integer f;
            @SerializedName("refinedValues")
            @Expose
            private List<Integer> g = null;

            public Integer getAwake() {
                return this.c;
            }

            public Integer getDeepSleep() {
                return this.e;
            }

            public String getEndTime() {
                return this.b;
            }

            public Integer getLightSleep() {
                return this.d;
            }

            public List<Integer> getRefinedValues() {
                return this.g;
            }

            public Integer getRemSleep() {
                return this.f;
            }

            public String getStartTime() {
                return this.f6716a;
            }

            public void setAwake(Integer num) {
                this.c = num;
            }

            public void setDeepSleep(Integer num) {
                this.e = num;
            }

            public void setEndTime(String str) {
                this.b = str;
            }

            public void setLightSleep(Integer num) {
                this.d = num;
            }

            public void setRefinedValues(List<Integer> list) {
                this.g = list;
            }

            public void setRemSleep(Integer num) {
                this.f = num;
            }

            public void setStartTime(String str) {
                this.f6716a = str;
            }
        }

        public List<Log> getLogs() {
            return this.f6715a;
        }

        public void setLogs(List<Log> list) {
            this.f6715a = list;
        }
    }

    public String getBaseUnit() {
        return this.d;
    }

    public String getDate() {
        return this.b;
    }

    public Summary getSummary() {
        return this.f;
    }

    public TimeLog getTimeLog() {
        return this.g;
    }

    public String getType() {
        return this.f6713a;
    }

    public String getTzOffset() {
        return this.c;
    }

    public Integer getValue() {
        return this.e;
    }

    public void setBaseUnit(String str) {
        this.d = str;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setSummary(Summary summary) {
        this.f = summary;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.g = timeLog;
    }

    public void setType(String str) {
        this.f6713a = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }

    public void setValue(Integer num) {
        this.e = num;
    }
}
