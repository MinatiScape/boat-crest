package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public final class zzkl extends zzhy {
    @zzjg
    private zzkw context;
    @zzjg
    private zzko cropHintsAnnotation;
    @zzjg
    private zzli error;
    @zzjg
    private List<Object> faceAnnotations;
    @zzjg
    private zzlk fullTextAnnotation;
    @zzjg
    private zzky imagePropertiesAnnotation;
    @zzjg
    private List<zzkv> labelAnnotations;
    @zzjg
    private List<zzkv> landmarkAnnotations;
    @zzjg
    private List<Object> localizedObjectAnnotations;
    @zzjg
    private List<zzkv> logoAnnotations;
    @zzjg
    private zzlg productSearchResults;
    @zzjg
    private zzlj safeSearchAnnotation;
    @zzjg
    private List<zzkv> textAnnotations;
    @zzjg
    private zzlp webDetection;

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzkl) super.clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzkl) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzkl) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzkl) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzkl) clone();
    }

    public final zzlk zzii() {
        return this.fullTextAnnotation;
    }

    public final List<zzkv> zzij() {
        return this.labelAnnotations;
    }

    public final List<zzkv> zzik() {
        return this.landmarkAnnotations;
    }
}
