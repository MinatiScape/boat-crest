package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public final class zzle extends zzhy {
    @zzjg
    private zzkp boundingBox;
    @zzjg
    private Float confidence;
    @zzjg
    private zzln property;
    @zzjg
    private List<zzlq> words;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzle) super.clone();
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final List<zzlq> getWords() {
        return this.words;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzle) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzle) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzle) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzle) clone();
    }

    public final zzkp zzil() {
        return this.boundingBox;
    }

    public final zzln zzim() {
        return this.property;
    }
}
