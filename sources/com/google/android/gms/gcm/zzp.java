package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.os.Trace;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.iid.zzai;
import com.google.android.gms.iid.zzaj;
import java.io.Closeable;
/* loaded from: classes6.dex */
public final class zzp implements Closeable {
    public static final zzaj<Boolean> i = zzai.zzy().zzd("nts.enable_tracing", true);
    public final boolean h;

    @TargetApi(18)
    public zzp(String str) {
        boolean z = PlatformVersion.isAtLeastJellyBeanMR2() && i.get().booleanValue();
        this.h = z;
        if (z) {
            Trace.beginSection(str.length() > 127 ? str.substring(0, 127) : str);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @TargetApi(18)
    public final void close() {
        if (this.h) {
            Trace.endSection();
        }
    }
}
