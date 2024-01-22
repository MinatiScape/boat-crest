package com.touchgui.sdk.bean;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public class TGEventReminder {
    @Nullable
    private String content;
    private int day;
    private int hour;
    private int id;
    private int minute;
    private int month;
    private int repeat;
    private int type;
    private int year;

    private void updateRepeatFlag() {
        int i = this.repeat & (-3);
        this.repeat = i;
        if ((i >> 2) > 0) {
            this.repeat = i | 2;
        }
    }

    @Nullable
    public String getContent() {
        return this.content;
    }

    public byte[] getContentBytes() {
        return !TextUtils.isEmpty(this.content) ? this.content.getBytes(StandardCharsets.UTF_8) : new byte[0];
    }

    public int getContentLen() {
        if (TextUtils.isEmpty(this.content)) {
            return 0;
        }
        return getContentBytes().length;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getId() {
        return this.id;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getRepeat() {
        return this.repeat;
    }

    public int getType() {
        return this.type;
    }

    public boolean[] getWeekRepeat() {
        boolean[] zArr = new boolean[7];
        for (int i = 0; i < 7; i++) {
            zArr[i] = ((this.repeat >> i) & 4) == 4;
        }
        return zArr;
    }

    public int getYear() {
        return this.year;
    }

    public boolean isEnable() {
        return (this.repeat & 1) == 1;
    }

    public boolean isMonthRepeat() {
        return (this.repeat & 512) == 512;
    }

    public boolean isYearRepeat() {
        return (this.repeat & 1024) == 1024;
    }

    public void setContent(@Nullable String str) {
        this.content = str;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setEnable(boolean z) {
        int i = this.repeat & (-2);
        this.repeat = i;
        if (z) {
            this.repeat = i | 1;
        }
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

    public void setMonth(int i) {
        this.month = i;
    }

    public void setMonthRepeat(boolean z) {
        int i = this.repeat & (-513);
        this.repeat = i;
        if (z) {
            this.repeat = i | 512;
        }
        updateRepeatFlag();
    }

    public void setRepeat(int i) {
        this.repeat = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.repeat &= -509;
        if (zArr != null) {
            for (int i = 0; i < Math.min(zArr.length, 7); i++) {
                if (zArr[i]) {
                    this.repeat |= 4 << i;
                }
            }
        }
        updateRepeatFlag();
    }

    public void setYear(int i) {
        this.year = i;
    }

    public void setYearRepeat(boolean z) {
        int i = this.repeat & (-1025);
        this.repeat = i;
        if (z) {
            this.repeat = i | 1024;
        }
        updateRepeatFlag();
    }
}
