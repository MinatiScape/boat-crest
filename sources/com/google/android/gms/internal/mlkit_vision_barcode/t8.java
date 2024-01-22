package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class t8 extends zzuu {

    /* renamed from: a  reason: collision with root package name */
    public final int f9505a;
    public final int b;
    public final float c;
    public final float d;
    public final boolean e;
    public final float f;
    public final float g;
    public final long h;
    public final long i;
    public final boolean j;
    public final float k;
    public final float l;

    public /* synthetic */ t8(int i, int i2, float f, float f2, boolean z, float f3, float f4, long j, long j2, boolean z2, float f5, float f6, zzum zzumVar) {
        this.f9505a = i;
        this.b = i2;
        this.c = f;
        this.d = f2;
        this.e = z;
        this.f = f3;
        this.g = f4;
        this.h = j;
        this.i = j2;
        this.j = z2;
        this.k = f5;
        this.l = f6;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float a() {
        return this.g;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float b() {
        return this.f;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float c() {
        return this.d;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float d() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float e() {
        return this.k;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzuu) {
            zzuu zzuuVar = (zzuu) obj;
            if (this.f9505a == zzuuVar.h() && this.b == zzuuVar.g() && Float.floatToIntBits(this.c) == Float.floatToIntBits(zzuuVar.d()) && Float.floatToIntBits(this.d) == Float.floatToIntBits(zzuuVar.c()) && this.e == zzuuVar.l() && Float.floatToIntBits(this.f) == Float.floatToIntBits(zzuuVar.b()) && Float.floatToIntBits(this.g) == Float.floatToIntBits(zzuuVar.a()) && this.h == zzuuVar.j() && this.i == zzuuVar.i() && this.j == zzuuVar.k() && Float.floatToIntBits(this.k) == Float.floatToIntBits(zzuuVar.e()) && Float.floatToIntBits(this.l) == Float.floatToIntBits(zzuuVar.f())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final float f() {
        return this.l;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final int g() {
        return this.b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final int h() {
        return this.f9505a;
    }

    public final int hashCode() {
        int floatToIntBits = ((((((((((((this.f9505a ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ Float.floatToIntBits(this.c)) * 1000003) ^ Float.floatToIntBits(this.d)) * 1000003) ^ (true != this.e ? 1237 : 1231)) * 1000003) ^ Float.floatToIntBits(this.f)) * 1000003) ^ Float.floatToIntBits(this.g);
        int i = (int) this.h;
        return (((((((((floatToIntBits * 1000003) ^ i) * 1000003) ^ ((int) this.i)) * 1000003) ^ (true == this.j ? 1231 : 1237)) * 1000003) ^ Float.floatToIntBits(this.k)) * 1000003) ^ Float.floatToIntBits(this.l);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final long i() {
        return this.i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final long j() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final boolean k() {
        return this.j;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuu
    public final boolean l() {
        return this.e;
    }

    public final String toString() {
        int i = this.f9505a;
        int i2 = this.b;
        float f = this.c;
        float f2 = this.d;
        boolean z = this.e;
        float f3 = this.f;
        float f4 = this.g;
        long j = this.h;
        long j2 = this.i;
        boolean z2 = this.j;
        float f5 = this.k;
        float f6 = this.l;
        return "AutoZoomOptions{recentFramesToCheck=" + i + ", recentFramesContainingPredictedArea=" + i2 + ", recentFramesIou=" + f + ", maxCoverage=" + f2 + ", useConfidenceScore=" + z + ", lowerConfidenceScore=" + f3 + ", higherConfidenceScore=" + f4 + ", zoomIntervalInMillis=" + j + ", resetIntervalInMillis=" + j2 + ", enableZoomThreshold=" + z2 + ", zoomInThreshold=" + f5 + ", zoomOutThreshold=" + f6 + "}";
    }
}
