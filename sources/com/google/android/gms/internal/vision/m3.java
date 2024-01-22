package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
/* loaded from: classes10.dex */
public final class m3 implements d3 {

    /* renamed from: a  reason: collision with root package name */
    public final zzic f9992a;
    public final String b;
    public final Object[] c;
    public final int d;

    public m3(zzic zzicVar, String str, Object[] objArr) {
        this.f9992a = zzicVar;
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

    @Override // com.google.android.gms.internal.vision.d3
    public final boolean a() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.vision.d3
    public final zzic b() {
        return this.f9992a;
    }

    @Override // com.google.android.gms.internal.vision.d3
    public final int c() {
        return (this.d & 1) == 1 ? zzgs.zzf.zzwz : zzgs.zzf.zzxa;
    }

    public final String d() {
        return this.b;
    }

    public final Object[] e() {
        return this.c;
    }
}
