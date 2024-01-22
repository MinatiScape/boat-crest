package com.coveiot.coveaccess.onboarding.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class LegalDocsAcceptedListRes {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)

    /* renamed from: a  reason: collision with root package name */
    public String f6662a;
    @SerializedName("data")
    public Data b;
    @SerializedName(Constants.KEY_MESSAGE)
    public String c;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)

        /* renamed from: a  reason: collision with root package name */
        public List<Items> f6663a;

        public Data(LegalDocsAcceptedListRes legalDocsAcceptedListRes) {
        }

        public List<Items> getItems() {
            return this.f6663a;
        }

        public void setItems(List<Items> list) {
            this.f6663a = list;
        }
    }

    /* loaded from: classes8.dex */
    public class Items {
        @SerializedName("type")

        /* renamed from: a  reason: collision with root package name */
        public String f6664a;
        @SerializedName("version")
        public String b;
        @SerializedName("acceptedDate")
        public String c;
        @SerializedName("medium")
        @Expose
        public String medium;
        @SerializedName("userDeviceId")
        @Expose
        public Integer userDeviceId;

        public Items(LegalDocsAcceptedListRes legalDocsAcceptedListRes) {
        }

        public String getAcceptedDate() {
            return this.c;
        }

        public String getMedium() {
            return this.medium;
        }

        public String getType() {
            return this.f6664a;
        }

        public Integer getUserDeviceId() {
            return this.userDeviceId;
        }

        public String getVersion() {
            return this.b;
        }

        public void setAcceptedDate(String str) {
            this.c = str;
        }

        public void setMedium(String str) {
            this.medium = str;
        }

        public void setType(String str) {
            this.f6664a = str;
        }

        public void setUserDeviceId(Integer num) {
            this.userDeviceId = num;
        }

        public void setVersion(String str) {
            this.b = str;
        }
    }

    public Data getData() {
        return this.b;
    }

    public String getMessage() {
        return this.c;
    }

    public String getStatus() {
        return this.f6662a;
    }

    public void setData(Data data) {
        this.b = data;
    }

    public void setMessage(String str) {
        this.c = str;
    }

    public void setStatus(String str) {
        this.f6662a = str;
    }
}
