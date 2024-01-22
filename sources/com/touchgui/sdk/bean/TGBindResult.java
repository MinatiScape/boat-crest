package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGBindResult {
    public static final int BOUND = 2;
    public static final int FAILURE = 1;
    public static final int SUCCESS = 0;
    private int encryptedVersion;
    @Nullable
    private String randomData;
    private int result;

    public int getEncryptedVersion() {
        return this.encryptedVersion;
    }

    @Nullable
    public String getRandomData() {
        return this.randomData;
    }

    public int getResult() {
        return this.result;
    }

    public void setEncryptedVersion(int i) {
        this.encryptedVersion = i;
    }

    public void setRandomData(@Nullable String str) {
        this.randomData = str;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public String toString() {
        return "TGBindResult{result=" + this.result + ", randomData='" + this.randomData + "'}";
    }
}
