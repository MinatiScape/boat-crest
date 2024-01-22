package com.coveiot.android.sportsnotification.model;

import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class MQTTPayLoad {
    @SerializedName(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP)

    /* renamed from: a  reason: collision with root package name */
    private Long f5870a;
    @SerializedName("evt")
    private Evt b;

    /* loaded from: classes7.dex */
    public static class Evt {
        @SerializedName("ty")

        /* renamed from: a  reason: collision with root package name */
        private String f5871a;
        @SerializedName("da")
        private Da b;

        /* loaded from: classes7.dex */
        public static class Da {
            @SerializedName("totalEvents")

            /* renamed from: a  reason: collision with root package name */
            private Integer f5872a;

            public Integer getTotalEvents() {
                return this.f5872a;
            }

            public void setTotalEvents(Integer num) {
                this.f5872a = num;
            }

            public String toString() {
                return "Da{totalEvents=" + this.f5872a + '}';
            }
        }

        public Da getDa() {
            return this.b;
        }

        public String getTy() {
            return this.f5871a;
        }

        public void setDa(Da da) {
            this.b = da;
        }

        public void setTy(String str) {
            this.f5871a = str;
        }

        public String toString() {
            return "Evt{ty='" + this.f5871a + "', da=" + this.b + '}';
        }
    }

    public Evt getEvt() {
        return this.b;
    }

    public Long getTs() {
        return this.f5870a;
    }

    public void setEvt(Evt evt) {
        this.b = evt;
    }

    public void setTs(Long l) {
        this.f5870a = l;
    }

    public String toString() {
        return "MQTTPayLoad{ts=" + this.f5870a + ", evt=" + this.b + '}';
    }
}
