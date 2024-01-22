package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class u3 implements l3 {

    /* renamed from: a  reason: collision with root package name */
    public final zzik f8853a;
    public final String b;
    public final Object[] c;
    public final int d;

    public u3(zzik zzikVar, String str, Object[] objArr) {
        this.f8853a = zzikVar;
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

    @Override // com.google.android.gms.internal.fitness.l3
    public final int a() {
        return (this.d & 1) == 1 ? zziw.zzaaw : zziw.zzaax;
    }

    @Override // com.google.android.gms.internal.fitness.l3
    public final boolean b() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.fitness.l3
    public final zzik c() {
        return this.f8853a;
    }

    public final String d() {
        return this.b;
    }

    public final Object[] e() {
        return this.c;
    }
}
