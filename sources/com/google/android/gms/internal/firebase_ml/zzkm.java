package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public final class zzkm extends zzhy {
    @zzjg
    private String blockType;
    @zzjg
    private zzkp boundingBox;
    @zzjg
    private Float confidence;
    @zzjg
    private List<zzle> paragraphs;
    @zzjg
    private zzln property;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzkm) super.clone();
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final List<zzle> getParagraphs() {
        return this.paragraphs;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzkm) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzkm) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzkm) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzkm) clone();
    }

    public final zzkp zzil() {
        return this.boundingBox;
    }

    public final zzln zzim() {
        return this.property;
    }
}
