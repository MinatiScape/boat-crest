package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class DisplayModeData {
    public static DisplayModeData b;

    /* renamed from: a  reason: collision with root package name */
    public String f7020a;

    public static DisplayModeData getInstance() {
        if (b == null) {
            b = new DisplayModeData();
        }
        return b;
    }

    public String getDisplay_mode() {
        return this.f7020a;
    }

    public void setDisplay_mode(String str) {
        this.f7020a = str;
    }
}
