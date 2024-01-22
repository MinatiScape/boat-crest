package com.htsmart.wristband2.bean.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public class FunctionConfig extends AbstractConfig {
    public static final int FLAG_DISCONNECT_REMINDER = 6;
    public static final int FLAG_EXERCISE_TARGET = 7;
    public static final int FLAG_HOUR_STYLE = 2;
    public static final int FLAG_LENGTH_UNIT = 3;
    public static final int FLAG_STRENGTHEN_TEST = 1;
    public static final int FLAG_TEMPERATURE_UNIT = 4;
    public static final int FLAG_WEAR_WAY = 0;
    public static final int FLAG_WEATHER_SWITCH = 5;
    public static final int PACKET_LENGTH = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface Flag {
    }

    public FunctionConfig() {
    }

    public FunctionConfig(byte[] bArr) {
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
