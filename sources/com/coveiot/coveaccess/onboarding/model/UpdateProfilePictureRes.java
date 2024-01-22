package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class UpdateProfilePictureRes extends CoveApiResponseBaseModel {
    public String dpUrl;

    public UpdateProfilePictureRes(int i) {
        super(i);
    }

    public String getDpUrl() {
        return this.dpUrl;
    }

    public void setDpUrl(String str) {
        this.dpUrl = str;
    }
}
