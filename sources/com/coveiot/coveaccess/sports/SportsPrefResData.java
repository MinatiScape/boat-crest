package com.coveiot.coveaccess.sports;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SportsPrefResData {
    @SerializedName("vibrationConfig")

    /* renamed from: a  reason: collision with root package name */
    private VibrationConfigBean f6750a;
    @SerializedName("pushToDevice")
    private Boolean b;
    @SerializedName("soccer")
    private Soccer c;
    @SerializedName(FitnessActivities.CRICKET)
    @Expose
    private Cricket d;

    public Cricket getCricket() {
        return this.d;
    }

    public Boolean getPushToDevice() {
        return this.b;
    }

    public Soccer getSoccer() {
        return this.c;
    }

    public VibrationConfigBean getVibrationConfig() {
        return this.f6750a;
    }

    public void setCricket(Cricket cricket) {
        this.d = cricket;
    }

    public void setPushToDevice(Boolean bool) {
        this.b = bool;
    }

    public void setSoccer(Soccer soccer) {
        this.c = soccer;
    }

    public void setVibrationConfig(VibrationConfigBean vibrationConfigBean) {
        this.f6750a = vibrationConfigBean;
    }
}
