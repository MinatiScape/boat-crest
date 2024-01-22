package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class x extends zzdf {

    /* renamed from: a  reason: collision with root package name */
    public int f9620a;
    public int b;
    public int c;

    public /* synthetic */ x(byte[] bArr, int i, int i2, boolean z, zzdc zzdcVar) {
        super(null);
        this.c = Integer.MAX_VALUE;
        this.f9620a = 0;
    }

    public final int a(int i) throws zzeo {
        int i2 = this.c;
        this.c = 0;
        int i3 = this.f9620a + this.b;
        this.f9620a = i3;
        if (i3 > 0) {
            this.b = i3;
            this.f9620a = i3 - i3;
        } else {
            this.b = 0;
        }
        return i2;
    }
}
