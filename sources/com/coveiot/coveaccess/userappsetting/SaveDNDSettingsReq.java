package com.coveiot.coveaccess.userappsetting;

import java.util.List;
/* loaded from: classes8.dex */
public class SaveDNDSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public List<ScheduleDNDListBean> f6837a;
    public boolean b;

    /* loaded from: classes8.dex */
    public static class ScheduleDNDListBean {

        /* renamed from: a  reason: collision with root package name */
        public String f6838a;
        public String b;

        public String getEndTime() {
            return this.b;
        }

        public String getStartTime() {
            return this.f6838a;
        }

        public void setEndTime(String str) {
            this.b = str;
        }

        public void setStartTime(String str) {
            this.f6838a = str;
        }
    }

    public List<ScheduleDNDListBean> getDndListBean() {
        return this.f6837a;
    }

    public boolean isActive() {
        return this.b;
    }

    public void setActive(boolean z) {
        this.b = z;
    }

    public void setDndListBean(List<ScheduleDNDListBean> list) {
        this.f6837a = list;
    }
}
