package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class DrinkWaterConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 9;

    public DrinkWaterConfig() {
    }

    public DrinkWaterConfig(byte[] bArr) {
        super(bArr);
    }

    public final int c(int i) {
        if (i < 30) {
            return 30;
        }
        return Math.min(i, 180);
    }

    public int getEnd() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[6] & 255) | ((bArr[5] & 255) << 8));
    }

    public int getInterval() {
        byte[] bArr = this.values;
        return c((bArr[2] & 255) | ((bArr[1] & 255) << 8));
    }

    public int getStart() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[4] & 255) | ((bArr[3] & 255) << 8));
    }

    public boolean isEnable() {
        return this.values[0] > 0;
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 9;
    }

    public void setEnable(boolean z) {
        this.values[0] = z ? (byte) 1 : (byte) 0;
    }

    public void setEnd(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[5] = (byte) ((adjustTime >> 8) & 255);
        bArr[6] = (byte) (adjustTime & 255);
    }

    public void setInterval(int i) {
        int c = c(i);
        byte[] bArr = this.values;
        bArr[1] = (byte) ((c >> 8) & 255);
        bArr[2] = (byte) (c & 255);
    }

    public void setStart(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[3] = (byte) ((adjustTime >> 8) & 255);
        bArr[4] = (byte) (adjustTime & 255);
    }
}
