package com.coveiot.coveaccess.device.rcf.mqtt.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetIotMqttCredInfoResponse {
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
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

    public GetIotMqttCredInfoResponse(Integer num) {
        this.code = num;
    }
}
