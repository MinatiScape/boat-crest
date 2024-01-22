package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzvk;
import com.google.android.gms.internal.firebase_ml.zzvl;
/* loaded from: classes7.dex */
public abstract class zzvk<MessageType extends zzvl<MessageType, BuilderType>, BuilderType extends zzvk<MessageType, BuilderType>> implements zzyn {
    public abstract BuilderType zza(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_ml.zzyn
    public final /* synthetic */ zzyn zza(zzyk zzykVar) {
        if (zzuv().getClass().isInstance(zzykVar)) {
            return zza((zzvk<MessageType, BuilderType>) ((zzvl) zzykVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // 
    /* renamed from: zztf */
    public abstract BuilderType clone();
}
