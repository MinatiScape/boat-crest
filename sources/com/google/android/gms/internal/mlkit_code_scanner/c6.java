package com.google.android.gms.internal.mlkit_code_scanner;

import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class c6 extends zzr {
    public static final zzr zza = new c6(null, new Object[0], 0);
    public final transient Object[] zzb;
    private final transient int zzc;

    private c6(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    public static c6 zzg(int i, Object[] objArr, zzq zzqVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        k5.a(obj, obj2);
        return new c6(null, objArr, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0020 A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.zzb
            int r1 = r4.zzc
            r2 = 0
            if (r5 != 0) goto L9
        L7:
            r5 = r2
            goto L1d
        L9:
            r3 = 1
            if (r1 != r3) goto L7
            r1 = 0
            r1 = r0[r1]
            r1.getClass()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L7
            r5 = r0[r3]
            r5.getClass()
        L1d:
            if (r5 != 0) goto L20
            return r2
        L20:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_code_scanner.c6.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    public final zzl zza() {
        return new b6(this.zzb, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    public final zzs zzd() {
        return new z5(this, this.zzb, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    public final zzs zze() {
        return new a6(this, new b6(this.zzb, 0, this.zzc));
    }
}