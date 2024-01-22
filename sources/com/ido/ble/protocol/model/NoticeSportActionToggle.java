package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class NoticeSportActionToggle {
    public static final int OPERATE_ACTION_TOGGLE = 5;
    public static final int OPERATE_END = 4;
    public static final int OPERATE_RECOVER = 3;
    public static final int OPERATE_START = 1;
    public static final int OPERATE_STOP = 2;
    public int action_type;
    public int day;
    public int err_code;
    public int height_heart;
    public int hour;
    public int low_heart;
    public int minute;
    public int month;
    public int operate;
    public int second;
    public int time;
    public int training_offset;
    public int type;
    public int version;
    public int year;

    public String toString() {
        return "NoticeSportActionToggle{err_code=" + this.err_code + ", operate=" + this.operate + ", version=" + this.version + ", type=" + this.type + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", training_offset=" + this.training_offset + ", time=" + this.time + ", low_heart=" + this.low_heart + ", height_heart=" + this.height_heart + ", action_type=" + this.action_type + '}';
    }
}
