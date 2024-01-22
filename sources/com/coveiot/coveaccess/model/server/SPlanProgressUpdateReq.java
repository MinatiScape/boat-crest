package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public class SPlanProgressUpdateReq {
    private int progress;
    private String progressStatus;
    private String userPlanId;

    public int getPercentage() {
        return this.progress;
    }

    public String getProgressStatus() {
        return this.progressStatus;
    }

    public String getUserPlanId() {
        return this.userPlanId;
    }

    public void setPercentage(int i) {
        this.progress = i;
    }

    public void setProgressStatus(String str) {
        this.progressStatus = str;
    }

    public void setUserPlanId(String str) {
        this.userPlanId = str;
    }
}
