package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzabq implements zzxc {
    UNKNOWN_EVENT_TYPE(0),
    VALIDATION_TEST(1),
    CONTINUOUS_FEEDBACK(2);
    
    private static final zzxf<zzabq> zzac = new zzxf<zzabq>() { // from class: com.google.android.gms.internal.firebase_ml.g0
    };
    private final int value;

    zzabq(int i) {
        this.value = i;
    }

    public static zzabq zzen(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return CONTINUOUS_FEEDBACK;
            }
            return VALIDATION_TEST;
        }
        return UNKNOWN_EVENT_TYPE;
    }

    public static zzxe zzf() {
        return h0.f8683a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzabq.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
