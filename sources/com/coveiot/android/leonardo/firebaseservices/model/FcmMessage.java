package com.coveiot.android.leonardo.firebaseservices.model;

import com.coveiot.android.leonardo.firebaseservices.enums.FcmMessageTypes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FcmMessage implements Serializable {
    @SerializedName("messageJsonString")
    @Expose
    public String messageJsonString;
    @SerializedName("messageType")
    @Expose
    public FcmMessageTypes messageType;
    @SerializedName("timeStamp")
    @Expose
    public long timeStamp;

    public String getMessageJsonString() {
        return this.messageJsonString;
    }

    public FcmMessageTypes getMessageType() {
        return this.messageType;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setMessageType(FcmMessageTypes fcmMessageTypes) {
        this.messageType = fcmMessageTypes;
    }

    public String toString() {
        return "CloveCMMessage{messageType=" + this.messageType + ", messageJsonString='" + this.messageJsonString + "', timeStamp=" + this.timeStamp + '}';
    }
}
