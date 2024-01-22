package com.coveiot.coveaccess.sports;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SportsUserPrefRequest {
    @SerializedName("userDeviceId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6752a;
    @SerializedName("vibrationConfig")
    private VibrationConfigBean b;
    @SerializedName(FitnessActivities.CRICKET)
    @Expose
    private Cricket c;
    @SerializedName("pushToDevice")
    private Boolean d;
    @SerializedName("soccer")
    private Soccer e;

    public Cricket getCricket() {
        return this.c;
    }

    public Boolean getPushToDevice() {
        return this.d;
    }

    public Soccer getSoccer() {
        return this.e;
    }

    public Integer getUserDeviceId() {
        return this.f6752a;
    }

    public VibrationConfigBean getVibrationConfig() {
        return this.b;
    }

    public void setCricket(Cricket cricket) {
        this.c = cricket;
    }

    public void setPushToDevice(Boolean bool) {
        this.d = bool;
    }

    public void setSoccer(Soccer soccer) {
        this.e = soccer;
    }

    public void setUserDeviceId(Integer num) {
        this.f6752a = num;
    }

    public void setVibrationConfig(VibrationConfigBean vibrationConfigBean) {
        this.b = vibrationConfigBean;
    }
}
