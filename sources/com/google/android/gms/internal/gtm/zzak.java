package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzak extends zzuv<zzak, zzal> {
    private static final zzvf<Integer, zzao> zze = new zzvf<Integer, zzao>() { // from class: com.google.android.gms.internal.gtm.zzak.1
        @Override // com.google.android.gms.internal.gtm.zzvf
        /* renamed from: zza */
        public zzao zzb(Integer num) {
            zzao zzb = zzao.zzb(num.intValue());
            return zzb == null ? zzao.ESCAPE_HTML : zzb;
        }
    };
    private static final zzak zzf;
    private int zzg;
    private long zzo;
    private boolean zzp;
    private boolean zzs;
    private byte zzt = 2;
    private int zzh = 1;
    private String zzi = "";
    private zzvh<zzak> zzj = zzuz.zzag();
    private zzvh<zzak> zzk = zzuz.zzag();
    private zzvh<zzak> zzl = zzuz.zzag();
    private String zzm = "";
    private String zzn = "";
    private zzvh<zzak> zzq = zzuz.zzag();
    private zzve zzr = zzuz.zzaf();

    static {
        zzak zzakVar = new zzak();
        zzf = zzakVar;
        zzuz.zzak(zzak.class, zzakVar);
    }

    public static /* synthetic */ void zzA(zzak zzakVar, String str) {
        str.getClass();
        zzakVar.zzg |= 4;
        zzakVar.zzm = str;
    }

    public static /* synthetic */ void zzB(zzak zzakVar, String str) {
        str.getClass();
        zzakVar.zzg |= 8;
        zzakVar.zzn = str;
    }

    public static /* synthetic */ void zzC(zzak zzakVar, String str) {
        str.getClass();
        zzakVar.zzg |= 2;
        zzakVar.zzi = str;
    }

    public static /* synthetic */ void zzD(zzak zzakVar, long j) {
        zzakVar.zzg |= 16;
        zzakVar.zzo = j;
    }

    public static /* synthetic */ void zzE(zzak zzakVar, boolean z) {
        zzakVar.zzg |= 32;
        zzakVar.zzp = z;
    }

    public static /* synthetic */ void zzF(zzak zzakVar, zzak zzakVar2) {
        zzakVar2.getClass();
        zzvh<zzak> zzvhVar = zzakVar.zzq;
        if (!zzvhVar.zzc()) {
            zzakVar.zzq = zzuz.zzah(zzvhVar);
        }
        zzakVar.zzq.add(zzakVar2);
    }

    public static /* synthetic */ void zzH(zzak zzakVar, Iterable iterable) {
        zzve zzveVar = zzakVar.zzr;
        if (!zzveVar.zzc()) {
            int size = zzveVar.size();
            zzakVar.zzr = zzveVar.zzg(size == 0 ? 10 : size + size);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzakVar.zzr.zzh(((zzao) it.next()).zza());
        }
    }

    public static /* synthetic */ void zzJ(zzak zzakVar, boolean z) {
        zzakVar.zzg |= 64;
        zzakVar.zzs = z;
    }

    public static /* synthetic */ void zzK(zzak zzakVar, zzak zzakVar2) {
        zzakVar2.getClass();
        zzakVar.zzal();
        zzakVar.zzj.add(zzakVar2);
    }

    public static /* synthetic */ void zzL(zzak zzakVar, Iterable iterable) {
        zzakVar.zzal();
        zzsh.zzS(iterable, zzakVar.zzj);
    }

    public static /* synthetic */ void zzP(zzak zzakVar, int i) {
        zzakVar.zzh = i;
        zzakVar.zzg |= 1;
    }

    public static zzal zzg() {
        return (zzal) zzf.zzY();
    }

    public static zzak zzi() {
        return zzf;
    }

    public static /* synthetic */ void zzu(zzak zzakVar, zzak zzakVar2) {
        zzakVar2.getClass();
        zzakVar.zzam();
        zzakVar.zzk.add(zzakVar2);
    }

    public static /* synthetic */ void zzv(zzak zzakVar, Iterable iterable) {
        zzakVar.zzam();
        zzsh.zzS(iterable, zzakVar.zzk);
    }

    public static /* synthetic */ void zzx(zzak zzakVar, zzak zzakVar2) {
        zzakVar2.getClass();
        zzakVar.zzan();
        zzakVar.zzl.add(zzakVar2);
    }

    public static /* synthetic */ void zzy(zzak zzakVar, Iterable iterable) {
        zzakVar.zzan();
        zzsh.zzS(iterable, zzakVar.zzl);
    }

    public final boolean zzM() {
        return this.zzp;
    }

    public final boolean zzN() {
        return this.zzs;
    }

    public final int zzO() {
        int zza = zzar.zza(this.zzh);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zza() {
        return this.zzj.size();
    }

    public final void zzal() {
        zzvh<zzak> zzvhVar = this.zzj;
        if (zzvhVar.zzc()) {
            return;
        }
        this.zzj = zzuz.zzah(zzvhVar);
    }

    public final void zzam() {
        zzvh<zzak> zzvhVar = this.zzk;
        if (zzvhVar.zzc()) {
            return;
        }
        this.zzk = zzuz.zzah(zzvhVar);
    }

    public final void zzan() {
        zzvh<zzak> zzvhVar = this.zzl;
        if (zzvhVar.zzc()) {
            return;
        }
        this.zzl = zzuz.zzah(zzvhVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzt = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzf;
                    }
                    return new zzal(null);
                }
                return new zzak();
            }
            return zzuz.zzaj(zzf, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0005\u0005\u0001ᔌ\u0000\u0002ဈ\u0001\u0003Л\u0004Л\u0005Л\u0006ဈ\u0002\u0007ဈ\u0003\bဂ\u0004\tဇ\u0006\n\u001e\u000bЛ\fဇ\u0005", new Object[]{"zzg", "zzh", zzaq.zza, "zzi", "zzj", zzak.class, "zzk", zzak.class, "zzl", zzak.class, "zzm", "zzn", "zzo", "zzs", "zzr", zzao.zzc(), "zzq", zzak.class, "zzp"});
        }
        return Byte.valueOf(this.zzt);
    }

    public final int zzc() {
        return this.zzk.size();
    }

    public final int zzd() {
        return this.zzl.size();
    }

    public final int zze() {
        return this.zzq.size();
    }

    public final long zzf() {
        return this.zzo;
    }

    public final zzak zzj(int i) {
        return this.zzj.get(i);
    }

    public final zzak zzk(int i) {
        return this.zzk.get(i);
    }

    public final zzak zzl(int i) {
        return this.zzl.get(i);
    }

    public final zzak zzm(int i) {
        return this.zzq.get(i);
    }

    public final String zzn() {
        return this.zzn;
    }

    public final String zzo() {
        return this.zzm;
    }

    public final String zzp() {
        return this.zzi;
    }

    public final List<zzao> zzq() {
        return new zzvg(this.zzr, zze);
    }

    public final List<zzak> zzr() {
        return this.zzj;
    }

    public final List<zzak> zzs() {
        return this.zzq;
    }
}
