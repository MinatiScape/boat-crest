package com.crrepa.ble.trans.watchface.entity;
/* loaded from: classes9.dex */
public class WatchFaceEntity {

    /* renamed from: a  reason: collision with root package name */
    public int f7688a;
    public String b;
    public int c;
    public String d;
    public String e;

    public int getCode() {
        return this.f7688a;
    }

    public String getFile() {
        return this.e;
    }

    public String getMessage() {
        return this.b;
    }

    public String getPreview() {
        return this.d;
    }

    public int getTpl() {
        return this.c;
    }

    public void setCode(int i) {
        this.f7688a = i;
    }

    public void setFile(String str) {
        this.e = str;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setPreview(String str) {
        this.d = str;
    }

    public void setTpl(int i) {
        this.c = i;
    }
}
