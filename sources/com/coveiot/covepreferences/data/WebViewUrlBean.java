package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class WebViewUrlBean {

    /* renamed from: a  reason: collision with root package name */
    public String f7050a;
    public String b;
    public String c;

    public WebViewUrlBean(String str, String str2, String str3) {
        this.f7050a = str;
        this.b = str2;
        this.c = str3;
    }

    public String getBrowsePlan() {
        return this.b;
    }

    public String getOnboarding() {
        return this.f7050a;
    }

    public String getUserPlanHistory() {
        return this.c;
    }

    public void setBrowsePlan(String str) {
        this.b = str;
    }

    public void setOnboarding(String str) {
        this.f7050a = str;
    }

    public void setUserPlanHistory(String str) {
        this.c = str;
    }
}
