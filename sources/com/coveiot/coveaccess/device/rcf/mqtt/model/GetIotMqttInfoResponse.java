package com.coveiot.coveaccess.device.rcf.mqtt.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetIotMqttInfoResponse {
    @SerializedName("entitySports")

    /* renamed from: a  reason: collision with root package name */
    private EntitySports f6474a;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("clientId")
    @Expose
    public String clientId = null;
    @SerializedName("host")
    @Expose
    public String host = null;
    @SerializedName("port")
    @Expose
    public Integer port = null;
    @SerializedName("keepAlive")
    @Expose
    public Integer keepAlive = null;
    @SerializedName("s2tCmd")
    @Expose
    public ServerToThingCommand s2tCmd = null;

    public GetIotMqttInfoResponse(Integer num) {
        this.code = num;
    }

    public EntitySports getEntitySports() {
        return this.f6474a;
    }

    public void setEntitySports(EntitySports entitySports) {
        this.f6474a = entitySports;
    }
}
