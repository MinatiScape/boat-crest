package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public final class RegisterExistingUserRes extends CoveApiResponseBaseModel {
    public String appTrackerId;

    public RegisterExistingUserRes(int i) {
        super(i);
    }

    public String getAppTrackerId() {
        return this.appTrackerId;
    }

    public void setAppTrackerId(String str) {
        this.appTrackerId = str;
    }
}
