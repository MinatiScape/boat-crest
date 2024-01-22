package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzabf implements zzxc {
    NNAPI_EXECUTION_PREFERENCE_UNDEFINED(0),
    NNAPI_EXECUTION_PREFERENCE_LOW_POWER(1),
    NNAPI_EXECUTION_PREFERENCE_FAST_SINGLE_ANSWER(2),
    NNAPI_EXECUTION_PREFERENCE_SUSTAINED_SPEED(3);
    
    private static final zzxf<zzabf> zzac = new zzxf<zzabf>() { // from class: com.google.android.gms.internal.firebase_ml.x
    };
    private final int value;

    zzabf(int i) {
        this.value = i;
    }

    public static zzabf zzej(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return NNAPI_EXECUTION_PREFERENCE_SUSTAINED_SPEED;
                }
                return NNAPI_EXECUTION_PREFERENCE_FAST_SINGLE_ANSWER;
            }
            return NNAPI_EXECUTION_PREFERENCE_LOW_POWER;
        }
        return NNAPI_EXECUTION_PREFERENCE_UNDEFINED;
    }

    public static zzxe zzf() {
        return y.f8751a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzabf.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
