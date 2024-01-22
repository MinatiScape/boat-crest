package com.htsmart.wristband2.bean.peripherals;
/* loaded from: classes11.dex */
public class PeripheralsData {

    /* renamed from: a  reason: collision with root package name */
    public final Peripherals f11983a;
    public float b;

    public PeripheralsData(Peripherals peripherals) {
        this.f11983a = peripherals;
    }

    public static PeripheralsData bloodGlucoseMeter(float f) {
        PeripheralsData peripheralsData = new PeripheralsData(Peripherals.BLOOD_GLUCOSE_METER);
        peripheralsData.b = f;
        return peripheralsData;
    }

    public byte[] toBytes() {
        Peripherals peripherals = this.f11983a;
        if (peripherals == Peripherals.BLOOD_GLUCOSE_METER) {
            int i = (int) (this.b * 10.0f);
            return new byte[]{peripherals.getType(), 2, (byte) ((i >> 8) & 255), (byte) (i & 255)};
        }
        throw new IllegalArgumentException();
    }
}
