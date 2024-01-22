package com.coveiot.coveaccess.sleepscore;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SleepScoreApiRes {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Data> f6710a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("algoIdentifier")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6711a;
        @SerializedName("computedDate")
        @Expose
        private String b;
        @SerializedName("date")
        @Expose
        private String c;
        @SerializedName("sleepQuality")
        @Expose
        private Integer d;
        @SerializedName("contributingFactors")
        @Expose
        private ContributingFactors e;

        /* loaded from: classes8.dex */
        public class ContributingFactors {
            @SerializedName("sleepDurationContribution")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private Integer f6712a;
            @SerializedName("sleepDuration")
            @Expose
            private Integer b;
            @SerializedName("timeToDeepSleepContribution")
            @Expose
            private Integer c;
            @SerializedName("timeToDeepSleep")
            @Expose
            private Integer d;
            @SerializedName("wasoContribution")
            @Expose
            private Integer e;
            @SerializedName("waso")
            @Expose
            private Integer f;
            @SerializedName("sleepConsistencyContribution")
            @Expose
            private Integer g;
            @SerializedName("sleepConsistency")
            @Expose
            private String h;

            public ContributingFactors(Data data) {
            }

            public String getSleepConsistency() {
                return this.h;
            }

            public Integer getSleepConsistencyContribution() {
                return this.g;
            }

            public Integer getSleepDuration() {
                return this.b;
            }

            public Integer getSleepDurationContribution() {
                return this.f6712a;
            }

            public Integer getTimeToDeepSleep() {
                return this.d;
            }

            public Integer getTimeToDeepSleepContribution() {
                return this.c;
            }

            public Integer getWaso() {
                return this.f;
            }

            public Integer getWasoContribution() {
                return this.e;
            }

            public void setSleepConsistency(String str) {
                this.h = str;
            }

            public void setSleepConsistencyContribution(Integer num) {
                this.g = num;
            }

            public void setSleepDuration(Integer num) {
                this.b = num;
            }

            public void setSleepDurationContribution(Integer num) {
                this.f6712a = num;
            }

            public void setTimeToDeepSleep(Integer num) {
                this.d = num;
            }

            public void setTimeToDeepSleepContribution(Integer num) {
                this.c = num;
            }

            public void setWaso(Integer num) {
                this.f = num;
            }

            public void setWasoContribution(Integer num) {
                this.e = num;
            }
        }

        public Data(SleepScoreApiRes sleepScoreApiRes) {
        }

        public String getAlgoIdentifier() {
            return this.f6711a;
        }

        public String getComputedDate() {
            return this.b;
        }

        public ContributingFactors getContributingFactors() {
            return this.e;
        }

        public String getDate() {
            return this.c;
        }

        public Integer getSleepQuality() {
            return this.d;
        }

        public void setAlgoIdentifier(String str) {
            this.f6711a = str;
        }

        public void setComputedDate(String str) {
            this.b = str;
        }

        public void setContributingFactors(ContributingFactors contributingFactors) {
            this.e = contributingFactors;
        }

        public void setDate(String str) {
            this.c = str;
        }

        public void setSleepQuality(Integer num) {
            this.d = num;
        }
    }

    public List<Data> getData() {
        return this.f6710a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(List<Data> list) {
        this.f6710a = list;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
