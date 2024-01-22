package com.coveiot.coveaccess.userappsetting;

import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
/* loaded from: classes8.dex */
public class SaveSedentaryAlertSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6856a;
    public String b;
    public String c;
    public String d;
    public SUserAppSettingsReq.SedentaryAlertBean.SiestaBean e;

    /* loaded from: classes8.dex */
    public static class SiestaBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6857a;

        public boolean isActive() {
            return this.f6857a;
        }

        public void setActive(boolean z) {
            this.f6857a = z;
        }
    }

    public String getEndTime() {
        return this.c;
    }

    public String getInterval() {
        return this.d;
    }

    public SUserAppSettingsReq.SedentaryAlertBean.SiestaBean getSiesta() {
        return this.e;
    }

    public String getStartTime() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6856a;
    }

    public void setActive(boolean z) {
        this.f6856a = z;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setInterval(String str) {
        this.d = str;
    }

    public void setSiesta(SUserAppSettingsReq.SedentaryAlertBean.SiestaBean siestaBean) {
        this.e = siestaBean;
    }

    public void setStartTime(String str) {
        this.b = str;
    }
}
