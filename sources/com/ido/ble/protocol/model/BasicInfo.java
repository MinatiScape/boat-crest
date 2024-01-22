package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BasicInfo implements Serializable {
    public static final int BATTERY_STATE_CHARGING = 1;
    public static final int BATTERY_STATE_ENERGY_FULL = 2;
    public static final int BATTERY_STATE_ENERGY_LOW = 3;
    public static final int BATTERY_STATE_NORMAL = 0;
    public static final int DEV_TYPE_BRACELET = 1;
    public static final int DEV_TYPE_INVALID = 0;
    public static final int DEV_TYPE_WATCH = 2;
    public static final int MODE_SLEEP = 1;
    public static final int MODE_SPORT = 0;
    public static final int PAIR_FLAG_BIND = 1;
    public static final int PAIR_FLAG_NOT_BIND = 0;
    public static final int PLATFORM_5340_80 = 80;
    public static final int PLATFORM_APOLLO3 = 30;
    public static final int PLATFORM_CYPRESS = 20;
    public static final int PLATFORM_FU_RUI_KUN_70 = 70;
    public static final int PLATFORM_HUI_DING = 40;
    public static final int PLATFORM_JU_XIN_90 = 90;
    public static final int PLATFORM_NORDIC = 0;
    public static final int PLATFORM_REALTEK = 10;
    public static final int PLATFORM_TAI_LING_WEI = 50;
    public static final int PLATFORM_TAI_LING_WEI_60 = 60;
    public static final int REBOOT_FLAG_NO = 0;
    public static final int REBOOT_FLAG_YES = 1;
    private static final long serialVersionUID = 1;
    public int battStatus;
    public int bind_confirm_method;
    public int bind_confirm_timeout;
    public int bootload_version;
    public int deivceId;
    public int dev_type;
    public int energe;
    public int firmwareVersion;
    public int mode;
    public int pairFlag;
    public int platform;
    public int reboot;
    public int shape;
    public int show_bind_choice_ui;
    public int user_defined_dial_main_version;
    public int user_defined_dial_sub_version;

    public String toString() {
        return "BasicInfo{deivceId=" + this.deivceId + ", firmwareVersion=" + this.firmwareVersion + ", battStatus=" + this.battStatus + ", mode=" + this.mode + ", pairFlag=" + this.pairFlag + ", reboot=" + this.reboot + ", energe=" + this.energe + ", bind_confirm_method=" + this.bind_confirm_method + ", bind_confirm_timeout=" + this.bind_confirm_timeout + ", platform=" + this.platform + ", shape=" + this.shape + ", dev_type=" + this.dev_type + ", user_defined_dial_main_version=" + this.user_defined_dial_main_version + ", user_defined_dial_sub_version=" + this.user_defined_dial_sub_version + ", show_bind_choice_ui=" + this.show_bind_choice_ui + ", bootload_version=" + this.bootload_version + '}';
    }
}
