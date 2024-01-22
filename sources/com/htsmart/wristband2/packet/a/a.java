package com.htsmart.wristband2.packet.a;

import com.htsmart.wristband2.bean.data.BloodPressureMeasureData;
import com.htsmart.wristband2.packet.SyncDataParser;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class a extends b<BloodPressureMeasureData> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f12027a;
    public final GregorianCalendar b = new GregorianCalendar();

    public a(boolean z) {
        this.f12027a = z;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    public int a() {
        return this.f12027a ? 7 : 6;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    /* renamed from: b */
    public BloodPressureMeasureData a(byte[] bArr) {
        int i = bArr[4] & 255;
        int i2 = bArr[5] & 255;
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        BloodPressureMeasureData bloodPressureMeasureData = new BloodPressureMeasureData();
        bloodPressureMeasureData.setTimeStamp(SyncDataParser.parserTime4Bytes(bArr, 0, this.b));
        bloodPressureMeasureData.setSbp(i);
        bloodPressureMeasureData.setDbp(i2);
        if (this.f12027a) {
            bloodPressureMeasureData.setHeartRate(bArr[6] & 255);
        }
        return bloodPressureMeasureData;
    }
}
