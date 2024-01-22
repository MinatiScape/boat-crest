package com.google.android.libraries.places.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
public class zzso extends IOException {
    private zzto zza;

    public zzso(String str) {
        super(str);
        this.zza = null;
    }

    public static zzsr zza() {
        return new zzsr("Protocol message tag had invalid wire type.");
    }
}
