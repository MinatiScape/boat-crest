package com.coveiot.coveaccess.weeklyreport.response;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WeekReportSubscriptionResponse {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6921a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private Data c;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<Item> f6922a = null;

        /* loaded from: classes8.dex */
        public class Item {
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6923a;
            @SerializedName(Constants.KEY_MESSAGE)
            @Expose
            private String b;
            @SerializedName("data")
            @Expose
            private DataItem c;
            @SerializedName("code")
            @Expose
            private String d;

            /* loaded from: classes8.dex */
            public class DataItem {
                @SerializedName("topic")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private String f6924a;
                @SerializedName("medium")
                @Expose
                private String b;

                public DataItem(Item item) {
                }

                public String getMedium() {
                    return this.b;
                }

                public String getTopic() {
                    return this.f6924a;
                }

                public void setMedium(String str) {
                    this.b = str;
                }

                public void setTopic(String str) {
                    this.f6924a = str;
                }
            }

            public Item(Data data) {
            }

            public String getCode() {
                return this.d;
            }

            public DataItem getData() {
                return this.c;
            }

            public String getMessage() {
                return this.b;
            }

            public String getStatus() {
                return this.f6923a;
            }

            public void setCode(String str) {
                this.d = str;
            }

            public void setData(DataItem dataItem) {
                this.c = dataItem;
            }

            public void setMessage(String str) {
                this.b = str;
            }

            public void setStatus(String str) {
                this.f6923a = str;
            }
        }

        public Data(WeekReportSubscriptionResponse weekReportSubscriptionResponse) {
        }

        public List<Item> getItems() {
            return this.f6922a;
        }

        public void setItems(List<Item> list) {
            this.f6922a = list;
        }
    }

    public Data getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6921a;
    }

    public void setData(Data data) {
        this.c = data;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6921a = str;
    }
}
