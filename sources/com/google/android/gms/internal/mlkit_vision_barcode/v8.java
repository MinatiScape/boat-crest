package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.GmsLogger;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public final class v8 implements zzek {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzpk f9519a;
    public final /* synthetic */ float b;
    public final /* synthetic */ zzuv c;
    public final /* synthetic */ float d;
    public final /* synthetic */ zzus e;

    public v8(zzus zzusVar, zzpk zzpkVar, float f, zzuv zzuvVar, float f2) {
        this.e = zzusVar;
        this.f9519a = zzpkVar;
        this.b = f;
        this.c = zzuvVar;
        this.d = f2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzek
    public final void zza(Throwable th) {
        GmsLogger gmsLogger;
        AtomicBoolean atomicBoolean;
        gmsLogger = zzus.s;
        float f = this.d;
        gmsLogger.w("AutoZoom", "Unable to set zoom to " + f, th);
        atomicBoolean = this.e.b;
        atomicBoolean.set(false);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzek
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        AtomicBoolean atomicBoolean;
        Float f = (Float) obj;
        if (f.floatValue() >= 1.0f) {
            zzus.d(this.e, f.floatValue());
            this.e.h(this.f9519a, this.b, f.floatValue(), this.c);
        }
        atomicBoolean = this.e.b;
        atomicBoolean.set(false);
    }
}
