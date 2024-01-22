package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
/* loaded from: classes7.dex */
public final class z7 implements l7 {

    /* renamed from: a  reason: collision with root package name */
    public final zzyk f8757a;
    public final String b;
    public final Object[] c;
    public final int d;

    public z7(zzyk zzykVar, String str, Object[] objArr) {
        this.f8757a = zzykVar;
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

    @Override // com.google.android.gms.internal.firebase_ml.l7
    public final zzyk a() {
        return this.f8757a;
    }

    @Override // com.google.android.gms.internal.firebase_ml.l7
    public final int b() {
        return (this.d & 1) == 1 ? zzwz.zzg.zzcmb : zzwz.zzg.zzcmc;
    }

    @Override // com.google.android.gms.internal.firebase_ml.l7
    public final boolean c() {
        return (this.d & 2) == 2;
    }

    public final String d() {
        return this.b;
    }

    public final Object[] e() {
        return this.c;
    }
}
