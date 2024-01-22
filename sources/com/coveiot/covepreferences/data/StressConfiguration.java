package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class StressConfiguration {
    @SerializedName(DeviceKey.Stress)

    /* renamed from: a  reason: collision with root package name */
    private Stress f7046a;

    /* loaded from: classes8.dex */
    public static class Stress {
        @SerializedName("readinessTime")

        /* renamed from: a  reason: collision with root package name */
        private String f7047a = "06:00:00";
        @SerializedName("baselineTime")
        private String b = "03:00:00";
        @SerializedName("alert")
        private Alert c;

        /* loaded from: classes8.dex */
        public static class Alert {
            @SerializedName("threshold")

            /* renamed from: a  reason: collision with root package name */
            private Integer f7048a = 70;
            @SerializedName("maxAllowed")
            private Integer b = 10;

            public Integer getMaxAllowed() {
                return this.b;
            }

            public Integer getThreshold() {
                return this.f7048a;
            }

            public void setMaxAllowed(Integer num) {
                this.b = num;
            }

            public void setThreshold(Integer num) {
                this.f7048a = num;
            }
        }

        public Alert getAlert() {
            return this.c;
        }

        public String getBaselineTime() {
            return this.b;
        }

        public String getReadinessTime() {
            return this.f7047a;
        }

        public void setAlert(Alert alert) {
            this.c = alert;
        }

        public void setBaselineTime(String str) {
            this.b = str;
        }

        public void setReadinessTime(String str) {
            this.f7047a = str;
        }
    }

    public Stress getStress() {
        return this.f7046a;
    }

    public void setStress(Stress stress) {
        this.f7046a = stress;
    }
}
