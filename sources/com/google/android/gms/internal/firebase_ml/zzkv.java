package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public final class zzkv extends zzhy {
    @zzjg
    private zzkp boundingPoly;
    @zzjg
    private Float confidence;
    @zzjg
    private String description;
    @zzjg
    private String locale;
    @zzjg
    private List<zzlc> locations;
    @zzjg
    private String mid;
    @zzjg
    private List<Object> properties;
    @zzjg
    private Float score;
    @zzjg
    private Float topicality;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzkv) super.clone();
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<zzlc> getLocations() {
        return this.locations;
    }

    public final String getMid() {
        return this.mid;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzkv) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzkv) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzkv) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzkv) clone();
    }

    public final zzkp zziq() {
        return this.boundingPoly;
    }

    public final Float zzir() {
        return this.score;
    }
}
