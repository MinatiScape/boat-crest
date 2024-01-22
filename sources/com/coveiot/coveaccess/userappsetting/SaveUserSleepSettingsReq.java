package com.coveiot.coveaccess.userappsetting;
/* loaded from: classes8.dex */
public class SaveUserSleepSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6858a;
    public SleepTimeBean b;
    public NapTimeBean c;

    /* loaded from: classes8.dex */
    public static class NapTimeBean {

        /* renamed from: a  reason: collision with root package name */
        public String f6859a;
        public String b;
        public boolean c;
        public String d;
        public boolean e;

        public String getEndTime() {
            return this.b;
        }

        public String getNotifyTime() {
            return this.d;
        }

        public String getStartTime() {
            return this.f6859a;
        }

        public boolean isActive() {
            return this.c;
        }

        public boolean isNotifyActive() {
            return this.e;
        }

        public void setActive(boolean z) {
            this.c = z;
        }

        public void setEndTime(String str) {
            this.b = str;
        }

        public void setNotifyActive(boolean z) {
            this.e = z;
        }

        public void setNotifyTime(String str) {
            this.d = str;
        }

        public void setStartTime(String str) {
            this.f6859a = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SleepTimeBean {

        /* renamed from: a  reason: collision with root package name */
        public String f6860a;
        public String b;
        public boolean c;
        public String d;
        public boolean e;

        public String getEndTime() {
            return this.b;
        }

        public String getNotifyTime() {
            return this.d;
        }

        public String getStartTime() {
            return this.f6860a;
        }

        public boolean isActive() {
            return this.c;
        }

        public boolean isNotifyActive() {
            return this.e;
        }

        public void setActive(boolean z) {
            this.c = z;
        }

        public void setEndTime(String str) {
            this.b = str;
        }

        public void setNotifyActive(boolean z) {
            this.e = z;
        }

        public void setNotifyTime(String str) {
            this.d = str;
        }

        public void setStartTime(String str) {
            this.f6860a = str;
        }
    }

    public NapTimeBean getNapTime() {
        return this.c;
    }

    public SleepTimeBean getSleepTime() {
        return this.b;
    }

    public boolean isActive() {
        return this.f6858a;
    }

    public void setActive(boolean z) {
        this.f6858a = z;
    }

    public void setNapTime(NapTimeBean napTimeBean) {
        this.c = napTimeBean;
    }

    public void setSleepTime(SleepTimeBean sleepTimeBean) {
        this.b = sleepTimeBean;
    }
}
