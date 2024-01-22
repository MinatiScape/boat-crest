package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuu;
import com.google.android.gms.internal.gtm.zzuv;
/* loaded from: classes8.dex */
public class zzuu<MessageType extends zzuv<MessageType, BuilderType>, BuilderType extends zzuu<MessageType, BuilderType>> extends zzut<MessageType, BuilderType> implements zzwl {
    public zzuu(MessageType messagetype) {
        super(messagetype);
    }

    @Override // com.google.android.gms.internal.gtm.zzut
    public final void zzF() {
        super.zzF();
        zzuv zzuvVar = (zzuv) this.zza;
        zzuvVar.zza = zzuvVar.zza.clone();
    }

    @Override // com.google.android.gms.internal.gtm.zzut, com.google.android.gms.internal.gtm.zzwj
    /* renamed from: zzH */
    public final MessageType zzD() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        ((zzuv) this.zza).zza.zzg();
        return (MessageType) super.zzD();
    }
}
