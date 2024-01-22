package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGFileOffset {
    private int checkCode;
    private int offset;

    public TGFileOffset() {
    }

    public TGFileOffset(int i, int i2) {
        this.offset = i;
        this.checkCode = i2;
    }

    public int getCheckCode() {
        return this.checkCode;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setCheckCode(int i) {
        this.checkCode = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public String toString() {
        return "TGFileOffset{offset=" + this.offset + ", checkCode=" + this.checkCode + '}';
    }
}
