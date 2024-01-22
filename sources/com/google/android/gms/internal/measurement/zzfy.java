package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.Constants;
import java.util.List;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes8.dex */
public final class zzfy extends zzjz<zzfy, zzfx> implements zzlh {
    public static final /* synthetic */ int zza = 0;
    private static final zzfy zze;
    private boolean zzA;
    private long zzC;
    private int zzD;
    private boolean zzG;
    private int zzJ;
    private int zzK;
    private int zzL;
    private long zzN;
    private long zzO;
    private int zzR;
    private zzgb zzT;
    private long zzV;
    private long zzW;
    private int zzZ;
    private boolean zzaa;
    private boolean zzac;
    private zzfu zzad;
    private int zzf;
    private int zzg;
    private int zzh;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private int zzt;
    private long zzx;
    private long zzy;
    private zzkg<zzfo> zzi = zzjz.zzbA();
    private zzkg<zzgh> zzj = zzjz.zzbA();
    private String zzp = "";
    private String zzq = "";
    private String zzr = "";
    private String zzs = "";
    private String zzu = "";
    private String zzv = "";
    private String zzw = "";
    private String zzz = "";
    private String zzB = "";
    private String zzE = "";
    private String zzF = "";
    private zzkg<zzfk> zzH = zzjz.zzbA();
    private String zzI = "";
    private String zzM = "";
    private String zzP = "";
    private String zzQ = "";
    private String zzS = "";
    private zzke zzU = zzjz.zzbx();
    private String zzX = "";
    private String zzY = "";
    private String zzab = "";

    static {
        zzfy zzfyVar = new zzfy();
        zze = zzfyVar;
        zzjz.zzbG(zzfy.class, zzfyVar);
    }

    public static /* synthetic */ void A(zzfy zzfyVar, Iterable iterable) {
        zzkg<zzfk> zzkgVar = zzfyVar.zzH;
        if (!zzkgVar.zzc()) {
            zzfyVar.zzH = zzjz.zzbB(zzkgVar);
        }
        zzih.zzbq(iterable, zzfyVar.zzH);
    }

    public static /* synthetic */ void C(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 16777216;
        zzfyVar.zzI = str;
    }

    public static /* synthetic */ void D(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 33554432;
        zzfyVar.zzJ = i;
    }

    public static /* synthetic */ void E(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1;
        zzfyVar.zzh = 1;
    }

    public static /* synthetic */ void F(zzfy zzfyVar) {
        zzfyVar.zzf &= -268435457;
        zzfyVar.zzM = zze.zzM;
    }

    public static /* synthetic */ void G(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= PKIFailureInfo.duplicateCertReq;
        zzfyVar.zzN = j;
    }

    public static /* synthetic */ void H(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzg |= 64;
        zzfyVar.zzX = str;
    }

    public static /* synthetic */ void I(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzg |= 128;
        zzfyVar.zzY = str;
    }

    public static /* synthetic */ void J(zzfy zzfyVar, Iterable iterable) {
        zzfyVar.h0();
        zzih.zzbq(iterable, zzfyVar.zzi);
    }

    public static /* synthetic */ void L(zzfy zzfyVar, int i) {
        zzfyVar.h0();
        zzfyVar.zzi.remove(i);
    }

    public static /* synthetic */ void M(zzfy zzfyVar, int i, zzgh zzghVar) {
        zzghVar.getClass();
        zzfyVar.i0();
        zzfyVar.zzj.set(i, zzghVar);
    }

    public static /* synthetic */ void N(zzfy zzfyVar, zzgh zzghVar) {
        zzghVar.getClass();
        zzfyVar.i0();
        zzfyVar.zzj.add(zzghVar);
    }

    public static /* synthetic */ void O(zzfy zzfyVar, Iterable iterable) {
        zzfyVar.i0();
        zzih.zzbq(iterable, zzfyVar.zzj);
    }

    public static /* synthetic */ void P(zzfy zzfyVar, int i) {
        zzfyVar.i0();
        zzfyVar.zzj.remove(i);
    }

    public static /* synthetic */ void Q(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 2;
        zzfyVar.zzk = j;
    }

    public static /* synthetic */ void R(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 4;
        zzfyVar.zzl = j;
    }

    public static /* synthetic */ void S(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 8;
        zzfyVar.zzm = j;
    }

    public static /* synthetic */ void T(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 16;
        zzfyVar.zzn = j;
    }

    public static /* synthetic */ void U(zzfy zzfyVar) {
        zzfyVar.zzf &= -17;
        zzfyVar.zzn = 0L;
    }

    public static /* synthetic */ void V(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 32;
        zzfyVar.zzo = j;
    }

    public static /* synthetic */ void W(zzfy zzfyVar) {
        zzfyVar.zzf &= -33;
        zzfyVar.zzo = 0L;
    }

