package com.apex.bluetooth.model;

import java.util.List;
/* loaded from: classes.dex */
public class EABleInfoPush {
    public List<EABlePushSwitch> s_app_sw;

    /* loaded from: classes.dex */
    public static class EABlePushSwitch {
        public int sw;

        public int getSw() {
            return this.sw;
        }

        public void setSw(int i) {
            this.sw = i;
        }

        public String toString() {
            return "EABlePushSwitch{sw=" + this.sw + '}';
        }
    }

    public List<EABlePushSwitch> getS_app_sw() {
        return this.s_app_sw;
    }

    public void setS_app_sw(List<EABlePushSwitch> list) {
        this.s_app_sw = list;
    }

    public String toString() {
        return "EABleInfoPush{s_app_sw=" + this.s_app_sw + '}';
    }
}
