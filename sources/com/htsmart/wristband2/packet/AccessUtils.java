package com.htsmart.wristband2.packet;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.packet.SyncDataParser;
import java.util.GregorianCalendar;
/* loaded from: classes11.dex */
public class AccessUtils {
    public static SyncDataParser.DataHeader parserHeader(byte[] bArr, byte b, @Nullable GregorianCalendar gregorianCalendar) {
        return SyncDataParser.b(bArr, b, gregorianCalendar);
    }
}
