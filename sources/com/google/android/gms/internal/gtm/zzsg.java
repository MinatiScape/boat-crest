package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzsg;
import com.google.android.gms.internal.gtm.zzsh;
/* loaded from: classes8.dex */
public abstract class zzsg<MessageType extends zzsh<MessageType, BuilderType>, BuilderType extends zzsg<MessageType, BuilderType>> implements zzwj {
    @Override // 
    /* renamed from: zzv */
    public abstract BuilderType clone();

    public abstract BuilderType zzw(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzwj
    public final /* bridge */ /* synthetic */ zzwj zzx(zzwk zzwkVar) {
        if (zzar().getClass().isInstance(zzwkVar)) {
            return zzw((zzsh) zzwkVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
