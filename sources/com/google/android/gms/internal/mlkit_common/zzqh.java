package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;
/* loaded from: classes8.dex */
public final class zzqh implements zzpr {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Provider f9358a;
    public final Provider b;
    public final zzpt c;

    public zzqh(Context context, zzpt zzptVar) {
        this.c = zzptVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.f9358a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzqe
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_common.zzqg
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzqf
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_common.zzqd
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    public static Event a(zzpt zzptVar, zzpq zzpqVar) {
        return Event.ofTelemetry(zzpqVar.zze(zzptVar.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpr
    public final void zza(zzpq zzpqVar) {
        if (this.c.zza() != 0) {
            ((Transport) this.b.get()).send(a(this.c, zzpqVar));
            return;
        }
        Provider provider = this.f9358a;
        if (provider != null) {
            ((Transport) provider.get()).send(a(this.c, zzpqVar));
        }
    }
}
