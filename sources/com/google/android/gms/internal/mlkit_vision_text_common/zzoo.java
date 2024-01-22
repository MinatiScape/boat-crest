package com.google.android.gms.internal.mlkit_vision_text_common;

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
/* loaded from: classes6.dex */
public final class zzoo implements zzof {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Provider f9954a;
    public final Provider b;
    public final zznx c;

    public zzoo(Context context, zznx zznxVar) {
        this.c = zznxVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.f9954a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzol
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzon
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzom
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzok
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    public static Event a(zznx zznxVar, zznv zznvVar) {
        int zza = zznxVar.zza();
        if (zznvVar.zza() != 0) {
            return Event.ofData(zznvVar.zze(zza, false));
        }
        return Event.ofTelemetry(zznvVar.zze(zza, false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzof
    public final void zza(zznv zznvVar) {
        if (this.c.zza() != 0) {
            ((Transport) this.b.get()).send(a(this.c, zznvVar));
            return;
        }
        Provider provider = this.f9954a;
        if (provider != null) {
            ((Transport) provider.get()).send(a(this.c, zznvVar));
        }
    }
}
