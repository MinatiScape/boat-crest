package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGFileResult {
    private int checkSum;
    private int code;
    private String md5;

    public int getCheckSum() {
        return this.checkSum;
    }

    public int getCode() {
        return this.code;
    }

    public String getMd5() {
        return this.md5;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public void setCheckSum(int i) {
        this.checkSum = i;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String toString() {
        return "FileResult{checkSum=" + this.checkSum + ", md5='" + this.md5 + "'}";
    }
}
