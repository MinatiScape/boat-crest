package com.google.android.gms.internal.mlkit_code_scanner;

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
public final class zzog implements zznr {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Provider f9151a;
    public final Provider b;
    public final zznt c;

    public zzog(Context context, zznt zzntVar) {
        this.c = zzntVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.f9151a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzod
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzof
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzoe
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzoc
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    public static Event a(zznt zzntVar, zznq zznqVar) {
        return Event.ofTelemetry(zznqVar.zzd(zzntVar.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznr
    public final void zza(zznq zznqVar) {
        if (this.c.zza() != 0) {
            ((Transport) this.b.get()).send(a(this.c, zznqVar));
            return;
        }
        Provider provider = this.f9151a;
        if (provider != null) {
            ((Transport) provider.get()).send(a(this.c, zznqVar));
        }
    }
}
