package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class CallQuickRepliesBean {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("messages")
    private List<String> messages;

    public Boolean getActive() {
        return this.active;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setMessages(List<String> list) {
        this.messages = list;
    }
}
