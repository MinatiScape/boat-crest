package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.CommonAction;
/* loaded from: classes.dex */
public class EABleAncsSw {
    public EABleAncsSwItem s_email;
    public EABleAncsSwItem s_incomingcall;
    public EABleAncsSwItem s_missedcall;
    public EABleAncsSwItem s_schedule;
    public EABleAncsSwItem s_sms;
    public EABleAncsSwItem s_social;

    /* loaded from: classes.dex */
    public static class EABleAncsSwItem {
        public CommonAction e_action;
        public int sw;

        public CommonAction getE_action() {
            return this.e_action;
        }

        public int getSw() {
            return this.sw;
        }

        public void setE_action(CommonAction commonAction) {
            this.e_action = commonAction;
        }

        public void setSw(int i) {
            this.sw = i;
        }

        public String toString() {
            return "EABleAncsSwItem{sw=" + this.sw + ", e_action=" + this.e_action + '}';
        }
    }

    public EABleAncsSwItem getS_email() {
        return this.s_email;
    }

    public EABleAncsSwItem getS_incomingcall() {
        return this.s_incomingcall;
    }

    public EABleAncsSwItem getS_missedcall() {
        return this.s_missedcall;
    }

    public EABleAncsSwItem getS_schedule() {
        return this.s_schedule;
    }

    public EABleAncsSwItem getS_sms() {
        return this.s_sms;
    }

    public EABleAncsSwItem getS_social() {
        return this.s_social;
    }

    public void setS_email(EABleAncsSwItem eABleAncsSwItem) {
        this.s_email = eABleAncsSwItem;
    }

    public void setS_incomingcall(EABleAncsSwItem eABleAncsSwItem) {
        this.s_incomingcall = eABleAncsSwItem;
    }

    public void setS_missedcall(EABleAncsSwItem eABleAncsSwItem) {
        this.s_missedcall = eABleAncsSwItem;
    }

    public void setS_schedule(EABleAncsSwItem eABleAncsSwItem) {
        this.s_schedule = eABleAncsSwItem;
    }

    public void setS_sms(EABleAncsSwItem eABleAncsSwItem) {
        this.s_sms = eABleAncsSwItem;
    }

    public void setS_social(EABleAncsSwItem eABleAncsSwItem) {
        this.s_social = eABleAncsSwItem;
    }

    public String toString() {
        return "EABleAncsSw{s_incomingcall=" + this.s_incomingcall + ", s_missedcall=" + this.s_missedcall + ", s_sms=" + this.s_sms + ", s_social=" + this.s_social + ", s_email=" + this.s_email + ", s_schedule=" + this.s_schedule + '}';
    }
}
