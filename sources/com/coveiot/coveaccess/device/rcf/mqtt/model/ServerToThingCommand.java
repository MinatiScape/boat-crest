package com.coveiot.coveaccess.device.rcf.mqtt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes8.dex */
public class ServerToThingCommand {
    @SerializedName("req")
    @Expose
    public Request req = null;
    @SerializedName("res")
    @Expose
    public Response res = null;

    /* loaded from: classes8.dex */
    public class Request {
        @SerializedName("topic")
        @Expose
        public String topic = null;
        @SerializedName(MqttServiceConstants.QOS)
        @Expose
        public Integer qos = null;
        @SerializedName(CMSAttributeTableGenerator.CONTENT_TYPE)
        @Expose
        public String contentType = null;

        public Request(ServerToThingCommand serverToThingCommand) {
        }
    }

    /* loaded from: classes8.dex */
    public class Response {
        @SerializedName("topic")
        @Expose
        public String topic = null;
        @SerializedName(MqttServiceConstants.QOS)
        @Expose
        public Integer qos = null;
        @SerializedName(CMSAttributeTableGenerator.CONTENT_TYPE)
        @Expose
        public String contentType = null;

        public Response(ServerToThingCommand serverToThingCommand) {
        }
    }
}
