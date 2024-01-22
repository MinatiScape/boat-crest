package com.coveiot.coveaccess.onboarding.model;
/* loaded from: classes8.dex */
public class ModifyPhoneNumberReq {

    /* renamed from: a  reason: collision with root package name */
    public int f6665a;
    public String b;
    public String c;

    public ModifyPhoneNumberReq(int i, String str, String str2) {
        this.f6665a = i;
        this.b = str;
        this.c = str2;
    }

    public String getMobileNumber() {
        return this.b;
    }

    public int getUserId() {
        return this.f6665a;
    }

    public String getVerificationCode() {
        return this.c;
    }
}
