package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionCloudImageLabelerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final float f11454a;
    public final boolean b;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public float f11455a = 0.5f;
        public boolean b = false;

        @NonNull
        public FirebaseVisionCloudImageLabelerOptions build() {
            return new FirebaseVisionCloudImageLabelerOptions(this.f11455a, this.b);
        }

        @NonNull
        public Builder enforceCertFingerprintMatch() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setConfidenceThreshold(float f) {
            Preconditions.checkArgument(Float.compare(f, 0.0f) >= 0 && Float.compare(f, 1.0f) <= 0, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.f11455a = f;
            return this;
        }
    }

    public FirebaseVisionCloudImageLabelerOptions(float f, boolean z) {
        this.f11454a = f;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionCloudImageLabelerOptions) {
            FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions = (FirebaseVisionCloudImageLabelerOptions) obj;
            return Float.compare(this.f11454a, firebaseVisionCloudImageLabelerOptions.f11454a) == 0 && this.b == firebaseVisionCloudImageLabelerOptions.b;
        }
        return false;
    }

    public float getConfidenceThreshold() {
        return this.f11454a;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.f11454a), Boolean.valueOf(this.b));
    }

    public boolean isEnforceCertFingerprintMatch() {
        return this.b;
    }
}
