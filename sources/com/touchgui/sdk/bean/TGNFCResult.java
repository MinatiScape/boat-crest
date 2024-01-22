package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGNFCResult {
    private int code;
    @Nullable
    private byte[] data;

    public int getCode() {
        return this.code;
    }

    @Nullable
    public byte[] getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.code == 224;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(@Nullable byte[] bArr) {
        this.data = bArr;
    }
}
