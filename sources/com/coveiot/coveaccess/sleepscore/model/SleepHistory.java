package com.coveiot.coveaccess.sleepscore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes8.dex */
public class SleepHistory {
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6717a;
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

    /* loaded from: classes8.dex */
    public static class Summary {
        @SerializedName("sleepStartTime")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6718a;
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
            return this.f6718a;
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
            this.f6718a = str;
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

    public String getType() {
        return this.f6717a;
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

    public void setType(String str) {
        this.f6717a = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }

    public void setValue(Integer num) {
        this.e = num;
    }
}
