package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzku extends zzhy {
    @zzjg
    private Integer maxResults;
    @zzjg
    private String model;
    @zzjg
    private String type;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzku) super.clone();
    }

    public final zzku zza(Integer num) {
        this.maxResults = num;
        return this;
    }

    public final zzku zzax(String str) {
        this.model = str;
        return this;
    }

    public final zzku zzay(String str) {
        this.type = str;
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzku) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzku) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzku) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzku) zzb(str, obj);
    }
}
