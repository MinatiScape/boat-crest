package com.coveiot.coveaccess.weeklyreport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WeeklyReportSubscriptionReq {
    @SerializedName("subscriptions")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Subscription> f6915a = null;

    /* loaded from: classes8.dex */
    public static class Subscription {
        @SerializedName("topic")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6916a;
        @SerializedName("medium")
        @Expose
        private String b;
        @SerializedName("lang")
        @Expose
        private String c;

        public String getLang() {
            return this.c;
        }

        public String getMedium() {
            return this.b;
        }

        public String getTopic() {
            return this.f6916a;
        }

        public Subscription setLang(String str) {
            this.c = str;
            return this;
        }

        public void setMedium(String str) {
            this.b = str;
        }

        public void setTopic(String str) {
            this.f6916a = str;
        }
    }

    public List<Subscription> getSubscriptions() {
        return this.f6915a;
    }

    public void setSubscriptions(List<Subscription> list) {
        this.f6915a = list;
    }
}
