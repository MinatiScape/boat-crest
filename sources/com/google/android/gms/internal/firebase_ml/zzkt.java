package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzkt extends zzhy {
    @zzjg
    private Float confidence;
    @zzjg
    private String languageCode;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzkt) super.clone();
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzkt) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzkt) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzkt) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzkt) clone();
    }
}
