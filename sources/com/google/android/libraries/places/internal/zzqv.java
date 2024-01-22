package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzqv;
import com.google.android.libraries.places.internal.zzqw;
/* loaded from: classes10.dex */
public abstract class zzqv<MessageType extends zzqw<MessageType, BuilderType>, BuilderType extends zzqv<MessageType, BuilderType>> implements zztr {
    @Override // 
    /* renamed from: zza */
    public abstract BuilderType clone();

    public abstract BuilderType zza(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.libraries.places.internal.zztr
    public final /* synthetic */ zztr zza(zzto zztoVar) {
        if (zzh().getClass().isInstance(zztoVar)) {
            return zza((zzqv<MessageType, BuilderType>) ((zzqw) zztoVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
