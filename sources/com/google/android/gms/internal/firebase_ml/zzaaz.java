package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzaaz implements zzxc {
    ANY_EXECUTION_PREFERENCE(0),
    LOW_LATENCY(1),
    LOW_POWER(2),
    FORCE_CPU(3);
    
    private final int value;
    private static final zzaaz zzcsw = ANY_EXECUTION_PREFERENCE;
    private static final zzxf<zzaaz> zzac = new zzxf<zzaaz>() { // from class: com.google.android.gms.internal.firebase_ml.t
    };

    zzaaz(int i) {
        this.value = i;
    }

    public static zzaaz zzeh(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return FORCE_CPU;
                }
                return LOW_POWER;
            }
            return LOW_LATENCY;
        }
        return ANY_EXECUTION_PREFERENCE;
    }

    public static zzxe zzf() {
        return u.f8735a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzaaz.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
