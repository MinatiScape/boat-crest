package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SGetIotMqttCredInfoResponse {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("caCertPem")
        @Expose
        public String caCertPem = null;
        @SerializedName("certPem")
        @Expose
        public String certPem = null;
        @SerializedName("pvtKeyPem")
        @Expose
        public String pvtKeyPem = null;
        @SerializedName("keystoreP12")
        @Expose
        public String keystoreP12 = null;

        public Data() {
        }
    }
}
