package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class DeviceModelBean {

    /* renamed from: a  reason: collision with root package name */
    public String f7019a;
    public String b;
    public Boolean c;
    public List<String> d;
    public String e;
    public Boolean f;
    public Boolean g;
    public Boolean h;
    public Boolean i;
    public Boolean j;
    @SerializedName("disableAutoHRFor")
    private List<String> k = new ArrayList();
    @SerializedName("minBatteryPerForWatchFaceUpload")
    private Integer l = 5;
    @SerializedName("minTimeGapForShowingNextAutoHRPopup")
    private Integer m;
    @SerializedName("logoType")
    private String n;
    @SerializedName("isQRCodeOnboardingSupported")
    private Boolean o;

    public List<String> getDisableAutoHRFor() {
        return this.k;
    }

    public Boolean getDiyWatchFaceSupported() {
        return this.f;
    }

    public String getIcon() {
        return this.e;
    }

    public Boolean getIs1kActivitySupported() {
        return this.i;
    }

    public Boolean getIsDiyWatchFaceSupported() {
        return this.f;
    }

    public Boolean getIsQRCodeOnboardingSupported() {
        return this.o;
    }

    public Boolean getIsWatchDiagnosticsSupported() {
        return this.h;
    }

    public String getLogoType() {
        return this.n;
    }

    public Integer getMinBatteryPerForWatchFaceUpload() {
        return this.l;
    }

    public Integer getMinTimeGapForShowingNextAutoHRPopup() {
        return this.m;
    }

    public String getName() {
        return this.f7019a;
    }

    public Boolean getRemoteFrameworkSupported() {
        return this.c;
    }

    public List<String> getScanFilter() {
        return this.d;
    }

    public Boolean getSleepAndEnergyScoreSupported() {
        return this.j;
    }

    public Boolean getTapAndPaySupported() {
        return this.g;
    }

    public String getType() {
        return this.b;
    }

    public Boolean getWatchDiagnosticsSupported() {
        return this.h;
    }

    public Boolean isTapAndPaySupported() {
        return this.g;
    }

    public void setDisableAutoHRFor(List<String> list) {
        this.k = list;
    }

    public void setDiyWatchFaceSupported(Boolean bool) {
        this.f = bool;
    }

    public void setIcon(String str) {
        this.e = str;
    }

    public void setIs1kActivitySupported(Boolean bool) {
        this.i = bool;
    }

    public void setIsDiyWatchFaceSupported(Boolean bool) {
        this.f = bool;
    }

    public void setIsQRCodeOnboardingSupported(Boolean bool) {
        this.o = bool;
    }

    public void setIsWatchDiagnosticsSupported(Boolean bool) {
        this.h = bool;
    }

    public void setLogoType(String str) {
        this.n = str;
    }

    public void setMinBatteryPerForWatchFaceUpload(Integer num) {
        this.l = num;
    }

    public void setMinTimeGapForShowingNextAutoHRPopup(Integer num) {
        this.m = num;
    }

    public void setName(String str) {
        this.f7019a = str;
    }

    public void setRemoteFrameworkSupported(Boolean bool) {
        this.c = bool;
    }

    public void setScanFilter(List<String> list) {
        this.d = list;
    }

    public void setSleepAndEnergyScoreSupported(Boolean bool) {
        this.j = bool;
    }

    public void setTapAndPaySupported(Boolean bool) {
        this.g = bool;
    }

    public void setType(String str) {
        this.b = str;
    }

    public void setWatchDiagnosticsSupported(Boolean bool) {
        this.h = bool;
    }
}
