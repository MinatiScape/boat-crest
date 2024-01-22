package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class NotDisturbConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 6;

    public NotDisturbConfig() {
    }

    public NotDisturbConfig(byte[] bArr) {
        super(bArr);
    }

    public int getEnd() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[5] & 255) | ((bArr[4] & 255) << 8));
    }

    public int getStart() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[3] & 255) | ((bArr[2] & 255) << 8));
    }

    public boolean isEnableAllDay() {
        return this.values[0] == 1;
    }

    public boolean isEnablePeriodTime() {
        return this.values[1] == 1;
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 6;
    }

    public void setEnableAllDay(boolean z) {
        this.values[0] = z ? (byte) 1 : (byte) 0;
    }

    public void setEnablePeriodTime(boolean z) {
        this.values[1] = z ? (byte) 1 : (byte) 0;
    }

    public void setEnd(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[4] = (byte) ((adjustTime >> 8) & 255);
        bArr[5] = (byte) (adjustTime & 255);
    }

    public void setStart(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[2] = (byte) ((adjustTime >> 8) & 255);
        bArr[3] = (byte) (adjustTime & 255);
    }
}
