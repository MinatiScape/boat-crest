package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLLocalModel;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLRemoteModel;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionOnDeviceAutoMLImageLabelerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final float f11457a;
    @Nullable
    public final FirebaseAutoMLLocalModel b;
    @Nullable
    public final FirebaseAutoMLRemoteModel c;

    public FirebaseVisionOnDeviceAutoMLImageLabelerOptions(float f, @Nullable FirebaseAutoMLLocalModel firebaseAutoMLLocalModel, @Nullable FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) {
        this.f11457a = f;
        this.b = firebaseAutoMLLocalModel;
        this.c = firebaseAutoMLRemoteModel;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionOnDeviceAutoMLImageLabelerOptions) {
            FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions = (FirebaseVisionOnDeviceAutoMLImageLabelerOptions) obj;
            return Float.compare(this.f11457a, firebaseVisionOnDeviceAutoMLImageLabelerOptions.f11457a) == 0 && Objects.equal(this.b, firebaseVisionOnDeviceAutoMLImageLabelerOptions.b) && Objects.equal(this.c, firebaseVisionOnDeviceAutoMLImageLabelerOptions.c);
        }
        return false;
    }

    public final float getConfidenceThreshold() {
        return this.f11457a;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.f11457a), this.b, this.c);
    }

    public final FirebaseAutoMLLocalModel zzqt() {
        return this.b;
    }

    public final FirebaseAutoMLRemoteModel zzqu() {
        return this.c;
    }

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public float f11458a = 0.5f;
        @Nullable
        public FirebaseAutoMLLocalModel b;
        @Nullable
        public FirebaseAutoMLRemoteModel c;

        public Builder(@NonNull FirebaseAutoMLLocalModel firebaseAutoMLLocalModel) {
            Preconditions.checkNotNull(firebaseAutoMLLocalModel);
            this.b = firebaseAutoMLLocalModel;
        }

        @Nonnull
        public FirebaseVisionOnDeviceAutoMLImageLabelerOptions build() {
            Preconditions.checkArgument((this.b == null && this.c == null) ? false : true, "Either a local model or remote model must be set.");
            return new FirebaseVisionOnDeviceAutoMLImageLabelerOptions(this.f11458a, this.b, this.c);
        }

        @Nonnull
        public Builder setConfidenceThreshold(float f) {
            Preconditions.checkArgument(Float.compare(f, 0.0f) >= 0 && Float.compare(f, 1.0f) <= 0, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.f11458a = f;
            return this;
        }

        public Builder(@NonNull FirebaseAutoMLRemoteModel firebaseAutoMLRemoteModel) {
            Preconditions.checkNotNull(firebaseAutoMLRemoteModel);
            this.c = firebaseAutoMLRemoteModel;
        }
    }
}
