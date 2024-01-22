package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes10.dex */
final class zzbu extends Lambda implements Function2 {
    public final /* synthetic */ zzbh zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ int zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbu(zzbh zzbhVar, String str, int i) {
        super(2);
        this.zza = zzbhVar;
        this.zzb = str;
        this.zzc = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        this.zza.zzd().zzb(this.zzb, (String) obj2);
        int i = this.zzc;
        if (i != -1) {
            this.zza.zze().zzf(i, objArr);
        }
        return Unit.INSTANCE;
    }
}
