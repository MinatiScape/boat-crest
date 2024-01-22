package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public abstract class zzdy implements Iterable {
    private final zzdn zza = zzdn.zza();

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        for (Object obj : this) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(obj);
            z = false;
        }
        sb.append(']');
        return sb.toString();
    }
}
