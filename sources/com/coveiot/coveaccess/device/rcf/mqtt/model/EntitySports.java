package com.coveiot.coveaccess.device.rcf.mqtt.model;

import com.coveiot.coveaccess.device.rcf.mqtt.model.ServerToThingCommand;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EntitySports {
    @SerializedName("soccer")

    /* renamed from: a  reason: collision with root package name */
    private ServerToThingCommand.Request f6473a;

    public ServerToThingCommand.Request getSoccer() {
        return this.f6473a;
    }

    public void setSoccer(ServerToThingCommand.Request request) {
        this.f6473a = request;
    }
}
