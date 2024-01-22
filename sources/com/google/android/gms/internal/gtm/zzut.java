package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzut;
import com.google.android.gms.internal.gtm.zzuz;
/* loaded from: classes8.dex */
public class zzut<MessageType extends zzuz<MessageType, BuilderType>, BuilderType extends zzut<MessageType, BuilderType>> extends zzsg<MessageType, BuilderType> {
    public MessageType zza;
    public boolean zzb = false;
    public final MessageType zzc;

    public zzut(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (MessageType) messagetype.zzb(4, null, null);
    }

    public static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzwt.zza().zzb(messagetype.getClass()).zzg(messagetype, messagetype2);
    }

    @Override // com.google.android.gms.internal.gtm.zzwj
    /* renamed from: zzA */
    public final MessageType zzC() {
        MessageType zzD = zzD();
        if (zzD.zzas()) {
            return zzD;
        }
        throw new zzxn(zzD);
    }

    @Override // com.google.android.gms.internal.gtm.zzwj
    /* renamed from: zzB */
    public MessageType zzD() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzwt.zza().zzb(messagetype.getClass()).zzf(messagetype);
        this.zzb = true;
        return this.zza;
    }

    public void zzF() {
        MessageType messagetype = (MessageType) this.zza.zzb(4, null, null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }

    @Override // com.google.android.gms.internal.gtm.zzwl
    public final /* bridge */ /* synthetic */ zzwk zzar() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzwl
    public final boolean zzas() {
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzsg
    public final /* bridge */ /* synthetic */ zzsg zzw(zzsh zzshVar) {
        zzz((zzuz) zzshVar);
        return this;
    }

    @Override // com.google.android.gms.internal.gtm.zzsg
    /* renamed from: zzy */
    public final BuilderType zzv() {
        BuilderType buildertype = (BuilderType) this.zzc.zzb(5, null, null);
        buildertype.zzz(zzD());
        return buildertype;
    }

    public final BuilderType zzz(MessageType messagetype) {
        if (this.zzb) {
            zzF();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }
}
