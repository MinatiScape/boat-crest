package com.ido.ble.icon.transfer;
/* loaded from: classes11.dex */
public class IconTranConfig {
    public static final int TYPE_MSG = 1;
    public static final int TYPE_SPORT_ANIMATION = 4;
    public static final int TYPE_SPORT_BIG = 3;
    public static final int TYPE_SPORT_MIDDLE = 5;
    public static final int TYPE_SPORT_SMALL = 2;
    public static final int TYPE_SPORT_SMALL_SMALL = 6;
    public int index;
    public int maxRetryTimes;
    public int type;

    public String toString() {
        return "IconTranConfig{type=" + this.type + ", index=" + this.index + ", maxRetryTimes=" + this.maxRetryTimes + '}';
    }
}
