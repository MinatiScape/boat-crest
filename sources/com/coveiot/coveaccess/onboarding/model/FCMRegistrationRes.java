package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class FCMRegistrationRes extends CoveApiResponseBaseModel {
    public String status;

    public FCMRegistrationRes(int i) {
        super(i);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
