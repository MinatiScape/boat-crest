package com.abupdate.iot_libs.info;

import java.text.SimpleDateFormat;
/* loaded from: classes.dex */
public class DownParamInfo {
    public int _id;
    public String deltaID;
    public String downEnd;
    public String downIp;
    public int downSize;
    public String downStart;
    public String downloadStatus;
    public String mid = DeviceInfo.getInstance().mid;

    public DownParamInfo() {
    }

    public static String getFormatTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
    }

    public String displayString() {
        return "mid" + this.mid + "', deltaID='" + this.deltaID + "', downloadStatus='" + this.downloadStatus + "', downStart='" + this.downStart + "', downEnd='" + this.downEnd + '\'';
    }

    public String toString() {
        return "DownParamInfo{\nmid='" + this.mid + "'\ndeltaID='" + this.deltaID + "'\ndownloadStatus='" + this.downloadStatus + "'\ndownStart='" + this.downStart + "'\ndownEnd='" + this.downEnd + "'\n}";
    }

    public DownParamInfo(String str, String str2, long j, long j2, int i, String str3) {
        this.deltaID = str;
        this.downEnd = String.valueOf(j2);
        this.downloadStatus = str2;
        this.downStart = String.valueOf(j);
        this.downIp = str3;
        this.downSize = i;
    }

    public DownParamInfo(String str, String str2, long j, long j2, String str3) {
        this.deltaID = str;
        this.downEnd = String.valueOf(j2);
        this.downloadStatus = str2;
        this.downStart = String.valueOf(j);
    }
}
