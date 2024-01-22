package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class TurnWristLightingConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 9;

    public TurnWristLightingConfig() {
    }

    public TurnWristLightingConfig(byte[] bArr) {
        super(bArr);
    }

    public int getEnd() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[4] & 255) | ((bArr[3] & 255) << 8));
    }

    public int getStart() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[2] & 255) | ((bArr[1] & 255) << 8));
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
        bArr[3] = (byte) ((adjustTime >> 8) & 255);
        bArr[4] = (byte) (adjustTime & 255);
    }

    public void setStart(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[1] = (byte) ((adjustTime >> 8) & 255);
        bArr[2] = (byte) (adjustTime & 255);
    }
}
