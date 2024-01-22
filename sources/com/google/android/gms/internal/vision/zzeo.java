package com.google.android.gms.internal.vision;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzeo implements zzgw {
    UNRECOGNIZED(0),
    CODE_128(1),
    CODE_39(2),
    CODE_93(3),
    CODABAR(4),
    DATA_MATRIX(5),
    EAN_13(6),
    EAN_8(7),
    ITF(8),
    QR_CODE(9),
    UPC_A(10),
    UPC_E(11),
    PDF417(12),
    AZTEC(13),
    DATABAR(14),
    TEZ_CODE(16);
    
    private static final zzgv<zzeo> zzhc = new zzgv<zzeo>() { // from class: com.google.android.gms.internal.vision.z0
        @Override // com.google.android.gms.internal.vision.zzgv
        public final /* synthetic */ zzeo zzh(int i) {
            return zzeo.zzac(i);
        }
    };
    private final int value;

    zzeo(int i) {
        this.value = i;
    }

    public static zzeo zzac(int i) {
        switch (i) {
            case 0:
                return UNRECOGNIZED;
            case 1:
                return CODE_128;
            case 2:
                return CODE_39;
            case 3:
                return CODE_93;
            case 4:
                return CODABAR;
            case 5:
                return DATA_MATRIX;
            case 6:
                return EAN_13;
            case 7:
                return EAN_8;
            case 8:
                return ITF;
            case 9:
                return QR_CODE;
            case 10:
                return UPC_A;
            case 11:
                return UPC_E;
            case 12:
                return PDF417;
            case 13:
                return AZTEC;
            case 14:
                return DATABAR;
            case 15:
            default:
                return null;
            case 16:
                return TEZ_CODE;
        }
    }

    public static zzgy zzah() {
        return y0.f10006a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzeo.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.vision.zzgw
    public final int zzag() {
        return this.value;
    }
}
