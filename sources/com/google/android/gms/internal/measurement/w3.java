package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class w3 implements m3 {

    /* renamed from: a  reason: collision with root package name */
    public final zzlg f8934a;
    public final String b;
    public final Object[] c;
    public final int d;

    public w3(zzlg zzlgVar, String str, Object[] objArr) {
        this.f8934a = zzlgVar;
        this.b = str;
        this.c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.d = i | (charAt2 << i2);
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

    @Override // com.google.android.gms.internal.measurement.m3
    public final zzlg zza() {
        return this.f8934a;
    }

    @Override // com.google.android.gms.internal.measurement.m3
    public final boolean zzb() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.measurement.m3
    public final int zzc() {
        return (this.d & 1) == 1 ? 1 : 2;
    }
}
