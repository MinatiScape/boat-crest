package com.google.android.recaptcha.internal;

import com.realsil.sdk.dfu.DfuException;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcy {
    @NotNull
    public static final zzcx zza = new zzcx(null);
    private int zzb;

    public zzcy(short s, short s2) {
        this.zzb = Math.abs((int) s);
    }

    public final short zza() {
        int i = ((this.zzb * 4391) + DfuException.ERROR_READ_REMOTE_MAC_ADDR) % 32779;
        this.zzb = i;
        return (short) (i % 255);
    }
}
