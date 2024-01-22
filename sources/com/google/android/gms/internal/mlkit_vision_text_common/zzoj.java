package com.google.android.gms.internal.mlkit_vision_text_common;

import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes6.dex */
public final class zzoj implements zznv {

    /* renamed from: a  reason: collision with root package name */
    public final zzku f9953a;
    public zzmw b = new zzmw();
    public final int c;

    public zzoj(zzku zzkuVar, int i) {
        this.f9953a = zzkuVar;
        zzos.zza();
        this.c = i;
    }

    public static zznv zzf(zzku zzkuVar) {
        return new zzoj(zzkuVar, 0);
    }

    public static zznv zzg(zzku zzkuVar, int i) {
        return new zzoj(zzkuVar, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznv
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznv
    public final zznv zzb(zzkt zzktVar) {
        this.f9953a.zzf(zzktVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznv
    public final zznv zzc(zzmw zzmwVar) {
        this.b = zzmwVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznv
    public final String zzd() {
        zzmy zzf = this.f9953a.zzj().zzf();
        return (zzf == null || zzab.zzb(zzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznv
    public final byte[] zze(int i, boolean z) {
        this.b.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.b.zze(Boolean.FALSE);
        this.f9953a.zzi(this.b.zzm());
        try {
            zzos.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzjd.zza).ignoreNullValues(true).build().encode(this.f9953a.zzj()).getBytes(JsonRequest.PROTOCOL_CHARSET);
            }
            zzkw zzj = this.f9953a.zzj();
            zzdd zzddVar = new zzdd();
            zzjd.zza.configure(zzddVar);
            return zzddVar.zza().zza(zzj);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
