package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzdo;
import com.google.android.gms.internal.auth.zzdp;
/* loaded from: classes6.dex */
public abstract class zzdo<MessageType extends zzdp<MessageType, BuilderType>, BuilderType extends zzdo<MessageType, BuilderType>> implements zzfv {
    @Override // 
    /* renamed from: zza */
    public abstract zzdo clone();

    public abstract zzdo zzb(zzdp zzdpVar);

    @Override // com.google.android.gms.internal.auth.zzfv
    public final /* bridge */ /* synthetic */ zzfv zzc(zzfw zzfwVar) {
        if (zzh().getClass().isInstance(zzfwVar)) {
            return zzb((zzdp) zzfwVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
