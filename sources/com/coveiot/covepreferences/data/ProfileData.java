package com.coveiot.covepreferences.data;

import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes8.dex */
public class ProfileData {
    public static ProfileData n;

    /* renamed from: a  reason: collision with root package name */
    public String f7034a;
    public String b;
    public String c;
    public boolean d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public int l;
    public String m;

    public static void clearInstance() {
        n = null;
    }

    public static ProfileData getInstance() {
        if (n == null) {
            n = new ProfileData();
        }
        return n;
    }

    public String getAppTrackerId() {
        return this.m;
    }

    public String getDob() {
        return this.g;
    }

    public String getDpUrl() {
        return this.b;
    }

    public String getEmail() {
        return this.f;
    }

    public String getGender() {
        return this.k;
    }

    public String getHeight() {
        String str = this.h;
        return str == null ? "165" : str;
    }

    public String getMobileNumber() {
        return this.c;
    }

    public String getName() {
        return this.e;
    }

    public String getProfile_pic() {
        return this.f7034a;
    }

    public String getStride_length() {
        return this.j;
    }

    public int getUserId() {
        return this.l;
    }

    public String getWeight() {
        String str = this.i;
        return str == null ? BleConst.GetMusicControl : str;
    }

    public boolean isExistingUser() {
        return this.d;
    }

    public void setAppTrackerId(String str) {
        this.m = str;
    }

    public void setDob(String str) {
        this.g = str;
    }

    public void setDpUrl(String str) {
        this.b = str;
    }

    public void setEmail(String str) {
        this.f = str;
    }

    public void setExistingUser(boolean z) {
        this.d = z;
    }

    public void setGender(String str) {
        this.k = str;
    }

    public void setHeight(String str) {
        this.h = str;
    }

    public void setMobileNumber(String str) {
        this.c = str;
    }

    public void setName(String str) {
        this.e = str;
    }

    public void setProfile_pic(String str) {
        this.f7034a = str;
    }

    public void setStride_length(String str) {
        this.j = str;
    }

    public void setUserId(int i) {
        this.l = i;
    }

    public void setWeight(String str) {
        this.i = str;
    }
}
