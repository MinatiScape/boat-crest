package com.coveiot.coveaccess.weeklyreport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WeeklyReportUnsubscribeReq {
    @SerializedName("subscriptions")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Subscription> f6917a = null;

    /* loaded from: classes8.dex */
    public static class Subscription {
        @SerializedName("topic")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6918a;
        @SerializedName("medium")
        @Expose
        private String b;
        @SerializedName("unsubscribeReason")
        @Expose
        private String c;

        public String getMedium() {
            return this.b;
        }

        public String getTopic() {
            return this.f6918a;
        }

        public String getUnsubscribeReason() {
            return this.c;
        }

        public void setMedium(String str) {
            this.b = str;
        }

        public void setTopic(String str) {
            this.f6918a = str;
        }

        public void setUnsubscribeReason(String str) {
            this.c = str;
        }
    }

    public List<Subscription> getSubscriptions() {
        return this.f6917a;
    }

    public void setSubscriptions(List<Subscription> list) {
        this.f6917a = list;
    }
}
