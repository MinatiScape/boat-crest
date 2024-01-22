package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes11.dex */
public abstract class AbstractExpandedDecoder {

    /* renamed from: a  reason: collision with root package name */
    public final BitArray f11837a;
    public final r b;

    public AbstractExpandedDecoder(BitArray bitArray) {
        this.f11837a = bitArray;
        this.b = new r(bitArray);
    }

    public static AbstractExpandedDecoder createDecoder(BitArray bitArray) {
        if (bitArray.get(1)) {
            return new g(bitArray);
        }
        if (!bitArray.get(2)) {
            return new j(bitArray);
        }
        int g = r.g(bitArray, 1, 4);
        if (g != 4) {
            if (g != 5) {
                int g2 = r.g(bitArray, 1, 5);
                if (g2 != 12) {
                    if (g2 != 13) {
                        switch (r.g(bitArray, 1, 7)) {
                            case 56:
                                return new e(bitArray, "310", BleConst.GetDeviceVersion);
                            case 57:
                                return new e(bitArray, "320", BleConst.GetDeviceVersion);
                            case 58:
                                return new e(bitArray, "310", BleConst.CMD_MCUReset);
                            case 59:
                                return new e(bitArray, "320", BleConst.CMD_MCUReset);
                            case 60:
                                return new e(bitArray, "310", BleConst.GetDeviceName);
                            case 61:
                                return new e(bitArray, "320", BleConst.GetDeviceName);
                            case 62:
                                return new e(bitArray, "310", BleConst.GetAutomaticHRMonitoring);
                            case 63:
                                return new e(bitArray, "320", BleConst.GetAutomaticHRMonitoring);
                            default:
                                throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(bitArray)));
                        }
                    }
                    return new d(bitArray);
                }
                return new c(bitArray);
            }
            return new b(bitArray);
        }
        return new a(bitArray);
    }

    public final r getGeneralDecoder() {
        return this.b;
    }

    public final BitArray getInformation() {
        return this.f11837a;
    }

    public abstract String parseInformation() throws NotFoundException, FormatException;
}
