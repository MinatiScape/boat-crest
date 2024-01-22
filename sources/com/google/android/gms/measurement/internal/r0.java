package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzoq;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class r0 implements Callable<byte[]> {
    public final /* synthetic */ zzat h;
    public final /* synthetic */ String i;
    public final /* synthetic */ zzgk j;

    public r0(zzgk zzgkVar, zzat zzatVar, String str) {
        this.j = zzgkVar;
        this.h = zzatVar;
        this.i = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ byte[] call() throws Exception {
        zzkn zzknVar;
        zzkn zzknVar2;
        byte[] bArr;
        zzkn zzknVar3;
        z3 z3Var;
        l0 l0Var;
        zzfv zzfvVar;
        String str;
        Bundle bundle;
        zzfx zzfxVar;
        byte[] bArr2;
        g c;
        long j;
        zzknVar = this.j.f10155a;
        zzknVar.a();
        zzknVar2 = this.j.f10155a;
        z1 zzr = zzknVar2.zzr();
        zzat zzatVar = this.h;
        String str2 = this.i;
        zzr.zzg();
        zzfs.e();
        Preconditions.checkNotNull(zzatVar);
        Preconditions.checkNotEmpty(str2);
        if (!zzr.zzs.zzf().zzs(str2, zzdw.zzU)) {
            zzr.zzs.zzay().zzc().zzb("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if (!"_iap".equals(zzatVar.zza) && !"_iapx".equals(zzatVar.zza)) {
            zzr.zzs.zzay().zzc().zzc("Generating a payload for this event is not available. package_name, event_name", str2, zzatVar.zza);
            return null;
        } else {
            zzfv zza = zzfw.zza();
            zzr.zzf.zzi().zzw();
            try {
                l0 H = zzr.zzf.zzi().H(str2);
                if (H == null) {
                    zzr.zzs.zzay().zzc().zzb("Log and bundle not available. package_name", str2);
                    bArr = new byte[0];
                    zzknVar3 = zzr.zzf;
                } else if (!H.K()) {
                    zzr.zzs.zzay().zzc().zzb("Log and bundle disabled. package_name", str2);
                    bArr = new byte[0];
                    zzknVar3 = zzr.zzf;
                } else {
                    zzfx zzu = zzfy.zzu();
                    zzu.zzaa(1);
                    zzu.zzW(Constants.KEY_ANDROID);
                    if (!TextUtils.isEmpty(H.e0())) {
                        zzu.zzA(H.e0());
                    }
                    if (!TextUtils.isEmpty(H.g0())) {
                        zzu.zzC((String) Preconditions.checkNotNull(H.g0()));
                    }
                    if (!TextUtils.isEmpty(H.h0())) {
                        zzu.zzD((String) Preconditions.checkNotNull(H.h0()));
                    }
                    if (H.M() != -2147483648L) {
                        zzu.zzE((int) H.M());
                    }
                    zzu.zzS(H.X());
                    zzu.zzM(H.V());
                    String k0 = H.k0();
                    String c0 = H.c0();
                    zzoq.zzc();
                    if (zzr.zzs.zzf().zzs(H.e0(), zzdw.zzad)) {
                        String j0 = H.j0();
                        if (!TextUtils.isEmpty(k0)) {
                            zzu.zzR(k0);
                        } else if (!TextUtils.isEmpty(j0)) {
                            zzu.zzQ(j0);
                        } else if (!TextUtils.isEmpty(c0)) {
                            zzu.zzy(c0);
                        }
                    } else if (!TextUtils.isEmpty(k0)) {
                        zzu.zzR(k0);
                    } else if (!TextUtils.isEmpty(c0)) {
                        zzu.zzy(c0);
                    }
                    zzag M = zzr.zzf.M(str2);
                    zzu.zzJ(H.U());
                    if (zzr.zzs.zzJ() && zzr.zzs.zzf().zzt(zzu.zzal()) && M.zzj() && !TextUtils.isEmpty(null)) {
                        zzu.zzL(null);
                    }
                    zzu.zzI(M.zzi());
                    if (M.zzj()) {
                        Pair<String, Boolean> c2 = zzr.zzf.zzs().c(H.e0(), M);
                        if (H.J() && !TextUtils.isEmpty((CharSequence) c2.first)) {
                            try {
                                zzu.zzab(z1.zza((String) c2.first, Long.toString(zzatVar.zzd)));
                                Object obj = c2.second;
                                if (obj != null) {
                                    zzu.zzU(((Boolean) obj).booleanValue());
                                }
                            } catch (SecurityException e) {
                                zzr.zzs.zzay().zzc().zzb("Resettable device id encryption failed", e.getMessage());
                                bArr = new byte[0];
                                zzknVar3 = zzr.zzf;
                            }
                        }
                    }
                    zzr.zzs.zzg().zzu();
                    zzu.zzK(Build.MODEL);
                    zzr.zzs.zzg().zzu();
                    zzu.zzV(Build.VERSION.RELEASE);
                    zzu.zzaf((int) zzr.zzs.zzg().zzb());
                    zzu.zzaj(zzr.zzs.zzg().zzc());
                    try {
                        if (M.zzk() && H.f0() != null) {
                            zzu.zzB(z1.zza((String) Preconditions.checkNotNull(H.f0()), Long.toString(zzatVar.zzd)));
                        }
                        if (!TextUtils.isEmpty(H.i0())) {
                            zzu.zzP((String) Preconditions.checkNotNull(H.i0()));
                        }
                        String e0 = H.e0();
                        List<z3> R = zzr.zzf.zzi().R(e0);
                        Iterator<z3> it = R.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z3Var = null;
                                break;
                            }
                            z3Var = it.next();
                            if ("_lte".equals(z3Var.c)) {
                                break;
                            }
                        }
                        if (z3Var == null || z3Var.e == null) {
                            z3 z3Var2 = new z3(e0, "auto", "_lte", zzr.zzs.zzav().currentTimeMillis(), 0L);
                            R.add(z3Var2);
                            zzr.zzf.zzi().n(z3Var2);
                        }
                        zzkp zzu2 = zzr.zzf.zzu();
                        zzu2.zzs.zzay().zzj().zza("Checking account type status for ad personalization signals");
                        if (zzu2.zzs.zzg().c()) {
                            String e02 = H.e0();
                            Preconditions.checkNotNull(e02);
                            if (H.J() && zzu2.zzf.zzo().f(e02)) {
                                zzu2.zzs.zzay().zzc().zza("Turning off ad personalization due to account type");
                                Iterator<z3> it2 = R.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    } else if ("_npa".equals(it2.next().c)) {
                                        it2.remove();
                                        break;
                                    }
                                }
                                R.add(new z3(e02, "auto", "_npa", zzu2.zzs.zzav().currentTimeMillis(), 1L));
                            }
                        }
                        zzgh[] zzghVarArr = new zzgh[R.size()];
                        for (int i = 0; i < R.size(); i++) {
                            zzgg zzd = zzgh.zzd();
                            zzd.zzf(R.get(i).c);
                            zzd.zzg(R.get(i).d);
                            zzr.zzf.zzu().B(zzd, R.get(i).e);
                            zzghVarArr[i] = zzd.zzaA();
                        }
                        zzu.zzi(Arrays.asList(zzghVarArr));
                        zzej zzb = zzej.zzb(zzatVar);
                        zzr.zzs.zzv().h(zzb.zzd, zzr.zzf.zzi().G(str2));
                        zzr.zzs.zzv().i(zzb, zzr.zzs.zzf().zzd(str2));
                        Bundle bundle2 = zzb.zzd;
                        bundle2.putLong("_c", 1L);
                        zzr.zzs.zzay().zzc().zza("Marking in-app purchase as real-time");
                        bundle2.putLong("_r", 1L);
                        bundle2.putString("_o", zzatVar.zzc);
                        if (zzr.zzs.zzv().u(zzu.zzal())) {
                            zzr.zzs.zzv().k(bundle2, "_dbg", 1L);
                            zzr.zzs.zzv().k(bundle2, "_r", 1L);
                        }
                        g L = zzr.zzf.zzi().L(str2, zzatVar.zza);
                        if (L == null) {
                            zzfxVar = zzu;
                            l0Var = H;
                            zzfvVar = zza;
                            str = str2;
                            bundle = bundle2;
                            bArr2 = null;
                            c = new g(str2, zzatVar.zza, 0L, 0L, 0L, zzatVar.zzd, 0L, null, null, null, null);
                            j = 0;
                        } else {
                            l0Var = H;
                            zzfvVar = zza;
                            str = str2;
                            bundle = bundle2;
                            zzfxVar = zzu;
                            bArr2 = null;
                            long j2 = L.f;
                            c = L.c(zzatVar.zzd);
                            j = j2;
                        }
                        zzr.zzf.zzi().e(c);
                        zzao zzaoVar = new zzao(zzr.zzs, zzatVar.zzc, str, zzatVar.zza, zzatVar.zzd, j, bundle);
                        zzfn zze = zzfo.zze();
                        zze.zzm(zzaoVar.d);
                        zze.zzi(zzaoVar.b);
                        zze.zzl(zzaoVar.e);
                        h hVar = new h(zzaoVar.f);
                        while (hVar.hasNext()) {
                            String next = hVar.next();
                            zzfr zze2 = com.google.android.gms.internal.measurement.zzfs.zze();
                            zze2.zzj(next);
                            Object d = zzaoVar.f.d(next);
                            if (d != null) {
                                zzr.zzf.zzu().A(zze2, d);
                                zze.zze(zze2);
                            }
                        }
                        zzfx zzfxVar2 = zzfxVar;
                        zzfxVar2.zzj(zze);
                        zzfz zza2 = zzgb.zza();
                        com.google.android.gms.internal.measurement.zzfp zza3 = com.google.android.gms.internal.measurement.zzfq.zza();
                        zza3.zza(c.c);
                        zza3.zzb(zzatVar.zza);
                        zza2.zza(zza3);
                        zzfxVar2.zzX(zza2);
                        zzfxVar2.zzf(zzr.zzf.zzf().b(l0Var.e0(), Collections.emptyList(), zzfxVar2.zzap(), Long.valueOf(zze.zzc()), Long.valueOf(zze.zzc())));
                        if (zze.zzq()) {
                            zzfxVar2.zzae(zze.zzc());
                            zzfxVar2.zzN(zze.zzc());
                        }
                        long Y = l0Var.Y();
                        int i2 = (Y > 0L ? 1 : (Y == 0L ? 0 : -1));
                        if (i2 != 0) {
                            zzfxVar2.zzY(Y);
                        }
                        long a0 = l0Var.a0();
                        if (a0 != 0) {
                            zzfxVar2.zzZ(a0);
                        } else if (i2 != 0) {
                            zzfxVar2.zzZ(Y);
                        }
                        l0Var.e();
                        zzfxVar2.zzF((int) l0Var.Z());
                        zzr.zzs.zzf().zzh();
                        zzfxVar2.zzah(42097L);
                        zzfxVar2.zzag(zzr.zzs.zzav().currentTimeMillis());
                        zzfxVar2.zzad(true);
                        zzfv zzfvVar2 = zzfvVar;
                        zzfvVar2.zza(zzfxVar2);
                        l0 l0Var2 = l0Var;
                        l0Var2.E(zzfxVar2.zzd());
                        l0Var2.C(zzfxVar2.zzc());
                        zzr.zzf.zzi().d(l0Var2);
                        zzr.zzf.zzi().zzC();
                        try {
                            return zzr.zzf.zzu().F(zzfvVar2.zzaA().zzbs());
                        } catch (IOException e2) {
                            zzr.zzs.zzay().zzd().zzc("Data loss. Failed to bundle and serialize. appId", zzei.zzn(str), e2);
                            return bArr2;
                        }
                    } catch (SecurityException e3) {
                        zzr.zzs.zzay().zzc().zzb("app instance id encryption failed", e3.getMessage());
                        bArr = new byte[0];
                        zzknVar3 = zzr.zzf;
                    }
                }
                zzknVar3.zzi().T();
                return bArr;
            } finally {
                zzr.zzf.zzi().T();
            }
        }
    }
}