    public static /* synthetic */ void X(zzfy zzfyVar, String str) {
        zzfyVar.zzf |= 64;
        zzfyVar.zzp = Constants.KEY_ANDROID;
    }

    public static /* synthetic */ void Y(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 128;
        zzfyVar.zzq = str;
    }

    public static /* synthetic */ void Z(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 256;
        zzfyVar.zzr = str;
    }

    public static /* synthetic */ void a0(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 512;
        zzfyVar.zzs = str;
    }

    public static /* synthetic */ void b0(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1024;
        zzfyVar.zzt = i;
    }

    public static /* synthetic */ void c0(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 2048;
        zzfyVar.zzu = str;
    }

    public static /* synthetic */ void d0(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 4096;
        zzfyVar.zzv = str;
    }

    public static /* synthetic */ void e(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 1073741824;
        zzfyVar.zzO = j;
    }

    public static /* synthetic */ void e0(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 8192;
        zzfyVar.zzw = str;
    }

    public static /* synthetic */ void f(zzfy zzfyVar) {
        zzfyVar.zzf &= Integer.MAX_VALUE;
        zzfyVar.zzP = zze.zzP;
    }

    public static /* synthetic */ void f0(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 16384;
        zzfyVar.zzx = j;
    }

    public static /* synthetic */ void g(zzfy zzfyVar, int i) {
        zzfyVar.zzg |= 2;
        zzfyVar.zzR = i;
    }

    public static /* synthetic */ void g0(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 32768;
        zzfyVar.zzy = 42097L;
    }

    public static /* synthetic */ void h(zzfy zzfyVar, int i, zzfo zzfoVar) {
        zzfoVar.getClass();
        zzfyVar.h0();
        zzfyVar.zzi.set(i, zzfoVar);
    }

    public static /* synthetic */ void i(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzg |= 4;
        zzfyVar.zzS = str;
    }

    public static /* synthetic */ void j(zzfy zzfyVar, zzgb zzgbVar) {
        zzgbVar.getClass();
        zzfyVar.zzT = zzgbVar;
        zzfyVar.zzg |= 8;
    }

    public static /* synthetic */ void k(zzfy zzfyVar, Iterable iterable) {
        zzke zzkeVar = zzfyVar.zzU;
        if (!zzkeVar.zzc()) {
            int size = zzkeVar.size();
            zzfyVar.zzU = zzkeVar.zzg(size == 0 ? 10 : size + size);
        }
        zzih.zzbq(iterable, zzfyVar.zzU);
    }

    public static /* synthetic */ void l(zzfy zzfyVar, zzfo zzfoVar) {
        zzfoVar.getClass();
        zzfyVar.h0();
        zzfyVar.zzi.add(zzfoVar);
    }

    public static /* synthetic */ void m(zzfy zzfyVar, long j) {
        zzfyVar.zzg |= 16;
        zzfyVar.zzV = j;
    }

    public static /* synthetic */ void n(zzfy zzfyVar, long j) {
        zzfyVar.zzg |= 32;
        zzfyVar.zzW = j;
    }

    public static /* synthetic */ void o(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 65536;
        zzfyVar.zzz = str;
    }

    public static /* synthetic */ void p(zzfy zzfyVar) {
        zzfyVar.zzf &= -65537;
        zzfyVar.zzz = zze.zzz;
    }

    public static /* synthetic */ void q(zzfy zzfyVar, boolean z) {
        zzfyVar.zzf |= 131072;
        zzfyVar.zzA = z;
    }

    public static /* synthetic */ void r(zzfy zzfyVar) {
        zzfyVar.zzf &= -131073;
        zzfyVar.zzA = false;
    }

    public static /* synthetic */ void s(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 262144;
        zzfyVar.zzB = str;
    }

    public static /* synthetic */ void t(zzfy zzfyVar) {
        zzfyVar.zzf &= -262145;
        zzfyVar.zzB = zze.zzB;
    }

    public static /* synthetic */ void u(zzfy zzfyVar, long j) {
        zzfyVar.zzf |= 524288;
        zzfyVar.zzC = j;
    }

    public static /* synthetic */ void v(zzfy zzfyVar, int i) {
        zzfyVar.zzf |= 1048576;
        zzfyVar.zzD = i;
    }

    public static /* synthetic */ void w(zzfy zzfyVar, String str) {
        zzfyVar.zzf |= 2097152;
        zzfyVar.zzE = str;
    }

    public static /* synthetic */ void x(zzfy zzfyVar) {
        zzfyVar.zzf &= -2097153;
        zzfyVar.zzE = zze.zzE;
    }

    public static /* synthetic */ void y(zzfy zzfyVar, String str) {
        str.getClass();
        zzfyVar.zzf |= 4194304;
        zzfyVar.zzF = str;
    }

    public static /* synthetic */ void z(zzfy zzfyVar, boolean z) {
        zzfyVar.zzf |= 8388608;
        zzfyVar.zzG = z;
    }

