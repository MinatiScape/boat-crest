package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes9.dex */
public final class s8 extends zzut {

    /* renamed from: a  reason: collision with root package name */
    public int f9498a;
    public int b;
    public float c;
    public float d;
    public boolean e;
    public float f;
    public float g;
    public long h;
    public long i;
    public boolean j;
    public float k;
    public float l;
    public short m;

    public final zzut a(int i) {
        this.f9498a = 10;
        this.m = (short) (this.m | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zza(boolean z) {
        this.j = true;
        this.m = (short) (this.m | 512);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzb(float f) {
        this.g = 0.8f;
        this.m = (short) (this.m | 64);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzc(float f) {
        this.f = 0.5f;
        this.m = (short) (this.m | 32);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzd(float f) {
        this.d = 0.8f;
        this.m = (short) (this.m | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zze(int i) {
        this.b = 5;
        this.m = (short) (this.m | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzf(float f) {
        this.c = 0.25f;
        this.m = (short) (this.m | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzh(long j) {
        this.i = 3000L;
        this.m = (short) (this.m | 256);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzi(boolean z) {
        this.e = z;
        this.m = (short) (this.m | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzj(float f) {
        this.k = 0.1f;
        this.m = (short) (this.m | 1024);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzk(long j) {
        this.h = 1500L;
        this.m = (short) (this.m | 128);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzut zzl(float f) {
        this.l = 0.05f;
        this.m = (short) (this.m | 2048);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzut
    public final zzuu zzm() {
        if (this.m != 4095) {
            StringBuilder sb = new StringBuilder();
            if ((this.m & 1) == 0) {
                sb.append(" recentFramesToCheck");
            }
            if ((this.m & 2) == 0) {
                sb.append(" recentFramesContainingPredictedArea");
            }
            if ((this.m & 4) == 0) {
                sb.append(" recentFramesIou");
            }
            if ((this.m & 8) == 0) {
                sb.append(" maxCoverage");
            }
            if ((this.m & 16) == 0) {
                sb.append(" useConfidenceScore");
            }
            if ((this.m & 32) == 0) {
                sb.append(" lowerConfidenceScore");
            }
            if ((this.m & 64) == 0) {
                sb.append(" higherConfidenceScore");
            }
            if ((this.m & 128) == 0) {
                sb.append(" zoomIntervalInMillis");
            }
            if ((this.m & 256) == 0) {
                sb.append(" resetIntervalInMillis");
            }
            if ((this.m & 512) == 0) {
                sb.append(" enableZoomThreshold");
            }
            if ((this.m & 1024) == 0) {
                sb.append(" zoomInThreshold");
            }
            if ((this.m & 2048) == 0) {
                sb.append(" zoomOutThreshold");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new t8(this.f9498a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, null);
    }
}
