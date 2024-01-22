package com.htsmart.wristband2.bean.config;
/* loaded from: classes11.dex */
public class BloodPressureConfig extends AbstractConfig {
    public static final int PACKET_LENGTH = 10;

    public BloodPressureConfig() {
    }

    public BloodPressureConfig(byte[] bArr) {
        super(bArr);
    }

    public int getDiastolicPressure() {
        return this.values[0] & 255;
    }

    public int getSystolicPressure() {
        return this.values[1] & 255;
    }

    public boolean isPrivateModel() {
        return this.values[2] == 1;
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 10;
    }

    public void setDiastolicPressure(int i) {
        this.values[0] = (byte) i;
    }

    public void setPrivateModel(boolean z) {
        this.values[2] = z ? (byte) 1 : (byte) 0;
    }

    public void setSystolicPressure(int i) {
        this.values[1] = (byte) i;
    }
}
