package com.google.firebase.ml.common.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.firebase.ml.common.internal.modeldownload.zzn;
/* loaded from: classes10.dex */
public class FirebaseLocalModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f11401a;
    public final String b;
    public final String c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final String f11402a;
        public String b = null;
        public String c = null;

        public Builder(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model name can not be empty");
            this.f11402a = str;
        }

        @NonNull
        public FirebaseLocalModel build() {
            String str = this.b;
            Preconditions.checkArgument((str != null && this.c == null) || (str == null && this.c != null), "Set either filePath or assetFilePath.");
            return new FirebaseLocalModel(this.f11402a, this.b, this.c);
        }

        @NonNull
        public Builder setAssetFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.b == null, "A local model source is either from local file or for asset, you can not set both.");
            this.c = str;
            return this;
        }

        @NonNull
        public Builder setFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.c == null, "A local model source is either from local file or for asset, you can not set both.");
            this.b = str;
            return this;
        }
    }

    @KeepForSdk
    public FirebaseLocalModel(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this.f11401a = str;
        this.b = str2;
        this.c = str3;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseLocalModel) {
            FirebaseLocalModel firebaseLocalModel = (FirebaseLocalModel) obj;
            return Objects.equal(this.f11401a, firebaseLocalModel.f11401a) && Objects.equal(this.b, firebaseLocalModel.b) && Objects.equal(this.c, firebaseLocalModel.c);
        }
        return false;
    }

    @Nullable
    @KeepForSdk
    public String getAssetFilePath() {
        return this.c;
    }

    @Nullable
    @KeepForSdk
    public String getFilePath() {
        return this.b;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11401a, this.b, this.c);
    }

    public final zzns.zzak zza(zzn zznVar) {
        zzns.zzaj.zzc zzcVar;
        zzns.zzak.zza zzmn = zzns.zzak.zzmn();
        zzns.zzaj.zza zzd = zzns.zzaj.zzml().zzd(zznVar.zzow());
        String str = this.b;
        if (str == null) {
            str = this.c;
        }
        zzns.zzaj.zza zzbe = zzd.zzbe(str);
        if (this.b != null) {
            zzcVar = zzns.zzaj.zzc.LOCAL;
        } else if (this.c != null) {
            zzcVar = zzns.zzaj.zzc.APP_ASSET;
        } else {
            zzcVar = zzns.zzaj.zzc.SOURCE_UNKNOWN;
        }
        return (zzns.zzak) ((zzwz) zzmn.zza(zzbe.zza(zzcVar)).zzvb());
    }
}
