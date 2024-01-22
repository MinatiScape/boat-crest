package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzeq;
import com.google.android.recaptcha.internal.zzer;
/* loaded from: classes10.dex */
public abstract class zzeq<MessageType extends zzer<MessageType, BuilderType>, BuilderType extends zzeq<MessageType, BuilderType>> implements zzio {
    @Override // 
    /* renamed from: zza */
    public abstract zzeq clone();

    public abstract zzeq zzb(zzer zzerVar);

    @Override // com.google.android.recaptcha.internal.zzio
    public final /* bridge */ /* synthetic */ zzio zzc(zzip zzipVar) {
        if (zzX().getClass().isInstance(zzipVar)) {
            return zzb((zzer) zzipVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
