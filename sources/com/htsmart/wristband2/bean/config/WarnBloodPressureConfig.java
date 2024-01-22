package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class WarnBloodPressureConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 5;

    public WarnBloodPressureConfig() {
    }

    public WarnBloodPressureConfig(byte[] bArr) {
        super(bArr);
    }

    public int getDbpLowerLimit() {
        return this.values[4] & 255;
    }

    public int getDbpUpperLimit() {
        return this.values[3] & 255;
    }

    public int getSbpLowerLimit() {
        return this.values[2] & 255;
    }

    public int getSbpUpperLimit() {
        return this.values[1] & 255;
    }

    public boolean isEnable() {
        return this.values[0] == 1;
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 5;
    }

    public void setDbpLowerLimit(int i) {
        this.values[4] = (byte) i;
    }

    public void setDbpUpperLimit(int i) {
        this.values[3] = (byte) i;
    }

    public void setEnable(boolean z) {
        this.values[0] = z ? (byte) 1 : (byte) 0;
    }

    public void setSbpLowerLimit(int i) {
        this.values[2] = (byte) i;
    }

    public void setSbpUpperLimit(int i) {
        this.values[1] = (byte) i;
    }
}
