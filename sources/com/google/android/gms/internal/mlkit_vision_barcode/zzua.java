package com.google.android.gms.internal.mlkit_vision_barcode;

import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes9.dex */
public final class zzua implements zztm {

    /* renamed from: a  reason: collision with root package name */
    public final zzpl f9578a;
    public zzsj b = new zzsj();
    public final int c;

    public zzua(zzpl zzplVar, int i) {
        this.f9578a = zzplVar;
        zzuj.zza();
        this.c = i;
    }

    public static zztm zzf(zzpl zzplVar) {
        return new zzua(zzplVar, 0);
    }

    public static zztm zzg(zzpl zzplVar, int i) {
        return new zzua(zzplVar, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztm
    public final int zza() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztm
    public final zztm zzb(zzpk zzpkVar) {
        this.f9578a.zzf(zzpkVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztm
    public final zztm zzc(zzsj zzsjVar) {
        this.b = zzsjVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztm
    public final String zzd() {
        zzsl zzg = this.f9578a.zzk().zzg();
        return (zzg == null || zzbd.zzc(zzg.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzg.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztm
    public final byte[] zze(int i, boolean z) {
        this.b.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.b.zze(Boolean.FALSE);
        this.f9578a.zzj(this.b.zzm());
        try {
            zzuj.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zznj.zza).ignoreNullValues(true).build().encode(this.f9578a.zzk()).getBytes(JsonRequest.PROTOCOL_CHARSET);
            }
            zzpn zzk = this.f9578a.zzk();
            zzfk zzfkVar = new zzfk();
            zznj.zza.configure(zzfkVar);
            return zzfkVar.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
