package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.device.rcf.mqtt.model.EntitySports;
import com.coveiot.coveaccess.device.rcf.mqtt.model.ServerToThingCommand;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SGetIotMqttInfoResponse {
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
        @SerializedName("entitySports")
        public EntitySports entitySports;
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

        public Data() {
        }
    }
}
