package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public final class RegisterNewUserRes extends CoveApiResponseBaseModel {
    public String appTrackerId;
    public String axTrackerId;
    public String dpUrl;
    public int userId;

    public RegisterNewUserRes(int i) {
        super(i);
    }

    public String getAppTrackerId() {
        return this.appTrackerId;
    }

    public String getAxTrackerId() {
        return this.axTrackerId;
    }

    public String getDpUrl() {
        return this.dpUrl;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setAppTrackerId(String str) {
        this.appTrackerId = str;
    }

    public void setAxTrackerId(String str) {
        this.axTrackerId = str;
    }

    public void setDpUrl(String str) {
        this.dpUrl = str;
    }

    public void setUserId(int i) {
        this.userId = i;
    }
}
