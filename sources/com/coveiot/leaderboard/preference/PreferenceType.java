package com.coveiot.leaderboard.preference;
/* loaded from: classes9.dex */
public enum PreferenceType {
    CLOVE_COMMON_APP("cloveCommonAppPref");
    
    private String fileName;

    PreferenceType(String str) {
        this.fileName = str;
    }

    public String getFileName() {
        return this.fileName;
    }
}
