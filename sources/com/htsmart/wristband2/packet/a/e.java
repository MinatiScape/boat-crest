package com.htsmart.wristband2.packet.a;

import com.htsmart.wristband2.bean.data.PressureData;
import com.htsmart.wristband2.packet.SyncDataParser;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class e extends b<PressureData> {

    /* renamed from: a  reason: collision with root package name */
    public final GregorianCalendar f12030a = new GregorianCalendar();

    @Override // com.htsmart.wristband2.packet.a.b
    public int a() {
        return 5;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    /* renamed from: b */
    public PressureData a(byte[] bArr) {
        int i = bArr[4] & 255;
        if (i <= 0) {
            return null;
        }
        PressureData pressureData = new PressureData();
        pressureData.setTimeStamp(SyncDataParser.parserTime4Bytes(bArr, 0, this.f12030a));
        pressureData.setPressure(i);
        return pressureData;
    }
}
