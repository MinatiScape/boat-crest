package com.htsmart.wristband2.packet.a;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.bean.data.RespiratoryRateData;
import com.htsmart.wristband2.packet.SyncDataParser;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class f extends b<RespiratoryRateData> {

    /* renamed from: a  reason: collision with root package name */
    public final GregorianCalendar f12031a = new GregorianCalendar();

    @Override // com.htsmart.wristband2.packet.a.b
    public int a() {
        return 5;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    @Nullable
    /* renamed from: b */
    public RespiratoryRateData a(byte[] bArr) {
        int i = bArr[4] & 255;
        if (i <= 0) {
            return null;
        }
        RespiratoryRateData respiratoryRateData = new RespiratoryRateData();
        respiratoryRateData.setTimeStamp(SyncDataParser.parserTime4Bytes(bArr, 0, this.f12031a));
        respiratoryRateData.setRate(i);
        return respiratoryRateData;
    }
}
