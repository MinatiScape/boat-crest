package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class WatchDiagnosticFeature {

    /* renamed from: a  reason: collision with root package name */
    public WatchDiagnosticFeatureType f3454a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public WatchDiagnosticFeature(WatchDiagnosticFeatureType watchDiagnosticFeatureType) {
        this.f3454a = watchDiagnosticFeatureType;
    }

    public int getButtonPosition() {
        return this.e;
    }

    public int getColor() {
        return this.c;
    }

    public int getSensorType() {
        return this.f;
    }

    public int getTimeout() {
        return this.b;
    }

    public int getVibrationCount() {
        return this.d;
    }

    public WatchDiagnosticFeatureType getmFeatureType() {
        return this.f3454a;
    }

    public void setButtonPosition(int i) {
        this.e = i;
    }

    public void setColor(int i) {
        this.c = i;
    }

    public void setSensorType(int i) {
        this.f = i;
    }

    public void setTimeout(int i) {
        this.b = i;
    }

    public void setVibrationCount(int i) {
        this.d = i;
    }
}
