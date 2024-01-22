package com.coveiot.coveaccess.respiratoryrate;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes8.dex */
public class RespiratoryRateApiRes {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Data> f6692a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;
    @SerializedName("algoIdentifier")
    @Expose
    private String d;
    @SerializedName("computedDate")
    @Expose
    private String e;
    @SerializedName("error")
    @Expose
    private Error f;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6693a;
        @SerializedName("respiratoryRate")
        @Expose
        private int b;
        @SerializedName("confidence")
        @Expose
        private float c;

        public Data(RespiratoryRateApiRes respiratoryRateApiRes) {
        }

        public float getConfidence() {
            return this.c;
        }

        public int getRespiratoryRate() {
            return this.b;
        }

        public String getTime() {
            return this.f6693a;
        }

        public void setConfidence(float f) {
            this.c = f;
        }

        public void setRespiratoryRate(int i) {
            this.b = i;
        }

        public void setTime(String str) {
            this.f6693a = str;
        }
    }

    /* loaded from: classes8.dex */
    public class Error {
        @SerializedName("reason")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6694a;
        @SerializedName(MqttServiceConstants.TRACE_EXCEPTION)
        @Expose
        private String b;
        @SerializedName("stacktrace")
        @Expose
        private String c;

        public Error(RespiratoryRateApiRes respiratoryRateApiRes) {
        }

        public String getException() {
            return this.b;
        }

        public String getReason() {
            return this.f6694a;
        }

        public String getStacktrace() {
            return this.c;
        }

        public void setException(String str) {
            this.b = str;
        }

        public void setReason(String str) {
            this.f6694a = str;
        }

        public void setStacktrace(String str) {
            this.c = str;
        }
    }

    public String getAlgoIdentifier() {
        return this.d;
    }

    public String getComputedDate() {
        return this.e;
    }

    public List<Data> getData() {
        return this.f6692a;
    }

    public Error getError() {
        return this.f;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setAlgoIdentifier(String str) {
        this.d = str;
    }

    public void setComputedDate(String str) {
        this.e = str;
    }

    public void setData(List<Data> list) {
        this.f6692a = list;
    }

    public void setError(Error error) {
        this.f = error;
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
