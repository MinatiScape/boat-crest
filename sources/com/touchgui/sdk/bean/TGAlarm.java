package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGAlarm {
    private int hour;
    private int id;
    private int minute;
    private int repeat;
    private int set_day;
    private int set_hour;
    private int set_minute;
    private int set_month;
    private int set_year;
    private boolean show;
    private int snooze;
    private int type;

    public int getHour() {
        return this.hour;
    }

    public int getId() {
        return this.id;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getRepeat() {
        return this.repeat;
    }

    public int getSet_day() {
        return this.set_day;
    }

    public int getSet_hour() {
        return this.set_hour;
    }

    public int getSet_minute() {
        return this.set_minute;
    }

    public int getSet_month() {
        return this.set_month;
    }

    public int getSet_year() {
        return this.set_year;
    }

    public int getSnooze() {
        return this.snooze;
    }

    public int getType() {
        return this.type;
    }

    public boolean isOn() {
        return (this.repeat & 1) == 1;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setRepeat(int i) {
        this.repeat = i;
    }

    public void setSet_day(int i) {
        this.set_day = i;
    }

    public void setSet_hour(int i) {
        this.set_hour = i;
    }

    public void setSet_minute(int i) {
        this.set_minute = i;
    }

    public void setSet_month(int i) {
        this.set_month = i;
    }

    public void setSet_year(int i) {
        this.set_year = i;
    }

    public void setShow(boolean z) {
        this.show = z;
    }

    public void setSnooze(int i) {
        this.snooze = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[13];
        bArr[0] = (byte) this.id;
        bArr[1] = (byte) (this.show ? 85 : 170);
        bArr[2] = (byte) this.type;
        bArr[3] = (byte) this.hour;
        bArr[4] = (byte) this.minute;
        bArr[5] = (byte) this.repeat;
        bArr[6] = (byte) this.snooze;
        int i = this.set_year;
        bArr[7] = (byte) (i & 255);
        bArr[8] = (byte) ((i >> 8) & 255);
        bArr[9] = (byte) this.set_month;
        bArr[10] = (byte) this.set_day;
        bArr[11] = (byte) this.set_hour;
        bArr[12] = (byte) this.set_minute;
        return bArr;
    }
}
