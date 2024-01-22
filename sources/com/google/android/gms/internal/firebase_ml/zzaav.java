package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzaav implements zzxc {
    DELEGATE_NONE(0),
    NNAPI(1),
    GPU(2),
    HEXAGON(3);
    
    private static final zzxf<zzaav> zzac = new zzxf<zzaav>() { // from class: com.google.android.gms.internal.firebase_ml.s
    };
    private final int value;

    zzaav(int i) {
        this.value = i;
    }

    public static zzaav zzeg(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return HEXAGON;
                }
                return GPU;
            }
            return NNAPI;
        }
        return DELEGATE_NONE;
    }

    public static zzxe zzf() {
        return r.f8722a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzaav.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
