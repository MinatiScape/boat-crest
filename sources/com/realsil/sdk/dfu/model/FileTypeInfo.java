package com.realsil.sdk.dfu.model;
/* loaded from: classes12.dex */
public class FileTypeInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f13631a;
    public String b;
    public boolean c;

    public FileTypeInfo(int i, String str) {
        this.f13631a = i;
        this.b = str;
    }

    public int getBitNumber() {
        return this.f13631a;
    }

    public String getName() {
        return this.b;
    }

    public boolean isSelected() {
        return this.c;
    }

    public void setBitNumber(int i) {
        this.f13631a = i;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setSelected(boolean z) {
        this.c = z;
    }
}
