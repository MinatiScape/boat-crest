package com.coveiot.coveaccess.onboarding.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AcceptLegalTermsRes extends CoveApiResponseBaseModel {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("type")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6660a;
        @SerializedName("version")
        @Expose
        private String b;
        @SerializedName("acceptedDate")
        @Expose
        private String c;
        @SerializedName("medium")
        @Expose
        public String medium;
        @SerializedName("userDeviceId")
        @Expose
        public Integer userDeviceId;

        public Data(AcceptLegalTermsRes acceptLegalTermsRes) {
        }

        public String getAcceptedDate() {
            return this.c;
        }

        public String getMedium() {
            return this.medium;
        }

        public String getType() {
            return this.f6660a;
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
            this.f6660a = str;
        }

        public void setUserDeviceId(Integer num) {
            this.userDeviceId = num;
        }

        public void setVersion(String str) {
            this.b = str;
        }
    }

    public AcceptLegalTermsRes(int i) {
        super(i);
    }

    public Data getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
