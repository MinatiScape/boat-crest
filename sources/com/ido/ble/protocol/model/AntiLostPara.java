package com.ido.ble.protocol.model;

import java.io.Serializable;
@Deprecated
/* loaded from: classes11.dex */
public class AntiLostPara implements Serializable {
    public static final int MODE_LONG_DISTANCE = 3;
    public static final int MODE_MID_DISTANCE = 2;
    public static final int MODE_NOT_ANTI_LOST = 0;
    public static final int MODE_SHORT_DISTANCE = 1;
    public static final int NOLINK_NEED_ANTI = 1;
    public static final int NOTLINK_NO_ANTI = 0;
    private static final long serialVersionUID = 1;
    public int anti_delay;
    public int anti_disconnect_delay;
    public int battStatus;
    public int is_disconnect_anti;
    public int mode;
    public int repetitions;
    public int rss;

    public String toString() {
        return "AntiLostPara{mode=" + this.mode + ", rss=" + this.rss + ", anti_delay=" + this.anti_delay + ", battStatus=" + this.battStatus + ", is_disconnect_anti=" + this.is_disconnect_anti + ", anti_disconnect_delay=" + this.anti_disconnect_delay + ", repetitions=" + this.repetitions + '}';
    }
}
