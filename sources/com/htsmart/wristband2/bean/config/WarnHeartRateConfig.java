package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class WarnHeartRateConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 4;

    public WarnHeartRateConfig() {
    }

    public WarnHeartRateConfig(byte[] bArr) {
        super(bArr);
    }

    public int getDynamicValue() {
        return this.values[3] & 255;
    }

    public int getStaticValue() {
        return this.values[1] & 255;
    }

    public boolean isDynamicEnable() {
        return this.values[2] == 1;
    }

    public boolean isStaticEnable() {
        return this.values[0] == 1;
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 4;
    }

    public void setDynamicEnable(boolean z) {
        this.values[2] = z ? (byte) 1 : (byte) 0;
    }

    public void setDynamicValue(int i) {
        this.values[3] = (byte) i;
    }

    public void setStaticEnable(boolean z) {
        this.values[0] = z ? (byte) 1 : (byte) 0;
    }

    public void setStaticValue(int i) {
        this.values[1] = (byte) i;
    }
}
