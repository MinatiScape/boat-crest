package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
final class zzfu extends zzfx {
    private final /* synthetic */ zzfv zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfu(zzfv zzfvVar, zzfs zzfsVar, CharSequence charSequence) {
        super(zzfsVar, charSequence);
        this.zzb = zzfvVar;
    }

    @Override // com.google.android.libraries.places.internal.zzfx
    public final int zza(int i) {
        return this.zzb.zza.zza(((zzfx) this).zza, i);
    }

    @Override // com.google.android.libraries.places.internal.zzfx
    public final int zzb(int i) {
        return i + 1;
    }
}
