package com.coveiot.sdk.ble.utils;
/* loaded from: classes9.dex */
public enum BlePreferenceType {
    CLOVE_APP("cloveAppPref"),
    CLOVE_NET_APP("cloveNetAppPref"),
    CLOVE_COMMON_APP("cloveCommonAppPref");
    

    /* renamed from: a  reason: collision with root package name */
    private String f7584a;

    BlePreferenceType(String str) {
        this.f7584a = str;
    }

    public String getFileName() {
        return this.f7584a;
    }
}
