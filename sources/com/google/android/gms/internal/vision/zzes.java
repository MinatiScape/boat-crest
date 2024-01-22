package com.google.android.gms.internal.vision;

import kotlin.text.Typography;
/* loaded from: classes10.dex */
public enum zzes implements zzgw {
    UNKNOWN_FORMAT(0),
    CONTACT_INFO(1),
    EMAIL(2),
    ISBN(3),
    PHONE(4),
    PRODUCT(5),
    SMS(6),
    TEXT(7),
    URL(8),
    WIFI(9),
    GEO(10),
    CALENDAR_EVENT(11),
    DRIVER_LICENSE(12),
    BOARDING_PASS(13);
    
    private static final zzgv<zzes> zzhc = new zzgv<zzes>() { // from class: com.google.android.gms.internal.vision.a1
        @Override // com.google.android.gms.internal.vision.zzgv
        public final /* synthetic */ zzes zzh(int i) {
            return zzes.zzad(i);
        }
    };
    private final int value;

    zzes(int i) {
        this.value = i;
    }

    public static zzes zzad(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_FORMAT;
            case 1:
                return CONTACT_INFO;
            case 2:
                return EMAIL;
            case 3:
                return ISBN;
            case 4:
                return PHONE;
            case 5:
                return PRODUCT;
            case 6:
                return SMS;
            case 7:
                return TEXT;
            case 8:
                return URL;
            case 9:
                return WIFI;
            case 10:
                return GEO;
            case 11:
                return CALENDAR_EVENT;
            case 12:
                return DRIVER_LICENSE;
            case 13:
                return BOARDING_PASS;
            default:
                return null;
        }
    }

    public static zzgy zzah() {
        return b1.f9964a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzes.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.vision.zzgw
    public final int zzag() {
        return this.value;
    }
}
