package com.google.android.gms.internal.mlkit_vision_barcode;

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
/* loaded from: classes9.dex */
public final class zzuf implements zztn {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Provider f9579a;
    public final Provider b;
    public final zztp c;

    public zzuf(Context context, zztp zztpVar) {
        this.c = zztpVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.f9579a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzuc
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzue
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzud
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzub
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    public static Event a(zztp zztpVar, zztm zztmVar) {
        int zza = zztpVar.zza();
        if (zztmVar.zza() != 0) {
            return Event.ofData(zztmVar.zze(zza, false));
        }
        return Event.ofTelemetry(zztmVar.zze(zza, false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztn
    public final void zza(zztm zztmVar) {
        if (this.c.zza() != 0) {
            ((Transport) this.b.get()).send(a(this.c, zztmVar));
            return;
        }
        Provider provider = this.f9579a;
        if (provider != null) {
            ((Transport) provider.get()).send(a(this.c, zztmVar));
        }
    }
}
