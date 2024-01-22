package com.elvishew.xlog;
/* loaded from: classes9.dex */
public class LogItem {
    public int level;
    public String msg;
    public String stackTraceInfo;
    public String tag;
    public String threadInfo;

    public LogItem(int i, String str, String str2) {
        this.level = i;
        this.tag = str;
        this.msg = str2;
    }

    public LogItem(int i, String str, String str2, String str3, String str4) {
        this.level = i;
        this.tag = str;
        this.threadInfo = str2;
        this.stackTraceInfo = str3;
        this.msg = str4;
    }
}
