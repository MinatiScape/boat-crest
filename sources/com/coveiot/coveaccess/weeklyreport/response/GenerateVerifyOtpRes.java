package com.coveiot.coveaccess.weeklyreport.response;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GenerateVerifyOtpRes {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6919a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private Data c;

    /* loaded from: classes8.dex */
    public static class Data {
        @SerializedName("emailId")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6920a;
        @SerializedName("verificationId")
        @Expose
        private String b;

        public String getEmailId() {
            return this.f6920a;
        }

        public String getVerificationId() {
            return this.b;
        }

        public void setEmailId(String str) {
            this.f6920a = str;
        }

        public void setVerificationId(String str) {
            this.b = str;
        }
    }

    public Data getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6919a;
    }

    public void setData(Data data) {
        this.c = data;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6919a = str;
    }
}
