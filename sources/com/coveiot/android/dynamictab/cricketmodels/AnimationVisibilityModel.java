package com.coveiot.android.dynamictab.cricketmodels;
/* loaded from: classes4.dex */
public class AnimationVisibilityModel {

    /* renamed from: a  reason: collision with root package name */
    public String f4328a;
    public int b;
    public String c;
    public String d;
    public DataBean e;

    /* loaded from: classes4.dex */
    public static class DataBean {

        /* renamed from: a  reason: collision with root package name */
        public String f4329a;
        public String b;

        public String getAction() {
            return this.f4329a;
        }

        public String getValue() {
            return this.b;
        }

        public void setAction(String str) {
            this.f4329a = str;
        }

        public void setValue(String str) {
            this.b = str;
        }
    }

    public DataBean getData() {
        return this.e;
    }

    public String getResId() {
        return this.c;
    }

    public String getResType() {
        return this.f4328a;
    }

    public int getResVer() {
        return this.b;
    }

    public String getStatus() {
        return this.d;
    }

    public void setData(DataBean dataBean) {
        this.e = dataBean;
    }

    public void setResId(String str) {
        this.c = str;
    }

    public void setResType(String str) {
        this.f4328a = str;
    }

    public void setResVer(int i) {
        this.b = i;
    }

    public void setStatus(String str) {
        this.d = str;
    }
}
