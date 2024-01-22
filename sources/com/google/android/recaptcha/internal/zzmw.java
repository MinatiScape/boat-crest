package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public enum zzmw implements zzhh {
    JS_CODE_UNSPECIFIED(0),
    JS_CODE_SUCCESS(1),
    JS_NETWORK_ERROR(2),
    JS_INTERNAL_ERROR(3),
    JS_INVALID_SITE_KEY(4),
    JS_INVALID_SITE_KEY_TYPE(5),
    JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED(6),
    JS_INVALID_ACTION(7),
    JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED(8),
    JS_PROGRAM_ERROR(9),
    UNRECOGNIZED(-1);
    
    private static final zzhi zzl = new zzhi() { // from class: com.google.android.recaptcha.internal.zzmv
    };
    private final int zzn;

    zzmw(int i) {
        this.zzn = i;
    }

    public static zzmw zzb(int i) {
        switch (i) {
            case 0:
                return JS_CODE_UNSPECIFIED;
            case 1:
                return JS_CODE_SUCCESS;
            case 2:
                return JS_NETWORK_ERROR;
            case 3:
                return JS_INTERNAL_ERROR;
            case 4:
                return JS_INVALID_SITE_KEY;
            case 5:
                return JS_INVALID_SITE_KEY_TYPE;
            case 6:
                return JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED;
            case 7:
                return JS_INVALID_ACTION;
            case 8:
                return JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED;
            case 9:
                return JS_PROGRAM_ERROR;
            default:
                return null;
        }
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(zza());
    }

    @Override // com.google.android.recaptcha.internal.zzhh
    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzn;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
