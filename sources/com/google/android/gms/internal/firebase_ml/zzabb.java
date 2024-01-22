package com.google.android.gms.internal.firebase_ml;

import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzabb implements zzxc {
    HANG_DETECTION_DEFAULT(0),
    HANG_DETECTION_NONE(1),
    HANG_DETECTION_LOG_ONLY(2),
    HANG_DETECTION_CRASH_PROCESS(3),
    HANG_DETECTION_ABANDON_THREAD(4);
    
    private static final zzxf<zzabb> zzac = new zzxf<zzabb>() { // from class: com.google.android.gms.internal.firebase_ml.w
    };
    private final int value;

    zzabb(int i) {
        this.value = i;
    }

    public static zzabb zzei(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return null;
                        }
                        return HANG_DETECTION_ABANDON_THREAD;
                    }
                    return HANG_DETECTION_CRASH_PROCESS;
                }
                return HANG_DETECTION_LOG_ONLY;
            }
            return HANG_DETECTION_NONE;
        }
        return HANG_DETECTION_DEFAULT;
    }

    public static zzxe zzf() {
        return v.f8740a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzabb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
