package com.abupdate.iot_libs.info;
/* loaded from: classes.dex */
public class PushMessageInfo {
    public int _id;
    public String msgId;

    public PushMessageInfo() {
    }

    public String toString() {
        return "PushMessageInfo{\nmsgId='" + this.msgId + "'\n}";
    }

    public PushMessageInfo(String str) {
        this.msgId = str;
    }
}
