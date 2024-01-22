package com.coveiot.coveaccess.weeklyreport.response;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WeeklyReportSubscriptionStatusResponse {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6925a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private Data c;

    /* loaded from: classes8.dex */
    public static class Data {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<Item> f6926a = null;

        /* loaded from: classes8.dex */
        public static class Item {
            @SerializedName("topic")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6927a;
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
                return this.f6927a;
            }

            public void setLang(String str) {
                this.c = str;
            }

            public void setMedium(String str) {
                this.b = str;
            }

            public void setTopic(String str) {
                this.f6927a = str;
            }
        }

        public List<Item> getItems() {
            return this.f6926a;
        }

        public void setItems(List<Item> list) {
            this.f6926a = list;
        }
    }

    public Data getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6925a;
    }

    public void setData(Data data) {
        this.c = data;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6925a = str;
    }
}
