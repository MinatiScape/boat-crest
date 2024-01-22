package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class SilentModeConfigAbstract {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3450a;
    public boolean b;

    public SilentModeConfigAbstract(boolean z, boolean z2) {
        this.f3450a = z;
        this.b = z2;
    }

    public boolean isSilentModeConfig() {
        return this.f3450a;
    }

    public boolean isVibrationConfig() {
        return this.b;
    }
}
