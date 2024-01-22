package com.htsmart.wristband2.bean.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public class PageConfig extends AbstractConfig {
    public static final int FLAG_BLOOD_PRESSURE = 7;
    public static final int FLAG_CALORIES = 3;
    public static final int FLAG_DISTANCE = 2;
    public static final int FLAG_FIND_PHONE = 9;
    public static final int FLAG_HEART_RATE = 5;
    public static final int FLAG_ID = 10;
    public static final int FLAG_OXYGEN = 6;
    public static final int FLAG_SLEEP = 4;
    public static final int FLAG_STEP = 1;
    public static final int FLAG_STOP_WATCH = 13;
    public static final int FLAG_TIME = 0;
    public static final int FLAG_WEATHER = 8;
    public static final int PACKET_LENGTH = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface Flag {
    }

    public PageConfig() {
    }

    public PageConfig(byte[] bArr) {
        super(bArr);
    }

    public boolean isFlagEnable(int i) {
        return b(i);
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 2;
    }

    public void setFlagEnable(int i, boolean z) {
        a(i, z);
    }
}
