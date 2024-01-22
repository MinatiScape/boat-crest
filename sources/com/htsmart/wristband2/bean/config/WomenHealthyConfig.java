package com.htsmart.wristband2.bean.config;

import com.crrepa.c.a;
import java.util.Date;
/* loaded from: classes11.dex */
public class WomenHealthyConfig extends AbstractConfig {
    public static final int MODE_MENSTRUATION = 1;
    public static final int MODE_NONE = 0;
    public static final int MODE_PREGNANCY = 3;
    public static final int MODE_PREGNANCY_PREPARE = 2;
    public static final int PACKET_LENGTH = 10;
    public static final int PREGNANCY_REMIND_TYPE_DUE_DAYS = 1;
    public static final int PREGNANCY_REMIND_TYPE_PREGNANCY_DAYS = 0;

    public WomenHealthyConfig() {
    }

    public WomenHealthyConfig(byte[] bArr) {
        super(bArr);
    }

    public int getMenstruationAdvance() {
        return this.values[1] & 255;
    }

    public int getMenstruationCycle() {
        return this.values[5] & 255;
    }

    public int getMenstruationDuration() {
        return this.values[4] & 255;
    }

    public int getMenstruationEndDay() {
        return this.values[8] & 255;
    }

    public Date getMenstruationLatest() {
        byte[] bArr = this.values;
        Date date = new Date();
        date.setYear((((bArr[6] & a.l1) >> 1) + 2000) - 1900);
        date.setMonth((((bArr[6] & 1) << 3) | ((bArr[7] >> 5) & 7)) - 1);
        date.setDate(bArr[7] & 31);
        return date;
    }

    public int getMode() {
        return this.values[0] & 255;
    }

    public int getPregnancyRemindType() {
        return this.values[9] & 255;
    }

    public int getRemindTime() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[3] & 255) | ((bArr[2] & 255) << 8));
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 10;
    }

    public void setMenstruationAdvance(int i) {
        this.values[1] = (byte) i;
    }

    public void setMenstruationCycle(int i) {
        this.values[5] = (byte) i;
    }

    public void setMenstruationDuration(int i) {
        this.values[4] = (byte) i;
    }

    public void setMenstruationEndDay(int i) {
        this.values[8] = (byte) i;
    }

    public void setMenstruationLatest(Date date) {
        int month = date.getMonth() + 1;
        int date2 = date.getDate();
        byte[] bArr = this.values;
        bArr[6] = (byte) (((((date.getYear() + 1900) - 2000) << 1) & 126) | ((month >> 3) & 1));
        bArr[7] = (byte) ((date2 & 31) | ((month & 7) << 5));
    }

    public void setMode(int i) {
        this.values[0] = (byte) i;
    }

    public void setPregnancyRemindType(int i) {
        this.values[9] = (byte) i;
    }

    public void setRemindTime(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[2] = (byte) ((adjustTime >> 8) & 255);
        bArr[3] = (byte) (adjustTime & 255);
    }
}
