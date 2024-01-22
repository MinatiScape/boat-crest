package com.google.android.gms.internal.mlkit_vision_common;

import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes8.dex */
public final class zzmk implements zzmb {

    /* renamed from: a  reason: collision with root package name */
    public final zziw f9793a;
    public zzky b = new zzky();

    public zzmk(zziw zziwVar, int i) {
        this.f9793a = zziwVar;
        zzmw.zza();
    }

    public static zzmb zze(zziw zziwVar) {
        return new zzmk(zziwVar, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb zza(zziv zzivVar) {
        this.f9793a.zzc(zzivVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final zzmb zzb(zzky zzkyVar) {
        this.b = zzkyVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final String zzc() {
        zzla zzc = this.f9793a.zzf().zzc();
        return (zzc == null || zzg.zzb(zzc.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzc.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmb
    public final byte[] zzd(int i, boolean z) {
        this.b.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.b.zze(Boolean.FALSE);
        this.f9793a.zze(this.b.zzm());
        try {
            zzmw.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzhe.zza).ignoreNullValues(true).build().encode(this.f9793a.zzf()).getBytes(JsonRequest.PROTOCOL_CHARSET);
            }
            zziy zzf = this.f9793a.zzf();
            zzam zzamVar = new zzam();
            zzhe.zza.configure(zzamVar);
            return zzamVar.zza().zza(zzf);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
