package com.google.firebase.ml.vision.cloud;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionCloudDetectorOptions {
    public static final FirebaseVisionCloudDetectorOptions DEFAULT = new Builder().build();
    public static final int LATEST_MODEL = 2;
    public static final int STABLE_MODEL = 1;

    /* renamed from: a  reason: collision with root package name */
    public final int f11436a;
    @ModelType
    public final int b;
    public final boolean c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f11437a = 10;
        @ModelType
        public int b = 1;
        public boolean c = false;

        @NonNull
        public FirebaseVisionCloudDetectorOptions build() {
            return new FirebaseVisionCloudDetectorOptions(this.f11437a, this.b, this.c);
        }

        @NonNull
        public Builder enforceCertFingerprintMatch() {
            this.c = true;
            return this;
        }

        @NonNull
        public Builder setMaxResults(int i) {
            this.f11437a = i;
            return this;
        }

        @NonNull
        public Builder setModelType(@ModelType int i) {
            this.b = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ModelType {
    }

    public FirebaseVisionCloudDetectorOptions(int i, @ModelType int i2, boolean z) {
        this.f11436a = i;
        this.b = i2;
        this.c = z;
    }

    @NonNull
    public Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionCloudDetectorOptions) {
            FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions = (FirebaseVisionCloudDetectorOptions) obj;
            return this.f11436a == firebaseVisionCloudDetectorOptions.f11436a && this.b == firebaseVisionCloudDetectorOptions.b && this.c == firebaseVisionCloudDetectorOptions.c;
        }
        return false;
    }

    public int getMaxResults() {
        return this.f11436a;
    }

    @ModelType
    public int getModelType() {
        return this.b;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f11436a), Integer.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.c;
    }
}
