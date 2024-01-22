package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGVersionInfo {
    @Nullable
    private String detailVersion;
    private int gestureAlgorithmVersion;
    private int hrAlgorithmVersion;
    private int pcbAlgorithmVersion;
    private int sdkVersion;
    private int sleepAlgorithmVersion;
    private int stepAlgorithmVersion;

    @Nullable
    public String getDetailVersion() {
        return this.detailVersion;
    }

    public int getGestureAlgorithmVersion() {
        return this.gestureAlgorithmVersion;
    }

    public int getHrAlgorithmVersion() {
        return this.hrAlgorithmVersion;
    }

    public int getPcbAlgorithmVersion() {
        return this.pcbAlgorithmVersion;
    }

    public int getSdkVersion() {
        return this.sdkVersion;
    }

    public int getSleepAlgorithmVersion() {
        return this.sleepAlgorithmVersion;
    }

    public int getStepAlgorithmVersion() {
        return this.stepAlgorithmVersion;
    }

    public void setDetailVersion(@Nullable String str) {
        this.detailVersion = str;
    }

    public void setGestureAlgorithmVersion(int i) {
        this.gestureAlgorithmVersion = i;
    }

    public void setHrAlgorithmVersion(int i) {
        this.hrAlgorithmVersion = i;
    }

    public void setPcbAlgorithmVersion(int i) {
        this.pcbAlgorithmVersion = i;
    }

    public void setSdkVersion(int i) {
        this.sdkVersion = i;
    }

    public void setSleepAlgorithmVersion(int i) {
        this.sleepAlgorithmVersion = i;
    }

    public void setStepAlgorithmVersion(int i) {
        this.stepAlgorithmVersion = i;
    }

    public String toString() {
        return "VersionInfo{sdkVersion=" + this.sdkVersion + ", hrAlgorithmVersion=" + this.hrAlgorithmVersion + ", sleepAlgorithmVersion=" + this.sleepAlgorithmVersion + ", stepAlgorithmVersion=" + this.stepAlgorithmVersion + ", gestureAlgorithmVersion=" + this.gestureAlgorithmVersion + ", pcbAlgorithmVersion=" + this.pcbAlgorithmVersion + ", detailVersion='" + this.detailVersion + "'}";
    }
}
