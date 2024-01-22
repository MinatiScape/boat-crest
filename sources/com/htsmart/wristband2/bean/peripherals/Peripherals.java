package com.htsmart.wristband2.bean.peripherals;
/* loaded from: classes11.dex */
public enum Peripherals {
    BLOOD_GLUCOSE_METER((byte) 1);
    
    private final byte b;

    Peripherals(byte b) {
        this.b = b;
    }

    public byte getType() {
        return this.b;
    }
}
