package com.coveiot.coveaccess.userappsetting;

import java.util.List;
/* loaded from: classes8.dex */
public class SaveWomenWellnessReq {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f6862a;
    public String b;
    public Integer c;
    public Integer d;
    public Integer e;
    public List<ReminderListBean> f = null;

    /* loaded from: classes8.dex */
    public static class ReminderListBean {

        /* renamed from: a  reason: collision with root package name */
        public Boolean f6863a;
        public String b;
        public Integer c;
        public String d;

        public Boolean getActive() {
            return this.f6863a;
        }

        public String getRemindAt() {
            return this.d;
        }

        public Integer getRemindBefore() {
            return this.c;
        }

        public String getType() {
            return this.b;
        }

        public void setActive(Boolean bool) {
            this.f6863a = bool;
        }

        public void setRemindAt(String str) {
            this.d = str;
        }

        public void setRemindBefore(Integer num) {
            this.c = num;
        }

        public void setType(String str) {
            this.b = str;
        }
    }

    public Boolean getActive() {
        return this.f6862a;
    }

    public Integer getCycleLength() {
        return this.c;
    }

    public String getCycleStartDate() {
        return this.b;
    }

    public Integer getPeriodLength() {
        return this.d;
    }

    public Integer getPmsLength() {
        return this.e;
    }

    public List<ReminderListBean> getReminderListBeans() {
        return this.f;
    }

    public void setActive(Boolean bool) {
        this.f6862a = bool;
    }

    public void setCycleLength(Integer num) {
        this.c = num;
    }

    public void setCycleStartDate(String str) {
        this.b = str;
    }

    public void setPeriodLength(Integer num) {
        this.d = num;
    }

    public void setPmsLength(Integer num) {
        this.e = num;
    }

    public void setReminderListBeans(List<ReminderListBean> list) {
        this.f = list;
    }
}
