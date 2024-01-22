package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class y1 implements o1 {

    /* renamed from: a  reason: collision with root package name */
    public final zzfw f8546a;
    public final String b = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a";
    public final Object[] c;
    public final int d;

    public y1(zzfw zzfwVar, String str, Object[] objArr) {
        this.f8546a = zzfwVar;
        this.c = objArr;
        char charAt = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a".charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a".charAt(i3);
            if (charAt2 < 55296) {
                this.d = (charAt2 << i2) | i;
                return;
            }
            i |= (charAt2 & 8191) << i2;
            i2 += 13;
            i3 = i4;
        }
    }

    public final String a() {
        return this.b;
    }

    public final Object[] b() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.auth.o1
    public final zzfw zza() {
        return this.f8546a;
    }

    @Override // com.google.android.gms.internal.auth.o1
    public final boolean zzb() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.auth.o1
    public final int zzc() {
        return (this.d & 1) == 1 ? 1 : 2;
    }
}
