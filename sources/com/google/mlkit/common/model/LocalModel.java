package com.google.mlkit.common.model;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzaa;
import com.google.android.gms.internal.mlkit_common.zzz;
/* loaded from: classes10.dex */
public class LocalModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f11577a;
    @Nullable
    public final String b;
    @Nullable
    public final Uri c;
    public final boolean d;

    /* loaded from: classes10.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public String f11578a = null;
        @Nullable
        public String b = null;
        @Nullable
        public Uri c = null;
        public boolean d = false;

        @NonNull
        public LocalModel build() {
            String str = this.f11578a;
            boolean z = true;
            if ((str == null || this.b != null || this.c != null) && ((str != null || this.b == null || this.c != null) && (str != null || this.b != null || this.c == null))) {
                z = false;
            }
            Preconditions.checkArgument(z, "Set one of filePath, assetFilePath and URI.");
            return new LocalModel(this.f11578a, this.b, this.c, this.d, null);
        }

        @NonNull
        public Builder setAbsoluteFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            boolean z = false;
            if (this.b == null && this.c == null && !this.d) {
                z = true;
            }
            Preconditions.checkArgument(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.f11578a = str;
            return this;
        }

        @NonNull
        public Builder setAbsoluteManifestFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Manifest file path can not be empty");
            boolean z = false;
            if (this.b == null && this.c == null && (this.f11578a == null || this.d)) {
                z = true;
            }
            Preconditions.checkArgument(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.f11578a = str;
            this.d = true;
            return this;
        }

        @NonNull
        public Builder setAssetFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            boolean z = false;
            if (this.f11578a == null && this.c == null && !this.d) {
                z = true;
            }
            Preconditions.checkArgument(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setAssetManifestFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Manifest file path can not be empty");
            boolean z = false;
            if (this.f11578a == null && this.c == null && (this.b == null || this.d)) {
                z = true;
            }
            Preconditions.checkArgument(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.b = str;
            this.d = true;
            return this;
        }

        @NonNull
        public Builder setUri(@NonNull Uri uri) {
            boolean z = false;
            if (this.f11578a == null && this.b == null) {
                z = true;
            }
            Preconditions.checkArgument(z, "A local model source is from absolute file path, asset file path or URI, you can only set one of them.");
            this.c = uri;
            return this;
        }
    }

    public /* synthetic */ LocalModel(String str, String str2, Uri uri, boolean z, zzc zzcVar) {
        this.f11577a = str;
        this.b = str2;
        this.c = uri;
        this.d = z;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalModel) {
            LocalModel localModel = (LocalModel) obj;
            return Objects.equal(this.f11577a, localModel.f11577a) && Objects.equal(this.b, localModel.b) && Objects.equal(this.c, localModel.c) && this.d == localModel.d;
        }
        return false;
    }

    @Nullable
    @KeepForSdk
    public String getAbsoluteFilePath() {
        return this.f11577a;
    }

    @Nullable
    @KeepForSdk
    public String getAssetFilePath() {
        return this.b;
    }

    @Nullable
    @KeepForSdk
    public Uri getUri() {
        return this.c;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11577a, this.b, this.c, Boolean.valueOf(this.d));
    }

    @KeepForSdk
    public boolean isManifestFile() {
        return this.d;
    }

    @NonNull
    public String toString() {
        zzz zza = zzaa.zza(this);
        zza.zza("absoluteFilePath", this.f11577a);
        zza.zza("assetFilePath", this.b);
        zza.zza(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, this.c);
        zza.zzb("isManifestFile", this.d);
        return zza.toString();
    }
}
