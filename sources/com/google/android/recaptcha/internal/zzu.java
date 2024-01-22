package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.sync.Mutex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzu extends ContinuationImpl {
    public Object zza;
    public Object zzb;
    public Object zzc;
    public long zzd;
    public /* synthetic */ Object zze;
    public final /* synthetic */ zzv zzf;
    public int zzg;
    public zzq zzh;
    public zzdi zzi;
    public Mutex zzj;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzu(zzv zzvVar, Continuation continuation) {
        super(continuation);
        this.zzf = zzvVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zze = obj;
        this.zzg |= Integer.MIN_VALUE;
        return this.zzf.zza(null, null, 0L, null, null, this);
    }
}
