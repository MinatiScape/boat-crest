package com.coveiot.coveaccess.userappsetting;

import com.coveiot.coveaccess.model.server.CurrentWatchFaceBean;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveDeviceSpecificParamsReq {

    /* renamed from: a  reason: collision with root package name */
    public String f6839a;
    public List<String> b;
    public String c;
    public String d;
    public String e;
    public boolean f;
    public String g;
    public BpCalibrationBean h;
    public CurrentWatchFaceBean i;

    public List<String> getActiveDisplays() {
        return this.b;
    }

    public BpCalibrationBean getBpCalibration() {
        return this.h;
    }

    public String getClockFormat() {
        return this.e;
    }

    public CurrentWatchFaceBean getCurrentWatchFace() {
        return this.i;
    }

    public String getDistanceUnit() {
        return this.d;
    }

    public String getScreenOrientation() {
        return this.c;
    }

    public String getTemperatureUnit() {
        return this.g;
    }

    public String getWearingOn() {
        return this.f6839a;
    }

    public boolean isLiftWristToView() {
        return this.f;
    }

    public void setActiveDisplays(List<String> list) {
        this.b = list;
    }

    public void setBpCalibration(BpCalibrationBean bpCalibrationBean) {
        this.h = bpCalibrationBean;
    }

    public void setClockFormat(String str) {
        this.e = str;
    }

    public void setCurrentWatchFace(CurrentWatchFaceBean currentWatchFaceBean) {
        this.i = currentWatchFaceBean;
    }

    public void setDistanceUnit(String str) {
        this.d = str;
    }

    public void setLiftWristToView(boolean z) {
        this.f = z;
    }

    public void setScreenOrientation(String str) {
        this.c = str;
    }

    public void setTemperatureUnit(String str) {
        this.g = str;
    }

    public void setWearingOn(String str) {
        this.f6839a = str;
    }
}
