package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class AlarmV3 implements Serializable {
    public static final int STATUS_DISPLAY = 85;
    public static final int STATUS_NOT_DISPLAY = 170;
    public static final int TYPE_BATH = 12;
    public static final int TYPE_BRUSH_TEETH = 9;
    public static final int TYPE_COURSE = 11;
    public static final int TYPE_CUSTOMIZE = 7;
    public static final int TYPE_CUSTOMIZE_NAME = 42;
    public static final int TYPE_DINNER = 8;
    public static final int TYPE_ENGAGEMENT = 4;
    public static final int TYPE_EXERCISE = 2;
    public static final int TYPE_GATHERING = 5;
    public static final int TYPE_GETUP = 0;
    public static final int TYPE_LEARN = 13;
    public static final int TYPE_MEDICINE = 3;
    public static final int TYPE_MEETING = 6;
    public static final int TYPE_PLAY = 14;
    public static final int TYPE_REST = 10;
    public static final int TYPE_SLEEP = 1;
    private static final long serialVersionUID = 1;
    public int alarm_id;
    public int delay_min;
    public int hour;
    public int minute;
    public String name;
    private boolean on_off;
    private int repeat;
    public int repeat_times;
    public int shock_on_off;
    public int tsnooze_duration;
    public int type;
    public int status = 85;
    private boolean[] weekRepeat = new boolean[7];

    private void byteToWeekRepeat() {
        this.weekRepeat = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat & (1 << i2)) != 0) {
                this.weekRepeat[i] = true;
            } else {
                this.weekRepeat[i] = false;
            }
            i = i2;
        }
        if ((this.repeat & 1) == 1) {
            this.on_off = true;
        } else {
            this.on_off = false;
        }
    }

    private int weekRepeatToByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weekRepeat;
    }

    public boolean isOn_off() {
        byteToWeekRepeat();
        return this.on_off;
    }

    public void setOn_off(boolean z) {
        this.on_off = z;
        this.repeat = weekRepeatToByte(this.weekRepeat, z);
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
        this.repeat = weekRepeatToByte(zArr, this.on_off);
    }

    public String toString() {
        return "AlarmV3{alarm_id=" + this.alarm_id + ", on_off=" + this.on_off + ", status=" + this.status + ", type=" + this.type + ", hour=" + this.hour + ", minute=" + this.minute + ", repeat=" + this.repeat + ", weekRepeat=" + Arrays.toString(getWeekRepeat()) + ", tsnooze_duration=" + this.tsnooze_duration + ", delay_min=" + this.delay_min + ", name='" + this.name + "', shock_on_off=" + this.shock_on_off + ", repeat_times=" + this.repeat_times + '}';
    }
}
