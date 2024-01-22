package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPWatchFaceInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7675a;
    public int b;
    public int c;
    public List<WatchFaceBean> d;

    /* loaded from: classes9.dex */
    public static class WatchFaceBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7676a;
        public String b;
        public String c;

        public WatchFaceBean(int i, String str, String str2) {
            this.f7676a = i;
            this.b = str;
            this.c = str2;
        }

        public String getFile() {
            return this.c;
        }

        public int getId() {
            return this.f7676a;
        }

        public String getPreview() {
            return this.b;
        }
    }

    public CRPWatchFaceInfo(int i, int i2, int i3, List<WatchFaceBean> list) {
        this.f7675a = i;
        this.b = i2;
        this.c = i3;
        this.d = list;
    }

    public List<WatchFaceBean> getList() {
        return this.d;
    }

    public int getPageIndex() {
        return this.c;
    }

    public int getPrePage() {
        return this.b;
    }

    public int getTotal() {
        return this.f7675a;
    }

    public void setList(List<WatchFaceBean> list) {
        this.d = list;
    }

    public void setPageIndex(int i) {
        this.c = i;
    }

    public void setPrePage(int i) {
        this.b = i;
    }

    public void setTotal(int i) {
        this.f7675a = i;
    }
}
