package com.mappls.sdk.maps.style.model;
/* loaded from: classes11.dex */
public class MapplsStyle {

    /* renamed from: a  reason: collision with root package name */
    public String f12849a;
    public String b;
    public String c;
    public Integer d;
    public String e;

    public MapplsStyle(String str, String str2, String str3, String str4, Integer num) {
        this.f12849a = str3;
        this.b = str2;
        this.c = str4;
        this.d = num;
        this.e = str;
    }

    public String getDescription() {
        return this.f12849a;
    }

    public String getDisplayName() {
        return this.b;
    }

    public String getImageUrl() {
        return this.c;
    }

    public String getName() {
        return this.e;
    }

    public Integer isDefault() {
        return this.d;
    }

    public void setDescription(String str) {
        this.f12849a = str;
    }

    public void setDisplayName(String str) {
        this.b = str;
    }

    public void setImageUrl(String str) {
        this.c = str;
    }

    public void setIsDefault(Integer num) {
        this.d = num;
    }

    public void setName(String str) {
        this.e = str;
    }
}
