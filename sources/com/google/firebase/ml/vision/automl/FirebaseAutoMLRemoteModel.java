package com.google.firebase.ml.vision.automl;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
/* loaded from: classes10.dex */
public class FirebaseAutoMLRemoteModel extends FirebaseRemoteModel {

    /* loaded from: classes10.dex */
    public static class Builder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final String f11411a;

        public Builder(@NonNull String str) {
            this.f11411a = str;
        }

        @NonNull
        public FirebaseAutoMLRemoteModel build() {
            Preconditions.checkArgument(!TextUtils.isEmpty(this.f11411a), "Model name cannot be empty");
            return new FirebaseAutoMLRemoteModel(this.f11411a);
        }
    }

    public FirebaseAutoMLRemoteModel(@NonNull String str) {
        super(str, null);
    }
}
