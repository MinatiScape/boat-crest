package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGNFCRfParam {
    @Nullable
    private byte[] data;
    private int type;

    @Nullable
    public byte[] getData() {
        return this.data;
    }

    public int getType() {
        return this.type;
    }

    public void setData(@Nullable byte[] bArr) {
        this.data = bArr;
    }

    public void setType(int i) {
        this.type = i;
    }
}
