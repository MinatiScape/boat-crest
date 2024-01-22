package com.htsmart.wristband2.packet.a;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.bean.data.HeartRateData;
import com.htsmart.wristband2.packet.SyncDataParser;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class c extends b<HeartRateData> {

    /* renamed from: a  reason: collision with root package name */
    public final GregorianCalendar f12028a = new GregorianCalendar();

    @Override // com.htsmart.wristband2.packet.a.b
    public int a() {
        return 5;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    @Nullable
    /* renamed from: b */
    public HeartRateData a(byte[] bArr) {
        int i = bArr[4] & 255;
        if (i <= 0) {
            return null;
        }
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.setTimeStamp(SyncDataParser.parserTime4Bytes(bArr, 0, this.f12028a));
        heartRateData.setHeartRate(i);
        return heartRateData;
    }
}
