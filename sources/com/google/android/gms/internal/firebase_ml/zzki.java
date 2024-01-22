package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public final class zzki extends zzhy {
    @zzjg
    private List<zzku> features;
    @zzjg
    private zzkx image;
    @zzjg
    private zzkz imageContext;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzki) super.clone();
    }

    public final zzki zza(zzkx zzkxVar) {
        this.image = zzkxVar;
        return this;
    }

    public final zzki zzb(List<zzku> list) {
        this.features = list;
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzki) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzki) clone();
    }

    public final zzki zza(zzkz zzkzVar) {
        this.imageContext = zzkzVar;
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzki) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzki) zzb(str, obj);
    }
}
