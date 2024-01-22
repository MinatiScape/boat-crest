package com.google.android.gms.internal.mlkit_code_scanner;

import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes8.dex */
public final class zzob implements zznq {

    /* renamed from: a  reason: collision with root package name */
    public final zzkc f9150a;
    public zzmq b = new zzmq();

    public zzob(zzkc zzkcVar, int i) {
        this.f9150a = zzkcVar;
        zzok.zza();
    }

    public static zznq zze(zzkc zzkcVar) {
        return new zzob(zzkcVar, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final zznq zza(zzkb zzkbVar) {
        this.f9150a.zzd(zzkbVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final zznq zzb(zzmq zzmqVar) {
        this.b = zzmqVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final String zzc() {
        zzms zzc = this.f9150a.zzf().zzc();
        return (zzc == null || zzg.zzb(zzc.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzc.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final byte[] zzd(int i, boolean z) {
        this.b.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.b.zze(Boolean.FALSE);
        this.f9150a.zze(this.b.zzm());
        try {
            zzok.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzig.zza).ignoreNullValues(true).build().encode(this.f9150a.zzf()).getBytes(JsonRequest.PROTOCOL_CHARSET);
            }
            zzke zzf = this.f9150a.zzf();
            zzal zzalVar = new zzal();
            zzig.zza.configure(zzalVar);
            return zzalVar.zza().zza(zzf);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
