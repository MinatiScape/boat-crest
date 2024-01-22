package com.coveiot.android.leonardo.utils;
/* loaded from: classes5.dex */
public enum PreferenceType {
    SF_APP("cloveAppPref");
    
    private String fileName;

    PreferenceType(String str) {
        this.fileName = str;
    }

    public String getFileName() {
        return this.fileName;
    }
}
