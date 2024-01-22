package com.google.android.gms.internal.mlkit_common;

import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes8.dex */
public final class zzqc implements zzpq {

    /* renamed from: a  reason: collision with root package name */
    public final zzlo f9357a;
    public zzol b = new zzol();

    public zzqc(zzlo zzloVar, int i) {
        this.f9357a = zzloVar;
        zzqn.zza();
    }

    public static zzpq zzf(zzlo zzloVar) {
        return new zzqc(zzloVar, 0);
    }

    public static zzpq zzg() {
        return new zzqc(new zzlo(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpq
    public final zzpq zza(zzln zzlnVar) {
        this.f9357a.zzf(zzlnVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpq
    public final zzpq zzb(zzlu zzluVar) {
        this.f9357a.zzi(zzluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpq
    public final zzpq zzc(zzol zzolVar) {
        this.b = zzolVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpq
    public final String zzd() {
        zzon zzf = this.f9357a.zzk().zzf();
        return (zzf == null || zzag.zzc(zzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzpq
    public final byte[] zze(int i, boolean z) {
        this.b.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.b.zze(Boolean.FALSE);
        this.f9357a.zzj(this.b.zzm());
        try {
            zzqn.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzjn.zza).ignoreNullValues(true).build().encode(this.f9357a.zzk()).getBytes(JsonRequest.PROTOCOL_CHARSET);
            }
            zzlq zzk = this.f9357a.zzk();
            zzbs zzbsVar = new zzbs();
            zzjn.zza.configure(zzbsVar);
            return zzbsVar.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
