package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class WristbandNotification {
    public static final byte TYPE_APPLE_MUSIC = 35;
    public static final byte TYPE_CALENDAR = 20;
    public static final byte TYPE_EMAIL = 17;
    public static final byte TYPE_FACEBOOK = 7;
    public static final byte TYPE_FACEBOOK_MESSENGER = 14;
    public static final byte TYPE_HIKE = 33;
    public static final byte TYPE_INSTAGRAM = 10;
    public static final byte TYPE_KAKAO = 15;
    public static final byte TYPE_LINE = 13;
    public static final byte TYPE_LINKEDIN = 9;
    public static final byte TYPE_OTHERS_APP = -1;
    public static final byte TYPE_PINTEREST = 11;
    public static final byte TYPE_QQ = 5;
    public static final byte TYPE_SKYPE = 16;
    public static final byte TYPE_SMS = 4;
    public static final byte TYPE_SNAPCHAT = 21;
    public static final byte TYPE_TELEGRAM = 18;
    public static final byte TYPE_TELEPHONE_COMING = 1;
    public static final byte TYPE_TELEPHONE_LISTENED = 2;
    public static final byte TYPE_TELEPHONE_MISSED = 25;
    public static final byte TYPE_TELEPHONE_REJECT = 3;
    public static final byte TYPE_TIKTOK = 37;
    public static final byte TYPE_TWITTER = 8;
    public static final byte TYPE_VIBER = 19;
    public static final byte TYPE_WECHAT = 6;
    public static final byte TYPE_WHATSAPP = 12;
    public static final byte TYPE_YOUTUBE = 34;
    public static final byte TYPE_ZOOM = 36;

    /* renamed from: a  reason: collision with root package name */
    public byte f11970a;
    public String b;
    public String c;

    public String getContent() {
        return this.c;
    }

    public String getName() {
        return this.b;
    }

    public byte getType() {
        return this.f11970a;
    }

    public void setContent(String str) {
        this.c = str;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setType(byte b) {
        this.f11970a = b;
    }
}
