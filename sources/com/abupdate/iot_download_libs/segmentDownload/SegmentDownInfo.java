package com.abupdate.iot_download_libs.segmentDownload;
/* loaded from: classes.dex */
public class SegmentDownInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f1884a;
    public String b;
    public long c;
    public long d;

    public long getEndpos() {
        return this.d;
    }

    public String getMd5() {
        return this.b;
    }

    public int getNum() {
        return this.f1884a;
    }

    public long getStartpos() {
        return this.c;
    }

    public void setEndpos(long j) {
        this.d = j;
    }

    public void setMd5(String str) {
        this.b = str;
    }

    public void setNum(int i) {
        this.f1884a = i;
    }

    public void setStartpos(long j) {
        this.c = j;
    }
}
