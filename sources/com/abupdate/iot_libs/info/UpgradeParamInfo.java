package com.abupdate.iot_libs.info;
/* loaded from: classes.dex */
public class UpgradeParamInfo {
    public int _id;
    public String deltaID;
    public String mid;
    public String updateStatus;

    public UpgradeParamInfo() {
        this.mid = DeviceInfo.getInstance().mid;
    }

    public String toString() {
        return "UpgradeParamInfo{\nupdateStatus='" + this.updateStatus + "'\ndeltaID='" + this.deltaID + "'\nmid='" + this.mid + "'\n}";
    }

    public UpgradeParamInfo(String str, String str2, String str3) {
        this.mid = str;
        this.deltaID = str2;
        this.updateStatus = str3;
    }
}
