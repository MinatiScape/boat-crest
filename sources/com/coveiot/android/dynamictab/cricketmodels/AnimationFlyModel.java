package com.coveiot.android.dynamictab.cricketmodels;

import java.util.List;
/* loaded from: classes4.dex */
public class AnimationFlyModel {

    /* renamed from: a  reason: collision with root package name */
    public String f4320a;
    public int b;
    public String c;
    public String d;
    public DataBean e;

    /* loaded from: classes4.dex */
    public static class DataBean {

        /* renamed from: a  reason: collision with root package name */
        public String f4321a;
        public List<StickersBean> b;

        /* loaded from: classes4.dex */
        public static class StickersBean {

            /* renamed from: a  reason: collision with root package name */
            public String f4322a;
            public String b;

            public String getImageUrl() {
                return this.b;
            }

            public String getStickerId() {
                return this.f4322a;
            }

            public void setImageUrl(String str) {
                this.b = str;
            }

            public void setStickerId(String str) {
                this.f4322a = str;
            }
        }

        public String getAction() {
            return this.f4321a;
        }

        public List<StickersBean> getStickers() {
            return this.b;
        }

        public void setAction(String str) {
            this.f4321a = str;
        }

        public void setStickers(List<StickersBean> list) {
            this.b = list;
        }
    }

    public DataBean getData() {
        return this.e;
    }

    public String getResId() {
        return this.c;
    }

    public String getResType() {
        return this.f4320a;
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
        this.f4320a = str;
    }

    public void setResVer(int i) {
        this.b = i;
    }

    public void setStatus(String str) {
        this.d = str;
    }
}
