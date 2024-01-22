package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes8.dex */
public final class zzbz extends zzce {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<Bundle> f8947a = new AtomicReference<>();
    public boolean b;

    public static final <T> T zze(Bundle bundle, Class<T> cls) {
        Object obj;
        if (bundle == null || (obj = bundle.get(RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME)) == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            Log.w("AM", String.format("Unexpected object type. Expected, Received: %s, %s", cls.getCanonicalName(), obj.getClass().getCanonicalName()), e);
            throw e;
        }
    }

    public final Bundle zzb(long j) {
        Bundle bundle;
        synchronized (this.f8947a) {
            if (!this.b) {
                try {
                    this.f8947a.wait(j);
                } catch (InterruptedException unused) {
                    return null;
                }
            }
            bundle = this.f8947a.get();
        }
        return bundle;
    }

    public final String zzc(long j) {
        return (String) zze(zzb(j), String.class);
    }

    @Override // com.google.android.gms.internal.measurement.zzcf
    public final void zzd(Bundle bundle) {
        synchronized (this.f8947a) {
            this.f8947a.set(bundle);
            this.b = true;
            this.f8947a.notify();
        }
    }
}
