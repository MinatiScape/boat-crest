package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
/* loaded from: classes8.dex */
public class zzdx<MessageType extends zzed<MessageType, BuilderType>, BuilderType extends zzdx<MessageType, BuilderType>> extends zzcj<MessageType, BuilderType> {
    public final zzed h;
    public zzed zza;

    public zzdx(MessageType messagetype) {
        this.h = messagetype;
        if (!messagetype.h()) {
            this.zza = messagetype.d();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    public static void a(Object obj, Object obj2) {
        d1.a().b(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp
    public final /* bridge */ /* synthetic */ zzfo zzab() {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp
    public final boolean zzac() {
        return zzed.zzW(this.zza, false);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj
    /* renamed from: zzf */
    public final zzdx zze() {
        zzdx zzdxVar = (zzdx) this.h.zzg(5, null, null);
        zzdxVar.zza = zzk();
        return zzdxVar;
    }

    public final zzdx zzg(zzed zzedVar) {
        if (!this.h.equals(zzedVar)) {
            if (!this.zza.h()) {
                zzn();
            }
            a(this.zza, zzedVar);
        }
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn
    /* renamed from: zzh */
    public final MessageType zzj() {
        MessageType zzk = zzk();
        if (zzk.zzac()) {
            return zzk;
        }
        throw new zzgx(zzk);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn
    /* renamed from: zzi */
    public MessageType zzk() {
        if (this.zza.h()) {
            this.zza.zzS();
            return (MessageType) this.zza;
        }
        return (MessageType) this.zza;
    }

    public final void zzm() {
        if (this.zza.h()) {
            return;
        }
        zzn();
    }

    public void zzn() {
        zzed d = this.h.d();
        a(d, this.zza);
        this.zza = d;
    }
}
