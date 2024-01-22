package com.htsmart.wristband2.packet.a;

import com.htsmart.wristband2.bean.data.TemperatureData;
import com.htsmart.wristband2.packet.SyncDataParser;
import com.htsmart.wristband2.utils.BytesUtil;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class g extends b<TemperatureData> {

    /* renamed from: a  reason: collision with root package name */
    public final GregorianCalendar f12032a = new GregorianCalendar();

    @Override // com.htsmart.wristband2.packet.a.b
    public int a() {
        return 8;
    }

    @Override // com.htsmart.wristband2.packet.a.b
    /* renamed from: b */
    public TemperatureData a(byte[] bArr) {
        float bytes2Short = BytesUtil.bytes2Short(bArr, 6, 2, true) / 100.0f;
        if (bytes2Short <= 0.0f) {
            return null;
        }
        TemperatureData temperatureData = new TemperatureData();
        temperatureData.setTimeStamp(SyncDataParser.parserTime4Bytes(bArr, 0, this.f12032a));
        temperatureData.setWrist(BytesUtil.bytes2Short(bArr, 4, 2, true) / 100.0f);
        temperatureData.setBody(bytes2Short);
        return temperatureData;
    }
}
