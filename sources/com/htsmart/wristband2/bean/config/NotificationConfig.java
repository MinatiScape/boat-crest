package com.htsmart.wristband2.bean.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public class NotificationConfig extends AbstractConfig {
    public static final int FLAG_APPLE_MUSIC = 21;
    public static final int FLAG_CALENDAR = 17;
    public static final int FLAG_EMAIL = 14;
    public static final int FLAG_FACEBOOK = 4;
    public static final int FLAG_FACEBOOK_MESSENGER = 11;
    public static final int FLAG_HIKE = 19;
    public static final int FLAG_INSTAGRAM = 7;
    public static final int FLAG_KAKAO = 12;
    public static final int FLAG_LINE = 10;
    public static final int FLAG_LINKEDIN = 6;
    public static final int FLAG_OTHERS_APP = 31;
    public static final int FLAG_PINTEREST = 8;
    public static final int FLAG_QQ = 2;
    public static final int FLAG_SKYPE = 13;
    public static final int FLAG_SMS = 1;
    public static final int FLAG_SNAPCHAT = 18;
    public static final int FLAG_TELEGRAM = 15;
    public static final int FLAG_TELEPHONE = 0;
    public static final int FLAG_TIKTOK = 23;
    public static final int FLAG_TWITTER = 5;
    public static final int FLAG_VIBER = 16;
    public static final int FLAG_WECHAT = 3;
    public static final int FLAG_WHATSAPP = 9;
    public static final int FLAG_YOUTUBE = 20;
    public static final int FLAG_ZOOM = 22;
    public static final int PACKET_LENGTH = 4;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface Flag {
    }

    public NotificationConfig() {
    }

    public NotificationConfig(byte[] bArr) {
        super(bArr);
    }

    public boolean isFlagEnable(int i) {
        return b(i);
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 4;
    }

    public void setFlagEnable(int i, boolean z) {
        a(i, z);
    }
}
