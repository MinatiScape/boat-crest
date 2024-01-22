package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzhc {

    /* renamed from: a  reason: collision with root package name */
    public zzgy f8767a;
    public zzgt h;
    public final zzhh i;
    public String j;
    public zzgu k;
    public zzhi n;
    public zzjm o;
    public zzgw p;
    public zzgx b = new zzgx();
    public zzgx c = new zzgx();
    public int d = 10;
    public int e = 16384;
    public boolean f = true;
    public boolean g = true;
    public int l = 20000;
    public int m = 20000;
    public boolean q = true;
    public boolean r = true;
    public zzjo s = zzjo.zzaif;

    public zzhc(zzhh zzhhVar, String str) {
        this.i = zzhhVar;
        zzaf(null);
    }

    public final String getRequestMethod() {
        return this.j;
    }

    public final zzhc zza(zzgu zzguVar) {
        this.k = (zzgu) zzml.checkNotNull(zzguVar);
        return this;
    }

    public final zzhc zzad(int i) {
        zzml.checkArgument(true);
        this.l = 5000;
        return this;
    }

    public final zzhc zzae(int i) {
        zzml.checkArgument(true);
        this.m = 10000;
        return this;
    }

    public final zzhc zzaf(String str) {
        zzml.checkArgument(str == null || zzgz.c(str));
        this.j = str;
        return this;
    }

    public final zzhh zzfv() {
        return this.i;
    }

    public final zzgu zzfw() {
        return this.k;
    }

    public final zzgt zzfx() {
        return this.h;
    }

    public final int zzfy() {
        return this.e;
    }

    public final boolean zzfz() {
        return this.f;
    }

    public final zzgx zzga() {
        return this.b;
    }

    public final zzgx zzgb() {
        return this.c;
    }

    public final zzhi zzgc() {
        return this.n;
    }

    public final zzjm zzgd() {
        return this.o;
    }

    public final boolean zzge() {
        return this.r;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0271 A[Catch: all -> 0x029c, TRY_LEAVE, TryCatch #2 {all -> 0x029c, blocks: (B:84:0x020c, B:86:0x0212, B:90:0x0226, B:95:0x0230, B:97:0x0242, B:99:0x024c, B:101:0x026e, B:103:0x0271), top: B:135:0x020c }] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0299 A[LOOP:0: B:7:0x0019->B:122:0x0299, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x027b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0212 A[Catch: all -> 0x029c, TryCatch #2 {all -> 0x029c, blocks: (B:84:0x020c, B:86:0x0212, B:90:0x0226, B:95:0x0230, B:97:0x0242, B:99:0x024c, B:101:0x026e, B:103:0x0271), top: B:135:0x020c }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0242 A[Catch: all -> 0x029c, TryCatch #2 {all -> 0x029c, blocks: (B:84:0x020c, B:86:0x0212, B:90:0x0226, B:95:0x0230, B:97:0x0242, B:99:0x024c, B:101:0x026e, B:103:0x0271), top: B:135:0x020c }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x024b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.firebase_ml.zzhd zzgf() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzhc.zzgf():com.google.android.gms.internal.firebase_ml.zzhd");
    }

    public final zzhc zza(zzgt zzgtVar) {
        this.h = zzgtVar;
        return this;
    }

    public final zzhc zza(zzgw zzgwVar) {
        this.p = zzgwVar;
        return this;
    }

    public final zzhc zza(zzgy zzgyVar) {
        this.f8767a = zzgyVar;
        return this;
    }

    public final zzhc zza(zzhi zzhiVar) {
        this.n = zzhiVar;
        return this;
    }

    public final zzhc zza(zzjm zzjmVar) {
        this.o = zzjmVar;
        return this;
    }
}
