package com.realsil.sdk.dfu.model;
/* loaded from: classes12.dex */
public class OtaModeInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f13633a;
    public String b;

    public OtaModeInfo(int i) {
        this.f13633a = i;
    }

    public String getName() {
        return this.b;
    }

    public int getWorkmode() {
        return this.f13633a;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setWorkmode(int i) {
        this.f13633a = i;
    }

    public OtaModeInfo(int i, String str) {
        this.f13633a = i;
        this.b = str;
    }
}
