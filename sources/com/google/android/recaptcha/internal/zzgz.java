package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzgz;
import com.google.android.recaptcha.internal.zzhf;
/* loaded from: classes10.dex */
public class zzgz<MessageType extends zzhf<MessageType, BuilderType>, BuilderType extends zzgz<MessageType, BuilderType>> extends zzeq<MessageType, BuilderType> {
    public zzhf zza;
    private final zzhf zzb;

    public zzgz(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzF()) {
            this.zza = messagetype.zzs();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zzd(Object obj, Object obj2) {
        zziy.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.recaptcha.internal.zziq
    public final /* synthetic */ zzip zzX() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzeq
    public final /* synthetic */ zzeq zzb(zzer zzerVar) {
        zzg((zzhf) zzerVar);
        return this;
    }

    @Override // com.google.android.recaptcha.internal.zzeq
    /* renamed from: zzf */
    public final zzgz zza() {
        zzgz zzgzVar = (zzgz) this.zzb.zzh(5, null, null);
        zzgzVar.zza = zzk();
        return zzgzVar;
    }

    public final zzgz zzg(zzhf zzhfVar) {
        if (!this.zzb.equals(zzhfVar)) {
            if (!this.zza.zzF()) {
                zzn();
            }
            zzd(this.zza, zzhfVar);
        }
        return this;
    }

    @Override // com.google.android.recaptcha.internal.zzio
    /* renamed from: zzh */
    public final MessageType zzj() {
        MessageType zzk = zzk();
        if (zzk.zzo()) {
            return zzk;
        }
        throw new zzjv(zzk);
    }

    @Override // com.google.android.recaptcha.internal.zzio
    /* renamed from: zzi */
    public MessageType zzk() {
        if (this.zza.zzF()) {
            this.zza.zzA();
            return (MessageType) this.zza;
        }
        return (MessageType) this.zza;
    }

    public final void zzm() {
        if (this.zza.zzF()) {
            return;
        }
        zzn();
    }

    public void zzn() {
        zzhf zzs = this.zzb.zzs();
        zzd(zzs, this.zza);
        this.zza = zzs;
    }

    @Override // com.google.android.recaptcha.internal.zziq
    public final boolean zzo() {
        return zzhf.zzE(this.zza, false);
    }
}
