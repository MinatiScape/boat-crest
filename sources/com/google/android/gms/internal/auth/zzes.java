package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzes;
import com.google.android.gms.internal.auth.zzeu;
/* loaded from: classes6.dex */
public class zzes<MessageType extends zzeu<MessageType, BuilderType>, BuilderType extends zzes<MessageType, BuilderType>> extends zzdo<MessageType, BuilderType> {
    public final zzeu h;
    public zzeu zza;
    public boolean zzb = false;

    public zzes(MessageType messagetype) {
        this.h = messagetype;
        this.zza = (zzeu) messagetype.zzi(4, null, null);
    }

    public static final void a(zzeu zzeuVar, zzeu zzeuVar2) {
        w1.a().b(zzeuVar.getClass()).g(zzeuVar, zzeuVar2);
    }

    @Override // com.google.android.gms.internal.auth.zzdo
    public final /* synthetic */ zzdo zzb(zzdp zzdpVar) {
        zze((zzeu) zzdpVar);
        return this;
    }

    @Override // com.google.android.gms.internal.auth.zzdo
    /* renamed from: zzd */
    public final zzes zza() {
        zzes zzesVar = (zzes) this.h.zzi(5, null, null);
        zzesVar.zze(zzg());
        return zzesVar;
    }

    public final zzes zze(zzeu zzeuVar) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        a(this.zza, zzeuVar);
        return this;
    }

    @Override // com.google.android.gms.internal.auth.zzfv
    /* renamed from: zzf */
    public MessageType zzg() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        zzeu zzeuVar = this.zza;
        w1.a().b(zzeuVar.getClass()).zze(zzeuVar);
        this.zzb = true;
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.auth.zzfx
    public final /* synthetic */ zzfw zzh() {
        return this.h;
    }

    public void zzi() {
        zzeu zzeuVar = (zzeu) this.zza.zzi(4, null, null);
        a(zzeuVar, this.zza);
        this.zza = zzeuVar;
    }
}
