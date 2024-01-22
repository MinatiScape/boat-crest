package com.google.android.gms.internal.firebase_ml;

import java.util.List;
/* loaded from: classes7.dex */
public class zzgd extends zzhy {
    @zzjg
    private int code;
    @zzjg
    private List<zza> errors;
    @zzjg
    private String message;

    /* loaded from: classes7.dex */
    public static class zza extends zzhy {
        @zzjg
        private String domain;
        @zzjg
        private String location;
        @zzjg
        private String locationType;
        @zzjg
        private String message;
        @zzjg
        private String reason;

        @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return (zza) super.clone();
        }

        public final String getReason() {
            return this.reason;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzhy
        public final /* synthetic */ zzhy zza(String str, Object obj) {
            return (zza) zzb(str, obj);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
        public final /* synthetic */ zzjf zzb(String str, Object obj) {
            return (zza) super.zzb(str, obj);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzhy
        public final /* synthetic */ zzhy zzfc() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
        public final /* synthetic */ zzjf zzfd() {
            return (zza) clone();
        }
    }

    static {
        zzix.zzd(zza.class);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf, java.util.AbstractMap
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzgd) super.clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zza(String str, Object obj) {
        return (zzgd) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzb(String str, Object obj) {
        return (zzgd) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy
    public final /* synthetic */ zzhy zzfc() {
        return (zzgd) clone();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzhy, com.google.android.gms.internal.firebase_ml.zzjf
    public final /* synthetic */ zzjf zzfd() {
        return (zzgd) clone();
    }

    public final List<zza> zzfe() {
        return this.errors;
    }
}
