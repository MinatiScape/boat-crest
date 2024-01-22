package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzfo;
import com.google.android.gms.internal.fitness.zzfr;
/* loaded from: classes8.dex */
public abstract class zzfr<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zzfr<MessageType, BuilderType>> implements zzij {
    public abstract BuilderType zza(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.fitness.zzij
    public final /* synthetic */ zzij zza(zzik zzikVar) {
        if (zzbu().getClass().isInstance(zzikVar)) {
            return zza((zzfr<MessageType, BuilderType>) ((zzfo) zzikVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // 
    /* renamed from: zzap */
    public abstract BuilderType clone();
}
