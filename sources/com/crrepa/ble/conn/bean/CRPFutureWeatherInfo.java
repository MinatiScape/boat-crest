package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPFutureWeatherInfo {

    /* renamed from: a  reason: collision with root package name */
    public List<FutureBean> f7648a;

    /* loaded from: classes9.dex */
    public static class FutureBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7649a;
        public int b;
        public int c;

        public FutureBean() {
        }

        public FutureBean(int i, int i2, int i3) {
            this.f7649a = i;
            this.b = i2;
            this.c = i3;
        }

        public int getHighTemperature() {
            return this.c;
        }

        public int getLowTemperature() {
            return this.b;
        }

        public int getWeatherId() {
            return this.f7649a;
        }

        public void setHighTemperature(int i) {
            this.c = i;
        }

        public void setLowTemperature(int i) {
            this.b = i;
        }

        public void setWeatherId(int i) {
            this.f7649a = i;
        }
    }

    public CRPFutureWeatherInfo() {
    }

    public CRPFutureWeatherInfo(List<FutureBean> list) {
        this.f7648a = list;
    }

    public List<FutureBean> getFuture() {
        return this.f7648a;
    }

    public void setFuture(List<FutureBean> list) {
        this.f7648a = list;
    }
}
