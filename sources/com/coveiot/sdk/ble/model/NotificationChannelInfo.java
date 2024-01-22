package com.coveiot.sdk.ble.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class NotificationChannelInfo implements Serializable {
    public String AppName;
    public String ChannelName;
    public String channelDefaultId;
    public String description;
    public int drawable;

    public String getAppName() {
        return this.AppName;
    }

    public String getChannelDefaultId() {
        return this.channelDefaultId;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDrawable() {
        return this.drawable;
    }

    public void setAppName(String str) {
        this.AppName = str;
    }

    public void setChannelDefaultId(String str) {
        this.channelDefaultId = str;
    }

    public void setChannelName(String str) {
        this.ChannelName = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDrawable(int i) {
        this.drawable = i;
    }

    public String toString() {
        return "NotificationChannelInfo{AppName='" + this.AppName + "', ChannelName='" + this.ChannelName + "', channelDefaultId=" + this.channelDefaultId + ", description='" + this.description + "', drawable=" + this.drawable + '}';
    }
}