    public static zzfx zzu() {
        return zze.zzbu();
    }

    public final void h0() {
        zzkg<zzfo> zzkgVar = this.zzi;
        if (zzkgVar.zzc()) {
            return;
        }
        this.zzi = zzjz.zzbB(zzkgVar);
    }

    public final void i0() {
        zzkg<zzgh> zzkgVar = this.zzj;
        if (zzkgVar.zzc()) {
            return;
        }
        this.zzj = zzjz.zzbB(zzkgVar);
    }

    public final String zzA() {
        return this.zzu;
    }

    public final String zzB() {
        return this.zzw;
    }

    public final String zzC() {
        return this.zzY;
    }

    public final String zzD() {
        return this.zzr;
    }

    public final String zzE() {
        return this.zzP;
    }

    public final String zzF() {
        return this.zzI;
    }

    public final String zzG() {
        return this.zzX;
    }

    public final String zzH() {
        return this.zzF;
    }

    public final String zzI() {
        return this.zzE;
    }

    public final String zzJ() {
        return this.zzq;
    }

    public final String zzK() {
        return this.zzp;
    }

    public final String zzL() {
        return this.zzz;
    }

    public final String zzM() {
        return this.zzs;
    }

    public final List<zzfk> zzN() {
        return this.zzH;
    }

    public final List<zzfo> zzO() {
        return this.zzi;
    }

    public final List<zzgh> zzP() {
        return this.zzj;
    }

    public final int zza() {
        return this.zzJ;
    }

    public final boolean zzaT() {
        return this.zzA;
    }

    public final boolean zzaU() {
        return this.zzG;
    }

    public final boolean zzaV() {
        return (this.zzf & 1073741824) != 0;
    }

    public final boolean zzaW() {
        return (this.zzf & 33554432) != 0;
    }

    public final boolean zzaX() {
        return (this.zzf & 1048576) != 0;
    }

    public final boolean zzaY() {
        return (this.zzf & PKIFailureInfo.duplicateCertReq) != 0;
    }

    public final boolean zzaZ() {
        return (this.zzg & 128) != 0;
    }

    public final int zzb() {
        return this.zzD;
    }

    public final boolean zzba() {
        return (this.zzf & 524288) != 0;
    }

    public final boolean zzbb() {
        return (this.zzg & 16) != 0;
    }

    public final boolean zzbc() {
        return (this.zzf & 8) != 0;
    }

    public final boolean zzbd() {
        return (this.zzf & 16384) != 0;
    }

    public final boolean zzbe() {
        return (this.zzf & 131072) != 0;
    }

    public final boolean zzbf() {
        return (this.zzf & 32) != 0;
    }

    public final boolean zzbg() {
        return (this.zzf & 16) != 0;
    }

    public final boolean zzbh() {
        return (this.zzf & 1) != 0;
    }

    public final boolean zzbi() {
        return (this.zzg & 2) != 0;
    }

    public final boolean zzbj() {
        return (this.zzf & 8388608) != 0;
    }

    public final boolean zzbk() {
        return (this.zzf & 4) != 0;
    }

    public final boolean zzbl() {
        return (this.zzf & 1024) != 0;
    }

    public final boolean zzbm() {
        return (this.zzf & 2) != 0;
    }

    public final boolean zzbn() {
        return (this.zzf & 32768) != 0;
    }

    public final int zzc() {
        return this.zzi.size();
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzR;
    }

    public final int zzf() {
        return this.zzt;
    }

    public final int zzg() {
        return this.zzj.size();
    }

    public final long zzh() {
        return this.zzO;
    }

    public final long zzi() {
        return this.zzN;
    }

    public final long zzj() {
        return this.zzC;
    }

    public final long zzk() {
        return this.zzV;
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zze;
                    }
                    return new zzfx(null);
                }
                return new zzfy();
            }
            return zzjz.zzbF(zze, "\u00011\u0000\u0002\u0001;1\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,", new Object[]{"zzf", "zzg", "zzh", "zzi", zzfo.class, "zzj", zzgh.class, "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzF", "zzn", "zzG", "zzH", zzfk.class, "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", "zzZ", j1.f8915a, "zzaa", "zzab", "zzac", "zzad"});
        }
        return (byte) 1;
    }

    public final long zzm() {
        return this.zzm;
    }

    public final long zzn() {
        return this.zzx;
    }

    public final long zzo() {
        return this.zzo;
    }

    public final long zzp() {
        return this.zzn;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final long zzr() {
        return this.zzk;
    }

    public final long zzs() {
        return this.zzy;
    }

    public final zzfo zzt(int i) {
        return this.zzi.get(i);
    }

    public final zzgh zzw(int i) {
        return this.zzj.get(i);
    }

    public final String zzx() {
        return this.zzS;
    }

    public final String zzy() {
        return this.zzv;
    }

    public final String zzz() {
        return this.zzB;
    }
}
