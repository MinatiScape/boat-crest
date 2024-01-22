package com.google.firebase.ml.vision.automl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModel;
/* loaded from: classes10.dex */
public class FirebaseAutoMLLocalModel extends FirebaseLocalModel {

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11410a = null;
        public String b = null;

        @NonNull
        public FirebaseAutoMLLocalModel build() {
            String str = this.f11410a;
            Preconditions.checkArgument((str != null && this.b == null) || (str == null && this.b != null), "Set either filePath or assetFilePath.");
            return new FirebaseAutoMLLocalModel(this.f11410a, this.b);
        }

        @NonNull
        public Builder setAssetFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.f11410a == null, "A local model source is either from local file or for asset, you can not set both.");
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.b == null, "A local model source is either from local file or for asset, you can not set both.");
            this.f11410a = str;
            return this;
        }
    }

    public FirebaseAutoMLLocalModel(@Nullable String str, @Nullable String str2) {
        super(null, str, str2);
    }
}
