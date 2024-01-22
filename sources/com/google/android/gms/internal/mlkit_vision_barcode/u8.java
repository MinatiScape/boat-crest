package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class u8 extends zzuv {

    /* renamed from: a  reason: collision with root package name */
    public final float f9512a;
    public final float b;
    public final float c;
    public final float d;

    public u8(float f, float f2, float f3, float f4, float f5) {
        this.f9512a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuv
    public final float a() {
        return 0.0f;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuv
    public final float b() {
        return this.c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuv
    public final float c() {
        return this.f9512a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuv
    public final float d() {
        return this.d;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzuv
    public final float e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzuv) {
            zzuv zzuvVar = (zzuv) obj;
            if (Float.floatToIntBits(this.f9512a) == Float.floatToIntBits(zzuvVar.c()) && Float.floatToIntBits(this.b) == Float.floatToIntBits(zzuvVar.e()) && Float.floatToIntBits(this.c) == Float.floatToIntBits(zzuvVar.b()) && Float.floatToIntBits(this.d) == Float.floatToIntBits(zzuvVar.d())) {
                int floatToIntBits = Float.floatToIntBits(0.0f);
                zzuvVar.a();
                if (floatToIntBits == Float.floatToIntBits(0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((Float.floatToIntBits(this.f9512a) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.b)) * 1000003) ^ Float.floatToIntBits(this.c)) * 1000003) ^ Float.floatToIntBits(this.d)) * 1000003) ^ Float.floatToIntBits(0.0f);
    }

    public final String toString() {
        float f = this.f9512a;
        float f2 = this.b;
        float f3 = this.c;
        float f4 = this.d;
        return "PredictedArea{xMin=" + f + ", yMin=" + f2 + ", xMax=" + f3 + ", yMax=" + f4 + ", confidenceScore=0.0}";
    }
}
