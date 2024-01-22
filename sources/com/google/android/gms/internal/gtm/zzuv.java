package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuu;
import com.google.android.gms.internal.gtm.zzuv;
/* loaded from: classes8.dex */
public abstract class zzuv<MessageType extends zzuv<MessageType, BuilderType>, BuilderType extends zzuu<MessageType, BuilderType>> extends zzuz<MessageType, BuilderType> implements zzwl {
    public zzuo<zzuw> zza = zzuo.zzd();

    public final zzuo<zzuw> zzU() {
        if (this.zza.zzj()) {
            this.zza = this.zza.clone();
        }
        return this.zza;
    }

    public final <Type> Type zzV(zzuh<MessageType, Type> zzuhVar) {
        zzux<MessageType, ?> zzuxVar = (zzux) zzuhVar;
        zzc(zzuxVar);
        Type type = (Type) this.zza.zze(zzuxVar.zzd);
        if (type == null) {
            return zzuxVar.zzb;
        }
        if (zzuxVar.zzd.zzc.zza() == zzyf.ENUM) {
            zzvc<?> zzvcVar = zzuxVar.zzd.zza;
            return (Type) zzyl.zzc(((Integer) type).intValue());
        }
        return type;
    }

    public final <Type> boolean zzW(zzuh<MessageType, Type> zzuhVar) {
        zzux<MessageType, ?> zzuxVar = (zzux) zzuhVar;
        zzc(zzuxVar);
        return this.zza.zza.get(zzuxVar.zzd) != null;
    }

    public final void zzc(zzux<MessageType, ?> zzuxVar) {
        if (zzuxVar.zza != ((zzuz) zzb(6, null, null))) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }
}
