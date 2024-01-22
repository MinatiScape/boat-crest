package com.abupdate.iot_libs.info;
/* loaded from: classes.dex */
public class ErrorFileParamInfo {
    public int _id;
    public String deltaID;
    public String errorType;
    public String mid;
    public String uploadFile;

    public ErrorFileParamInfo(String str, String str2, String str3, String str4) {
        this.mid = str;
        this.deltaID = str2;
        this.errorType = str3;
        this.uploadFile = str4;
    }

    public String toString() {
        return "ErrorFileParamInfo{\nmid='" + this.mid + "'\ndeltaID='" + this.deltaID + "'\nerrorType='" + this.errorType + "'\nuploadFile='" + this.uploadFile + "'\n}";
    }

    public ErrorFileParamInfo(String str, String str2, String str3) {
        this.mid = str;
        this.errorType = str2;
        this.uploadFile = str3;
    }
}
