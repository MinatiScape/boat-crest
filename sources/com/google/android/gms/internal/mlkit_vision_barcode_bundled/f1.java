package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class f1 implements v0 {

    /* renamed from: a  reason: collision with root package name */
    public final zzfo f9594a;
    public final String b;
    public final Object[] c;
    public final int d;

    public f1(zzfo zzfoVar, String str, Object[] objArr) {
        this.f9594a = zzfoVar;
        this.b = str;
        this.c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 1;
        int i3 = 13;
        while (true) {
            int i4 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 < 55296) {
                this.d = i | (charAt2 << i3);
                return;
            }
            i |= (charAt2 & 8191) << i3;
            i3 += 13;
            i2 = i4;
        }
    }

    public final String a() {
        return this.b;
    }

    public final Object[] b() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.v0
    public final zzfo zza() {
        return this.f9594a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.v0
    public final boolean zzb() {
        return (this.d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.v0
    public final int zzc() {
        return (this.d & 1) == 1 ? 1 : 2;
    }
}
