package com.google.android.gms.internal.common;
/* loaded from: classes7.dex */
public abstract class o extends f {
    public final CharSequence j;
    public final boolean k;
    public int l = 0;
    public int m;

    public o(zzx zzxVar, CharSequence charSequence) {
        boolean z;
        zzo unused;
        unused = zzxVar.f8631a;
        z = zzxVar.b;
        this.k = z;
        this.m = Integer.MAX_VALUE;
        this.j = charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
        r3 = r5.m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
        if (r3 != 1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        r1 = r5.j.length();
        r5.l = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        if (r1 <= r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        r5.j.charAt(r1 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        r5.m = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
        return r5.j.subSequence(r0, r1).toString();
     */
    @Override // com.google.android.gms.internal.common.f
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* bridge */ /* synthetic */ java.lang.Object a() {
        /*
            r5 = this;
            int r0 = r5.l
        L2:
            int r1 = r5.l
            r2 = -1
            if (r1 == r2) goto L6c
            int r1 = r5.d(r1)
            if (r1 != r2) goto L17
            java.lang.CharSequence r1 = r5.j
            int r1 = r1.length()
            r5.l = r2
            r3 = r2
            goto L1d
        L17:
            int r3 = r5.c(r1)
            r5.l = r3
        L1d:
            if (r3 != r0) goto L2e
            int r3 = r3 + 1
            r5.l = r3
            java.lang.CharSequence r1 = r5.j
            int r1 = r1.length()
            if (r3 <= r1) goto L2
            r5.l = r2
            goto L2
        L2e:
            if (r0 >= r1) goto L35
            java.lang.CharSequence r3 = r5.j
            r3.charAt(r0)
        L35:
            if (r0 >= r1) goto L3e
            java.lang.CharSequence r3 = r5.j
            int r4 = r1 + (-1)
            r3.charAt(r4)
        L3e:
            boolean r3 = r5.k
            if (r3 == 0) goto L47
            if (r0 != r1) goto L47
            int r0 = r5.l
            goto L2
        L47:
            int r3 = r5.m
            r4 = 1
            if (r3 != r4) goto L5e
            java.lang.CharSequence r1 = r5.j
            int r1 = r1.length()
            r5.l = r2
            if (r1 <= r0) goto L61
            java.lang.CharSequence r2 = r5.j
            int r3 = r1 + (-1)
            r2.charAt(r3)
            goto L61
        L5e:
            int r3 = r3 + r2
            r5.m = r3
        L61:
            java.lang.CharSequence r2 = r5.j
            java.lang.CharSequence r0 = r2.subSequence(r0, r1)
            java.lang.String r0 = r0.toString()
            goto L70
        L6c:
            r5.b()
            r0 = 0
        L70:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.common.o.a():java.lang.Object");
    }

    public abstract int c(int i);

    public abstract int d(int i);
}
