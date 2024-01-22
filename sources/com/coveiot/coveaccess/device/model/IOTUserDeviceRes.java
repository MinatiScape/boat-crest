package com.coveiot.coveaccess.device.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class IOTUserDeviceRes extends CoveApiResponseBaseModel {
    public String appTrackerId;
    public String userDeviceId;

    public IOTUserDeviceRes(int i) {
        super(i);
    }

    public String getAppTrackerId() {
        return this.appTrackerId;
    }

    public String getUserDeviceId() {
        return this.userDeviceId;
    }

    public void setAppTrackerId(String str) {
        this.appTrackerId = str;
    }

    public void setUserDeviceId(String str) {
        this.userDeviceId = str;
    }
}
