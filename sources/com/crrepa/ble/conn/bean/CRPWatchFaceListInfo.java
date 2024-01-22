package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPWatchFaceListInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7678a;
    public List<WatchFaceBean> b;

    /* loaded from: classes9.dex */
    public static class WatchFaceBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7679a;
        public String b;
        public int c;

        public WatchFaceBean(int i, String str, int i2) {
            this.f7679a = i;
            this.b = str;
            this.c = i2;
        }

        public int getId() {
            return this.c;
        }

        public int getIndex() {
            return this.f7679a;
        }

        public String getType() {
            return this.b;
        }

        public void setId(int i) {
            this.c = i;
        }

        public void setIndex(int i) {
            this.f7679a = i;
        }

        public void setType(String str) {
            this.b = str;
        }
    }

    public CRPWatchFaceListInfo(int i, List<WatchFaceBean> list) {
        this.f7678a = i;
        this.b = list;
    }

    public int getCount() {
        return this.f7678a;
    }

    public List<WatchFaceBean> getList() {
        return this.b;
    }

    public void setCount(int i) {
        this.f7678a = i;
    }

    public void setList(List<WatchFaceBean> list) {
        this.b = list;
    }
}
