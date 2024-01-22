package com.crrepa.ble.trans.watchface.entity;

import java.util.List;
/* loaded from: classes9.dex */
public class WatchFaceStoreEntity {

    /* renamed from: a  reason: collision with root package name */
    public int f7689a;
    public String b;
    public String c;
    public String d;
    public int e;
    public List<FacesBean> f;

    /* loaded from: classes9.dex */
    public static class FacesBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7690a;
        public int b;
        public String c;
        public String d;
        public List<Integer> e;

        public String getFile() {
            return this.d;
        }

        public int getId() {
            return this.f7690a;
        }

        public String getPreview() {
            return this.c;
        }

        public int getTpl() {
            return this.b;
        }

        public List<Integer> getTpls() {
            return this.e;
        }

        public void setFile(String str) {
            this.d = str;
        }

        public void setId(int i) {
            this.f7690a = i;
        }

        public void setPreview(String str) {
            this.c = str;
        }

        public void setTpl(int i) {
            this.b = i;
        }

        public void setTpls(List<Integer> list) {
            this.e = list;
        }
    }

    public int getCode() {
        return this.f7689a;
    }

    public String getCurrent_page() {
        return this.c;
    }

    public List<FacesBean> getFaces() {
        return this.f;
    }

    public String getMessage() {
        return this.b;
    }

    public String getPer_page() {
        return this.d;
    }

    public int getTotal() {
        return this.e;
    }

    public void setCode(int i) {
        this.f7689a = i;
    }

    public void setCurrent_page(String str) {
        this.c = str;
    }

    public void setFaces(List<FacesBean> list) {
        this.f = list;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setPer_page(String str) {
        this.d = str;
    }

    public void setTotal(int i) {
        this.e = i;
    }
}
